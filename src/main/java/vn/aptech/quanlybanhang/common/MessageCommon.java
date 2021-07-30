/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.common;

import java.text.MessageFormat;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class MessageCommon {
    public static String getMessage(String msgPattern, String param) {
        if (!StringCommon.isNullOrBlank(msgPattern) && !StringCommon.isNullOrBlank(param)) {
            Object[] a = {param};
            msgPattern = MessageFormat.format(msgPattern, a);
        }
        return msgPattern;
    }
}
