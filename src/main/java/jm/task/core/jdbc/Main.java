package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImplHibernate;
import jm.task.core.jdbc.service.UserServiceImplJDBC;

import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        // реализуйте алгоритм здесь

         /**********************************************************************************************
         *  ПРИМЕР РЕАЛИЗАЦИИ С JDBC********************************************************************
         *  ********************************************************************************************
         */
        UserServiceImplJDBC connectJDBC = new UserServiceImplJDBC();

       /**  Создание таблицы User(ов) */
        connectJDBC.createUsersTable();
//
       /**  Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления
       *  должен быть вывод в консоль ( User с именем – name добавлен в базу данных */
        connectJDBC.saveUser("Ivan", "Petrov", (byte)13);
        connectJDBC.saveUser("Sergey", "Suvorov", (byte)16);
        connectJDBC.saveUser("Maxim", "Ivanov", (byte)45);
        connectJDBC.saveUser("Petr", "Sidorov", (byte)73);

       /**  Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User) */
       List<User> list = connectJDBC.getAllUsers();
       System.out.println("ВЫВОД после получения данных!");
       list.stream().forEach (a -> System.out.println(a.toString()));

       //удаление по id
        connectJDBC.removeUserById(1);

       /**  Очистка таблицы User(ов) */
        connectJDBC.cleanUsersTable();

       /**  Удаление таблицы */
        connectJDBC.dropUsersTable();

        /**
         *  ********************************************************************************************
         *  ПРИМЕР РЕАЛИЗАЦИИ С HIBERNATE***************************************************************
         *  ********************************************************************************************
         */

        UserServiceImplHibernate connect = new UserServiceImplHibernate();

        /**  Создание таблицы User(ов) */
        connect.createUsersTable();

        /**  Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления
         *  должен быть вывод в консоль ( User с именем – name добавлен в базу данных */
        connect.saveUser("Ivan", "Petrov", (byte)13);
        connect.saveUser("Sergey", "Suvorov", (byte)16);
        connect.saveUser("Maxim", "Ivanov", (byte)45);
        connect.saveUser("Petr", "Sidorov", (byte)73);
//
        /**  Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User) */
        List<User> listUser = connect.getAllUsers();
        System.out.println("ВЫВОД ВЫВОД ПОЛУЧЕННЫХ ДАННЫХ!");
        list.stream().forEach (a -> System.out.println(a.toString()));
//
//        удаление по id
        connect.removeUserById(4);

        /**  Очистка таблицы User(ов) */
        connect.cleanUsersTable();

        /**  Удаление таблицы */
        connect.dropUsersTable();

    }
}
