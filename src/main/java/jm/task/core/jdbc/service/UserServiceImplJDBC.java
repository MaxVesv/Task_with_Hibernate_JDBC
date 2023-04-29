package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.TaskJdbcException;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImplJDBC implements UserService {
    public void createUsersTable() throws TaskJdbcException {
        UserDao service = UserDaoJDBCImpl.getDaoInstanceJdbc();
        service.createUsersTable();

    }

    public void dropUsersTable() throws TaskJdbcException {
        UserDao service = UserDaoJDBCImpl.getDaoInstanceJdbc();
        service.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) throws TaskJdbcException {
        UserDao service = UserDaoJDBCImpl.getDaoInstanceJdbc();
        service.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) throws TaskJdbcException {
        UserDao service = UserDaoJDBCImpl.getDaoInstanceJdbc();
        service.removeUserById(id);

    }

    public List<User> getAllUsers() throws TaskJdbcException {
        UserDao service = UserDaoJDBCImpl.getDaoInstanceJdbc();
        List<User> listUser = service.getAllUsers();
        return listUser;
    }

    public void cleanUsersTable() throws TaskJdbcException {
        UserDao service = UserDaoJDBCImpl.getDaoInstanceJdbc();
        service.cleanUsersTable();

    }
}
