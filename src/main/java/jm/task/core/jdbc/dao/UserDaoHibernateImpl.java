package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao{

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession()){
            session.beginTransaction();
            session.createNativeQuery("""
                CREATE TABLE users(
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(128),
                    lastname VARCHAR(128),
                    age TINYINT UNSIGNED
            )
            """).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession()){
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            User user = User.builder()
                    .name(name)
                    .lastname(lastName)
                    .age(age)
                    .build();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUser;
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            listUser = session.createQuery("FROM User", User.class).getResultList();
            session.getTransaction().commit();
        }
        return listUser;
    }



    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession()){
            session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
