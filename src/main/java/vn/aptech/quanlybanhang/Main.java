/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang;

import java.sql.SQLException;
import vn.aptech.quanlybanhang.menu.MainMenu;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.utilities.DBConnection;

/**
 *
 * @author vanluong
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            /* App start */
            
            DBConnection.getConnection();
            DBConnection.showTables();
//            Scanner scanner = new Scanner(System.in);
//            MainMenu mainMenu = new MainMenu();
//            mainMenu.start(scanner);
            
            DBConnection.closeConnection(); // connection should be closed to avoid database connection sleeps on server.
            /* App close */
            
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

