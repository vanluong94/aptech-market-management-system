/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.common;

import java.util.Objects;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ValidateCommon {
    public static void validateNullObject(Object object, String objectName) {
        if (object instanceof String) {
            if (StringCommon.isNullOrBlank(String.valueOf(object)))
                throw new CommonException(Response.MISSING_PARAM, MessageCommon.getMessage(MessageContent.MISSING_PARAM, objectName));
        } else if (Objects.isNull(object))
            throw new CommonException(Response.MISSING_PARAM, MessageCommon.getMessage(MessageContent.MISSING_PARAM, objectName));
    }
}
