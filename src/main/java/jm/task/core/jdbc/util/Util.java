package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {

    private static String URL_KEY = GetProperties.getKey("db.url");
    private static String USER_KEY = GetProperties.getKey("db.user");
    private static String PASSWORD_KEY = GetProperties.getKey("db.password");

    public static Connection open(){
        try {
            Connection connection = DriverManager.getConnection(URL_KEY, USER_KEY, PASSWORD_KEY);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Util() {}
}
