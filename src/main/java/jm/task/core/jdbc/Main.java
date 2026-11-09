package jm.task.core.jdbc;


import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    static void main() {

        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            System.out.println("-".repeat(100) + "\nПодключение к БД: УСПЕШНО!\n" + "-".repeat(100));

            session.getTransaction().commit();
        }
    }
}