package com.example.ban_hai_san.DAO;

import com.example.ban_hai_san.Model.User;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class JDBIConnector {
    private static Jdbi jdbi;

    private static void makeConnection() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://" + DBProperties.getDbHost() + ":" + DBProperties.getDbPort() + "/" + DBProperties.getDbName());
        mysqlDataSource.setUser(DBProperties.getUsername());
        mysqlDataSource.setPassword(DBProperties.getPassword());

        try {
            mysqlDataSource.setUseCompression(true);
            mysqlDataSource.setAutoReconnect(true);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException(sqlException);
        }

        jdbi = Jdbi.create(mysqlDataSource);
    }

    private JDBIConnector() {

    }

    public static Jdbi get() {
        if(jdbi == null)
            makeConnection();
        return jdbi;
    }

    public static void main(String[] args) {
        List<User> users = JDBIConnector.get().withHandle(handle -> {
            return handle.createQuery("select * from User").
                    mapToBean(User.class).stream().collect(Collectors.toList());
        });

        System.out.println(users);
    }
}
