/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class Discount extends BaseEntity {

    private int id;
    private String name;
    private List<ProductDiscount> productDiscounts;
    private int productCount;

    public Discount() {
        this.productDiscounts = new ArrayList<>();
    }

    public Discount(String name) {
        this.name = name;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the productDiscounts
     */
    public List<ProductDiscount> getProductDiscounts() {
        return productDiscounts;
    }

    /**
     * @param productDiscounts the productDiscounts to set
     */
    public void setProductDiscounts(List<ProductDiscount> productDiscounts) {
        this.productDiscounts = productDiscounts;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public ProductDiscount findProductDiscount(int productId) {
        for (ProductDiscount pDiscount : this.getProductDiscounts()) {
            if (pDiscount.getProduct().getId() == productId) {
                return pDiscount;
            }
        }
        return null;
    }

}
