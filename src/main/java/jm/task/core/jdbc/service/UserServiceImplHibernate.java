// Eудалить данный фаил после завершения проверки ментором предыдущего задания по JDBC
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.TaskJdbcException;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImplHibernate implements UserService {

    public void createUsersTable() throws TaskJdbcException {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();

    }

    public void dropUsersTable() throws TaskJdbcException {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) throws TaskJdbcException {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) throws TaskJdbcException {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.removeUserById(id);

    }

    public List<User> getAllUsers() throws TaskJdbcException {
        UserDao userDao = new UserDaoHibernateImpl();
        List<User> listUser = userDao.getAllUsers();
        return listUser;
    }

    public void cleanUsersTable() throws TaskJdbcException {
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.cleanUsersTable();

    }
}
