/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

import java.util.Date;
import vn.aptech.quanlybanhang.common.DateCommon;
import vn.aptech.quanlybanhang.utilities.AppScanner;

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

    public ProductDiscount(){
        
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
        Date dateFrom = null;
        String dateFormatPattern = "dd/MM/yyyy HH:mm";
        do {
            String dateFromStr = AppScanner.scanStringWithMessage("Thời gian bắt đầu giảm giá [" + dateFormatPattern + "]: ");
            dateFrom = DateCommon.convertStringToDateByPattern(dateFromStr, dateFormatPattern);

            if (dateFrom == null) {
                System.out.println("Thời gian đã nhập không hợp lệ, vui lòng thử lại.");
            }
        } while (dateFrom == null);
        
        return dateFrom;
    }
    
    public Date scanEndDate() {
        Date dateTo = null;
        String dateFormatPattern = "dd/MM/yyyy HH:mm";
        
        do {
            String dateToStr = AppScanner.scanStringWithMessage("Thời gian kết thúc giảm giá [" + dateFormatPattern + "]: ");
            dateTo = DateCommon.convertStringToDateByPattern(dateToStr, dateFormatPattern);

            if (dateTo == null) {
                System.out.println("Thời gian đã nhập không hợp lệ, vui lòng thử lại.");
            } else if (this.startDate != null && dateTo.compareTo(this.startDate) <= 0) {
                System.out.println("Thời gian kết thúc giảm giá không được sớm hơn thời gian bắt đầu giảm giá");
                dateTo = null; // reset to repeat
            }
        } while (dateTo == null);
        
        return dateTo;
    }

    public float scanDiscountPercentage() {
        float discountPercent = 0;
        while (discountPercent < 1) {
            discountPercent = AppScanner.scanFloatWithMessage("Nhập % giảm giá cho sản phẩm: ");
            
            if (discountPercent < 1) {
                System.out.println("Giá trị % giảm giá cho sản phẩm phải >= 1");
            }
        }
        return discountPercent;
    }

}
