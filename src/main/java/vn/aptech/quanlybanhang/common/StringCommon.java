/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.common;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class StringCommon {

    public static boolean isNullOrBlank(String str) {
        return str == null || str.trim().equals("");
    }

    public static String convertDoubleToVND(double input) {
        return NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(input);
    }

    public static String safeNullObject(String str) {
        if (isNullOrBlank(str)) {
            return "-";
        }
        return str;
    }
}
