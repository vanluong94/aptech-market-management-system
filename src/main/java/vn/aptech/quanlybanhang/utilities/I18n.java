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

    private static final ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", Locale.getDefault());

    public static String translate(String key, Object... args) {
        MessageFormat formatter = new MessageFormat("");
        formatter.applyPattern(messages.getString(key).replaceAll("'", "''"));
        return formatter.format(args);
    }
}
