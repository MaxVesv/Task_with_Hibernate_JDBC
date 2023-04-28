package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util_Jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws TaskJdbcException {
        String sqlCreate = """
                CREATE TABLE IF NOT EXISTS user
                (id INT PRIMARY KEY AUTO_INCREMENT,
                name NVARCHAR(20),
                lastName NVARCHAR(20),
                age INT)
                """;

        try (Connection connection = Util_Jdbc.getJDBCConnectionToMySQL();
             Statement stm = connection.createStatement()) {
            System.out.println("---------------------------------------");
            System.out.println("Создаем таблицу если отсутсвует!");
            stm.execute(sqlCreate);
        } catch (SQLException e) {
            throw new TaskJdbcException(e);
        }
    }

    public void dropUsersTable() throws TaskJdbcException {
        String sqlDrop = """
                DROP TABLE IF EXISTS user
                """;

        try (Connection connection = Util_Jdbc.getJDBCConnectionToMySQL();
             Statement stm = connection.createStatement()) {
            System.out.println("---------------------------------------");
            System.out.println("Удаляем таблицу user!");
            stm.executeUpdate(sqlDrop);

        } catch (SQLException e) {
            throw new TaskJdbcException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) throws TaskJdbcException {
        String sqlSave = """
                INSERT INTO user
                (name,
                lastName,
                age) VALUES (?,?,?)
                """;

        try (Connection connection = Util_Jdbc.getJDBCConnectionToMySQL();
             PreparedStatement stm = connection.prepareStatement(sqlSave)) {
            stm.setString(1, name);
            stm.setString(2, lastName);
            stm.setInt(3, age);

            System.out.println("---------------------------------------");
            System.out.println("Добавляем новую запись в таблицу user!");
            if (stm.executeUpdate() != 0) {
                System.out.println(String.format("User %s %s в возрасте %d упешно добавлен в БД!",
                        name, lastName, age));
            } else {
                System.out.println(String.format("Не удалось добавить в БД User %s %s в возрасте %d!",
                        name, lastName, age));
            }
        } catch (SQLException e) {
            throw new TaskJdbcException(e);
        }
    }

    public void removeUserById(long id) throws TaskJdbcException {
        String sqlSelect = """
                SELECT id, name, lastName, age 
                FROM user
                WHERE id = ?
                """;

        try (Connection connection = Util_Jdbc.getJDBCConnectionToMySQL();
             PreparedStatement stm = connection.prepareStatement(sqlSelect)) {
            System.out.println("---------------------------------------");
            System.out.println("Получаем user согласно Id!");
            stm.setLong(1, id);

            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("Id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                System.out.println(String.format("Выводим данные о User c Id=%d :", id) + user.toString());
            }


        } catch (SQLException e) {
            throw new TaskJdbcException(e);
        }
    }

    public List<User> getAllUsers() throws TaskJdbcException {
        List<User> listUsers = new ArrayList<>();

        String sqlSelect = """
                SELECT * FROM user
                """;

        try (Connection connection = Util_Jdbc.getJDBCConnectionToMySQL();
             Statement stm = connection.createStatement()) {
            stm.setFetchSize(50);
            stm.setQueryTimeout(5);
            stm.setMaxRows(1000);

            System.out.println("---------------------------------------");
            System.out.println("Получаем данные из таблицы User!");
            ResultSet resultSet = stm.executeQuery(sqlSelect);

            System.out.println("Сохраняем в List полученный результат:");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("Id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                System.out.println("В List добавили пользователя: " + user.toString());
                listUsers.add(user);
            }
        } catch (SQLException e) {
            throw new TaskJdbcException(e);
        }
        return listUsers;
    }

    public void cleanUsersTable() throws TaskJdbcException {
        String sqlDelete = """
                DELETE FROM user
                """;

        try (Connection connection = Util_Jdbc.getJDBCConnectionToMySQL();
             Statement stm = connection.createStatement()) {
            System.out.println("---------------------------------------");
            System.out.println("Очищаем таблицу user!");
            int numDeleteColumns = stm.executeUpdate(sqlDelete);
            if (numDeleteColumns != 0) {
                System.out.println(String.format("Успешно удалили %d записей из БД", numDeleteColumns));
            } else {
                System.out.println("Не удалось выполнить удаление данных из таблицы user !");
            }
        } catch (SQLException e) {
            throw new TaskJdbcException(e);
        }

    }
}
