/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.constant;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public final class Constant {

    public static enum STATUS {
        ACTIVE,
        INACTIVE
    }

    public static final int PER_PAGE = 10;

    public static final String DATE_TIMESTAMP_FORMAT = "HH:mm:ss.SSS dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT = "HH:mm:ss dd/MM/yyyy";
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_TIME_SIMPLE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static final String INVOICE_TEMPLATE_FILE = "invoice.jrxml";
    public static final String INVOICE_EXPORT_FILE = "invoice.xlsx";
}
