/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * @author anhnbt
 * @author vanluong
 */
public class DBConnection {
    private final static String DRIVER         = "com.mysql.cj.jdbc.Driver";
    private final static String SERVER         = "35.247.137.54";
    private final static String PORT           = "3306";
    private final static String DATABASE       = "aptech_java_project";
    private final static String USER           = "aptech_participant";
    private final static String PASSWORD       = "tT2uOgleWf0n";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3307/quanlysieuthi?user=lab&password=";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = String.format("jdbc:mysql://%s:%s/%s", SERVER, PORT, DATABASE);
            Class.forName(DRIVER);
//            System.out.println("Connecting to MySQL...");
            conn = DriverManager.getConnection(url, USER, PASSWORD);
//            System.out.println("Connected to database.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Exception when DBConnection.getConnection: " + e.getMessage());
        }
        return conn;
    }

    public static void showTables() throws SQLException, ClassNotFoundException {

        Statement st = getConnection().createStatement();
        ResultSet tables = st.executeQuery("SHOW TABLES");

        if (tables.next()) {
            System.out.println("\nDatabase tables:");

            do {
                System.out.println("   + " + tables.getString(1));
            } while (tables.next());
        } else {
            System.out.println("No tables found.");
        }

    }

}
