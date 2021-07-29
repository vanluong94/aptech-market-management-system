/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author anhnbt
 * @author vanluong
 */
public class DBConnection {
    private final static String DRIVER          = "com.mysql.cj.jdbc.Driver";
    private final static String serverName      = "35.247.137.54";
    private final static String port            = "3306";
    private final static String databaseName    = "aptech_java_project";
    private final static String user            = "aptech_participant";
    private final static String password        = "tT2uOgleWf0n";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3307/quanlysieuthi?user=lab&password=";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = String.format("jdbc:mysql://%s:%s/%s", serverName, port, databaseName);
        Class.forName(DRIVER);
        System.out.println("Connecting to MySQL...");
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to database.");
        return conn;
    }

    public static void showTables() throws SQLException, ClassNotFoundException {

        Statement st = getConnection().createStatement();
        ResultSet tables = st.executeQuery("SHOW TABLES");

        if (tables.next()) {
            System.out.println("\nDatabase tables:");

            do{
                System.out.println("   + " + tables.getString(1));
            }while (tables.next());
        } else {
            System.out.println("No tables found.");
        }

    }

}
