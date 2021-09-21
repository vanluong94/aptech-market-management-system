/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import vn.aptech.quanlybanhang.menu.Breadcrumb;
import vn.aptech.quanlybanhang.pages.AuthPage;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.Config;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author vanluong
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
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
            I18n.print("msg.bye");
        }
    }

    public static void start() {
        Breadcrumb.reset();
        AuthPage authPage = new AuthPage();
        authPage.start();
    }

    public static void init() {
        /**
         * initialize configuration
         */
        try {
            Config.getInstance();
        } catch (IOException ex) {
            I18n.print("app.error.configFile");
            I18n.print("msg.bye");
            System.exit(0);
        }
        
        /**
         * initialize language
         */
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
