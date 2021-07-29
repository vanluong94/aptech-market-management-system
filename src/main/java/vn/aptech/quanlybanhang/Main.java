/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.menu.MainMenu;
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
            MainMenu mainMenu = new MainMenu();
            mainMenu.start();
        } finally {
            DBConnection.maybeCloseConnection();
        }
    }
}
