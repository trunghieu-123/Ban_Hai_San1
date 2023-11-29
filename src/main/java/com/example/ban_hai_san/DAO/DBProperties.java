package com.example.ban_hai_san.DAO;

import java.io.IOException;
import java.util.Properties;

public class DBProperties {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(DBProperties.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static String getDbHost() {
        return properties.get("db.host").toString();
    }

    public static String getDbPort() {
        return properties.get("db.port").toString();
    }

    public static String getUsername() {
        return properties.get("db.username").toString();
    }

    public static String getPassword() {
        return properties.get("db.password").toString();
    }

    public static String getDbName() {
        return properties.get("db.databaseName").toString();
    }
}
