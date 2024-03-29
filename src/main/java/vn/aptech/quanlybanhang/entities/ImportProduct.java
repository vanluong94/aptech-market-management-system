/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import vn.aptech.quanlybanhang.common.StringCommon;
import vn.aptech.quanlybanhang.constant.Constant;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ImportProduct {

    private int id;
    private Supplier supplier;
    private Product product;
    private Employee employee;
    private int quantity;
    private double price;
    private Date createdAt;

    public ImportProduct() {
        this.supplier = new Supplier();
        this.product = new Product();
        this.employee = new Employee();
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
     * @return the supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getQtyString() {
        return StringCommon.formatNumberCommas(quantity);
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPriceString() {
        return StringCommon.convertDoubleToVND(price);
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAtString() {
        SimpleDateFormat SDFormat = new SimpleDateFormat(Constant.DATE_TIME_SIMPLE_FORMAT);
        return SDFormat.format(createdAt);
    }

}
