package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    static void main() {
        try (Session session = Util.getSession()){
            session.beginTransaction();
            User user = User.builder()
                    .name("Andrey")
                    .lastname("Pushkov")
                    .age((byte) 23)
                    .build();
            session.persist(user);
            session.getTransaction().commit();
        }
    }
}