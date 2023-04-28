package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.TaskJdbcException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util_Hibernate {
    private static SessionFactory sessionFactory = null;
    private Util_Hibernate() throws TaskJdbcException {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        System.out.println("Создаем фабрику для подключения к БД согласно настройкам соединения!");
    }
    public static SessionFactory getSessionFactoryToMySQL() throws TaskJdbcException {
        if(sessionFactory == null) {
            new Util_Hibernate();
            System.out.println("Создали фабрику для открытия подключений к MySQL");
        } else {
                // проверяем открыта ли фабрика
                if(sessionFactory.isClosed()) {
                    System.out.println("Фабрика закрыта! Создаем новую");
                    new Util_Hibernate();
                } else {
                    System.out.println("Фабрика открыта: используем для подключения к MySQL");
                }
            return sessionFactory;
        }
        return sessionFactory;
    }

}
