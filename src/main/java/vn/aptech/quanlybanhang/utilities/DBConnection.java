/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author anhnbt
 */
public class DBConnection {

    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
//    private final static String serverName = "localhost";
//    private final static String port = "3306";
//    private final static String databaseName = "quanlybanhang";
//    private final static String user = "lab";
//    private final static String password = "";
//    private final static String url = "jdbc:mysql://localhost:3306/quanlybanhang";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/quanlybanhang?user=lab&password=";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        System.out.println("Connecting to MySQL...");
        Connection conn = DriverManager.getConnection(CONNECTION_URL);
        System.out.println("Connected to database.");
        return conn;
    }

}
