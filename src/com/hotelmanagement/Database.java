package com.hotelmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Veritabanına bağlantı sağladığımız class
public class Database {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Acente";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "hakanka1";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
