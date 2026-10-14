package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class GetProperties {

    private static final Properties PROPERTIES  = new Properties();

    static {
        loadProperties();
    }

    public static void loadProperties(){
        InputStream inputStream = GetProperties.class.getClassLoader().getResourceAsStream("aplication.properties");
        try {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getKey(String key){
        return PROPERTIES.getProperty(key);
    }

    private GetProperties(){}

}
