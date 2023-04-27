package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.TaskJdbcException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util_Jdbc {
    private static final String LOGIN = "Kata_login_114";
    private static final String PASSW = "Kata_login_114";
    private static Connection jdbc_connection = null;

    private Util_Jdbc() throws TaskJdbcException {
        try {
            jdbc_connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PP_1_1_4_KATA", LOGIN, PASSW);
        } catch (SQLException e) {
            throw new TaskJdbcException(e);
        }

    }
    public static Connection getJDBCConnectionToMySQL() throws TaskJdbcException {
        if(jdbc_connection == null) {
            new Util_Jdbc();
            System.out.println("Создали подключение к MySQL");

        } else {
            throw new TaskJdbcException("Невозможно создать jdbc_connection, так как значение уже присвоено!", true);
        }
        return jdbc_connection;
    }

}
