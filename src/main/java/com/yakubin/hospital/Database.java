package com.yakubin.hospital;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static Connection connectDB() {

        try {

            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "password123");
            return connection;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
