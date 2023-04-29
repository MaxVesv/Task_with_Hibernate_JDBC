package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util_Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final UserDaoHibernateImpl DAO_INSTANCE_HIBERNATE = new UserDaoHibernateImpl();

    private UserDaoHibernateImpl() {

    }

    public static UserDaoHibernateImpl getDaoInstanceHubernate() {
        return DAO_INSTANCE_HIBERNATE;
    }


    @Override
    public void createUsersTable() throws TaskJdbcException {
        String sqlCreate = """
                CREATE TABLE IF NOT EXISTS user
                (id INT PRIMARY KEY AUTO_INCREMENT,
                name NVARCHAR(20),
                lastName NVARCHAR(20),
                age INT)
                """;

        SessionFactory factory = Util_Hibernate.getSessionFactoryToMySQL();
        Session session = factory.getCurrentSession();

        //---------------------------//
        session.beginTransaction();
        Query query = session.createNativeQuery(sqlCreate);
        query.executeUpdate();
        session.getTransaction().commit();
        //---------------------------//

        System.out.println("Выполнена команда для создания таблицы User!");
        System.out.println("--------------------------------------------");

    }

    @Override
    public void dropUsersTable() throws TaskJdbcException {
        String sqlDrop = """
                DROP TABLE IF EXISTS user
                """;

        SessionFactory factory = Util_Hibernate.getSessionFactoryToMySQL();
        Session session = factory.getCurrentSession();

        //---------------------------//
        session.beginTransaction();
        Query query = session.createNativeQuery(sqlDrop);
        query.executeUpdate();
        session.getTransaction().commit();
        //---------------------------//

        System.out.println("Выполнена команда на удаление таблицы User!");
        System.out.println("--------------------------------------------");

    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws TaskJdbcException {
        SessionFactory factory = Util_Hibernate.getSessionFactoryToMySQL();
        Session session = factory.getCurrentSession();

        //---------------------------//
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        //---------------------------//

        System.out.println("Выполнена команда для сохранения нового пользователя!");
        System.out.println("--------------------------------------------");
    }

    @Override
    public void removeUserById(long id) throws TaskJdbcException {
        String hqlSelect = """
                DELETE from User where id = :id
                """;

        SessionFactory factory = Util_Hibernate.getSessionFactoryToMySQL();
        Session session = factory.getCurrentSession();

        //---------------------------//
        session.beginTransaction();
        Query query = session.createQuery(hqlSelect);
        int tempLines = query.setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
        //---------------------------//

        if (tempLines == 0) {
            System.out.println(String.format("Не удалось удалить User с id=%d", id));
            System.out.println("--------------------------------------------");
        } else {
            System.out.println(String.format("Удалено строк=%d с id=%d", tempLines, id));
            System.out.println("--------------------------------------------");
        }
    }

    @Override
    public List<User> getAllUsers() throws TaskJdbcException {
        String hqlSelect = """
                from User
                """;

        SessionFactory factory = Util_Hibernate.getSessionFactoryToMySQL();
        Session session = factory.getCurrentSession();

        //---------------------------//
        session.beginTransaction();
        Query<User> query = session.createQuery(hqlSelect, User.class);
//        query.setFetchSize(100);
        List<User> user = query.list();
        session.getTransaction().commit();
        //---------------------------//

        System.out.println("Выгрузка всех пользователей завершена!");
        System.out.println("--------------------------------------------");
        return user;
    }

    @Override
    public void cleanUsersTable() throws TaskJdbcException {
        String hqlDelete = """
                DELETE User
                """;

        SessionFactory factory = Util_Hibernate.getSessionFactoryToMySQL();
        Session session = factory.getCurrentSession();

        //---------------------------//
        session.beginTransaction();
        Query query = session.createQuery(hqlDelete);
        int tempLines = query.executeUpdate();
        session.getTransaction().commit();
        //---------------------------//

        if(tempLines == 0) {
            System.out.println(String.format("Не удалось удалить записи из таблицы User! Количество обработанных записей=%d", tempLines));
            System.out.println("--------------------------------------------");
        } else {
            System.out.println(String.format("Из БД удалено количество записей=%d", tempLines));
            System.out.println("--------------------------------------------");
        }

    }
}
