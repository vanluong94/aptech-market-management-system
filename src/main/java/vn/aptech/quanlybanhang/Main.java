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
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

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
            System.out.println(I18n.getMessage("msg.bye"));
        }
    }

    public static void start() {
        Breadcrumb.reset();
        AuthPage authPage = new AuthPage();
        authPage.start();
    }

    public static void init() {
        String language;
        String country;
        String choice = AppScanner.scanStringWithMessage(I18n.getMessage("msg.choice.lang"), true);
        if (choice.equalsIgnoreCase("en")) {
            language = "en";
            country = "US";
        } else {
            language = "vi";
            country = "VN";
        }
        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", new Locale(language, country));
        I18n.setMessages(messages);
    }
}
