/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang;

import java.util.Locale;
import java.util.ResourceBundle;
import vn.aptech.quanlybanhang.menu.Breadcrumb;
import vn.aptech.quanlybanhang.pages.AuthPage;
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
            init(args);
            start();
        } finally {
//            DBConnection.maybeCloseConnection();
            System.out.println("Bye bye...");
        }
    }
    
    public static void start(){
        Breadcrumb.reset();
        AuthPage authPage = new AuthPage();
        authPage.start();
    }
    
    public static void init(String[] args) {
        String language = null;
        String country = null;
        if (args.length == 2) {
//            language = args[0];
//            country = args[1];
            language = "en";
            country = "US";
        } else {
            language = "vi";
            country = "VN";
        }
        Locale.setDefault(new Locale(language, country));
    }
}
