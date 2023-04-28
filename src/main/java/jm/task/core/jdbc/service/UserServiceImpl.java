package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.TaskJdbcException;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() throws TaskJdbcException {
        UserDaoJDBCImpl serviceJDBCService = UserDaoJDBCImpl.getDaoInstanceJdbc();
        serviceJDBCService.createUsersTable();

    }

    public void dropUsersTable() throws TaskJdbcException {
        UserDaoJDBCImpl serviceJDBCService = UserDaoJDBCImpl.getDaoInstanceJdbc();
        serviceJDBCService.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) throws TaskJdbcException {
        UserDaoJDBCImpl serviceJDBCService = UserDaoJDBCImpl.getDaoInstanceJdbc();
        serviceJDBCService.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) throws TaskJdbcException {
        UserDaoJDBCImpl serviceJDBCService = UserDaoJDBCImpl.getDaoInstanceJdbc();
        serviceJDBCService.removeUserById(id);

    }

    public List<User> getAllUsers() throws TaskJdbcException {
        UserDaoJDBCImpl serviceJDBCService = UserDaoJDBCImpl.getDaoInstanceJdbc();
        List<User> listUser = serviceJDBCService.getAllUsers();
        return listUser;
    }

    public void cleanUsersTable() throws TaskJdbcException {
        UserDaoJDBCImpl serviceJDBCService = UserDaoJDBCImpl.getDaoInstanceJdbc();
        serviceJDBCService.cleanUsersTable();

    }
}
