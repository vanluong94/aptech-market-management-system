/*
 *  Do an Java tai HaNoi Aptech
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

    public static Connection conn;

    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {

        try {
            if (conn == null || conn.isClosed()) {
                String url = String.format(
                        "jdbc:mysql://%s:%s/%s",
                        Config.get("db.host"),
                        Config.get("db.port"),
                        Config.get("db.database")
                );
                System.out.println("Connecting...");
                Class.forName(DRIVER);
                conn = DriverManager.getConnection(url, Config.get("db.user"), Config.get("db.password"));
            }
        } catch (SQLException | ClassNotFoundException  ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;

    }

    public static void maybeCloseConnection() {
        try {
            if (conn != null && conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failed to close DBConnection");
        }
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
