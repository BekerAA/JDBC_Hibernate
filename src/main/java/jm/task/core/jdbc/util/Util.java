package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {
    //---------------------------------JDBC---------------------------------
    private static String URL_KEY = GetProperties.getKey("db.url");
    private static String USER_KEY = GetProperties.getKey("db.user");
    private static String PASSWORD_KEY = GetProperties.getKey("db.password");

    public static Connection openSessionJDBK(){
        try {
            Connection connection = DriverManager.getConnection(URL_KEY, USER_KEY, PASSWORD_KEY);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //---------------------------------HIBERNATE---------------------------------
    public static SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/tables");
        configuration.setProperty("hibernate.connection.username", "andrey");
        configuration.setProperty("hibernate.connection.password", "14555555");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        return configuration.buildSessionFactory();
    }

    public Util(){}
}
