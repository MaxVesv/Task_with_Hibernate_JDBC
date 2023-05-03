// Eудалить данный фаил после завершения проверки ментором предыдущего задания по JDBC
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.TaskJdbcException;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImplHibernate implements UserService {

    private UserDao userDao;
    public UserServiceImplHibernate() {
         this.userDao = new UserDaoHibernateImpl();
    }

    public void createUsersTable() throws TaskJdbcException {
        userDao.createUsersTable();

    }

    public void dropUsersTable() throws TaskJdbcException {
        userDao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) throws TaskJdbcException {
        userDao.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) throws TaskJdbcException {
        userDao.removeUserById(id);

    }

    public List<User> getAllUsers() throws TaskJdbcException {
        List<User> listUser = userDao.getAllUsers();
        return listUser;
    }

    public void cleanUsersTable() throws TaskJdbcException {
        userDao.cleanUsersTable();

    }
}
