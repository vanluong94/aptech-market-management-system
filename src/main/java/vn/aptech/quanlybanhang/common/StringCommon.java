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
    
    private static final Locale localeVN = new Locale("vi", "VN");
    
    public static boolean isNullOrBlank(String str) {
        return str == null || str.trim().equals("");
    }

    public static String convertDoubleToVND(double input) {
        return NumberFormat.getCurrencyInstance(localeVN).format(input);
    }

    public static String formatNumberCommas(int num) {
        return String.format("%,d", num);
    }

    public static String safeNullObject(String str) {
        if (isNullOrBlank(str)) {
            return "-";
        }
        return str;
    }
}
