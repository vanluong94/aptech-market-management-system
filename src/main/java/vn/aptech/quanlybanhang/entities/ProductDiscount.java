/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

import java.util.Date;
import org.apache.commons.validator.GenericValidator;
import vn.aptech.quanlybanhang.common.DateCommon;
import vn.aptech.quanlybanhang.constant.Constant;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 *
 */
public class ProductDiscount {

    private int id;
    private int discountId;
    private Product product;
    private float discountPercentage;
    private Date startDate;
    private Date endDate;

    public ProductDiscount() {

    }

    public ProductDiscount(int id, int discountId, Product product, float discount, Date startDate, Date endDate) {
        this.id = id;
        this.discountId = discountId;
        this.product = product;
        this.discountPercentage = discount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the discountId
     */
    public int getDiscountId() {
        return discountId;
    }

    /**
     * @param discountId the discountId to set
     */
    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the discountPercentage
     */
    public float getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * @param discount the discountPercentage to set
     */
    public void setDiscountPercentage(float discount) {
        this.discountPercentage = discount;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date scanStartDate() {
        Date fromDate = null;
        Date toDay = DateCommon.getToday();
        do {
            String fromDateStr = AppScanner.scanStringWithMessage(I18n.getMessage("discount.scan.datetime.start", Constant.DATE_TIME_FORMAT_MINUTES));
            fromDate = DateCommon.convertStringToDateByPattern(fromDateStr, Constant.DATE_TIME_FORMAT_MINUTES);
            if (!GenericValidator.isDate(fromDateStr, Constant.DATE_TIME_FORMAT_MINUTES, true)) {
                fromDate = null;
                I18n.print("discount.error.invalidDatetime");
            } else if (toDay.after(fromDate)) {
                fromDate = null;
                I18n.print("discount.error.compareGreaterThanCurrentDate", I18n.getMessage("startDateTime"));
            }
        } while (fromDate == null);

        return fromDate;
    }

    public Date scanEndDate(Date fromDate) {
        Date toDate = null;
        Date toDay = DateCommon.getToday();
        do {
            String toDateStr = AppScanner.scanStringWithMessage(I18n.getMessage("discount.scan.datetime.end", Constant.DATE_TIME_FORMAT_MINUTES));
            toDate = DateCommon.convertStringToDateByPattern(toDateStr, Constant.DATE_TIME_FORMAT_MINUTES);
            if (!GenericValidator.isDate(toDateStr, Constant.DATE_TIME_FORMAT_MINUTES, true)) {
                toDate = null;
                I18n.print("order.error.date.invalid");
            } else if (toDay.after(toDate)) {
                toDate = null;
                I18n.print("discount.error.compareGreaterThanCurrentDate", I18n.getMessage("endDateTime"));
            } else if (toDate.before(fromDate)) {
                toDate = null;
                I18n.print("discount.error.invalidTimeRange");
            }
        } while (toDate == null);

        return toDate;
    }

    public float scanDiscountPercentage() {
        float discountPercent = 0;
        while (discountPercent < 1) {
            discountPercent = AppScanner.scanFloatWithi18Message("discount.scan.discountPercentage");

            if (discountPercent < 1) {
                I18n.print("discount.error.invalidPercentage");
            }
        }
        return discountPercent;
    }

}
