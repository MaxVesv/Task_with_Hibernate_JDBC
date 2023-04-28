package jm.task.core.jdbc.dao;

import java.sql.SQLException;

public class TaskJdbcException extends SQLException {
    public TaskJdbcException(String massage, Boolean statusMsgTaskOrNotTask) {
        if (statusMsgTaskOrNotTask) {
            /**
             * Для всех сообщений исключений, связанных с логикой задачи
             * Место для вставки обработки исключения по задаче
             *
             */
            System.out.println("ERROR DAO " + massage);
        } else {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            massage = "Для исключения передан неправильный параметр = "
                    + statusMsgTaskOrNotTask
                    + "\nИсключение вызванно в методе " + stackTrace[2].getMethodName()
                    + " из класса: " + stackTrace[2].getClassName()
                    + "\nСообщение исключения: " + massage;
            throw new RuntimeException(massage);
        }
    }

    public TaskJdbcException(SQLException e) {
        super(e);
        System.out.println();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println("Исключение вызвано в методе:" + stackTrace[2].getMethodName() +
                "\nиз класса: " + stackTrace[2].getClassName());
    }
}
