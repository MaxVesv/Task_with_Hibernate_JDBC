package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util_Jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

      public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws TaskJdbcException {
        String sqlCreate = """
                CREATE TABLE IF NOT EXISTS user
                (id INT,
                name NVARCHAR(20),
                lastName NVARCHAR(20),
                age INT)
                """;
        try (Connection connection = Util_Jdbc.getJDBCConnectionToMySQL();
            Statement stm = connection.createStatement())
        {
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
        String sqlCheck = """
                CHECK TABLE user
                """;

        try(Connection connection = Util_Jdbc.getJDBCConnectionToMySQL();
            Statement stm = connection.createStatement())
        {
            System.out.println("Удаляем!");
//            stm.executeUpdate(sqlDrop);

//            System.out.println("Перед удалением проверим наличие таблицы user");
//            if (stm.execute(sqlCheck)) {
//                System.out.println("Таблица user существует в БД \n" +
//                                   "Удаляем таблицу user!");
//                if (stm.executeUpdate(sqlDrop) == 0) {
//                    System.out.println("Таблица user не удалена!");
////                    throw new TaskJdbcException("Таблица user не удалена!", true);
//                } else {
//                    System.out.println("Таблица user успешно удалена!");
//                }
//            } else {
//                System.out.println("Таблица user отсуствует в БД для удаления!");
//            }
        } catch (SQLException e) {
            throw new TaskJdbcException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
