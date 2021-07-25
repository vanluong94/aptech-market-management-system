/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public class Discount {
    private int discountId;
    private String discountName;

    public Discount() {
    }

    public Discount( String discountName) {
        this.discountName = discountName;
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

    /**
     * @return the discountName
     */
    public String getDiscountName() {
        return discountName;
    }

    /**
     * @param discountName the discountName to set
     */
    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    @Override
    public String toString() {
        return "Discount { " + "DiscountId = " + discountId + ", DiscountName = " + discountName + " } ";
    }
    
    
    
}
