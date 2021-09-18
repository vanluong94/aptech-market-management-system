/*
 * Java 2 Practical - HaNoi Aptech
 */
package vn.aptech.quanlybanhang.utilities;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class I18n {

    private static ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", Locale.getDefault());

    public static void setMessages(ResourceBundle messages) {
        I18n.messages = messages;
    }

    public static String getMessage(String key, Object... args) {
        MessageFormat formatter = new MessageFormat("");
        formatter.applyPattern(messages.getString(key).replaceAll("'", "''"));
        return formatter.format(args);
    }
    
    public static void print(String key, Object... args) {
        System.out.println(getMessage(key, args));
    }
    
    public static String getEntityMessage(String entity, String msgKey) {
        return getEntityMessage(entity, msgKey, false);
    }
    
    public static void printEntityMessage(String entity, String msgKey) {
        printEntityMessage(entity, msgKey, false);
    }
    
    public static String getEntityMessage(String entity, String msgKey, boolean usePlural) {
        return getMessage(msgKey, getMessage(entity + ".label." + (usePlural ? "plural" : "singular")));
    }
    
    public static void printEntityMessage(String entity, String msgKey, boolean usePlural) {
        System.out.println(getEntityMessage(entity, msgKey, usePlural));
    }
}
