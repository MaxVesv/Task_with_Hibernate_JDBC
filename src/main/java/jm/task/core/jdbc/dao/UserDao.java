package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable() throws TaskJdbcException;

    void dropUsersTable() throws TaskJdbcException;

    void saveUser(String name, String lastName, byte age) throws TaskJdbcException;

    void removeUserById(long id) throws TaskJdbcException;

    List<User> getAllUsers() throws TaskJdbcException;

    void cleanUsersTable() throws TaskJdbcException;
}
