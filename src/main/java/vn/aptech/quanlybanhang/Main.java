/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang;

import java.util.Locale;
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
            init();
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
    
    public static void init() {
        Locale.setDefault(new Locale("vi", "VN"));
    }
}
