/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

import vn.aptech.quanlybanhang.common.StringCommon;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class OrderItem extends BaseEntity {

    private int id;
    private Order order;
    private Product product;
    private String productName;
    private int quantity;
    private double productPrice;
    private double discountPrice;
    private ProductDiscount discount;
    private double totalPrice;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderItem() {
        this.discount = new ProductDiscount();
    }

    public OrderItem(Order order, int id, String productName, int quantity, double productPrice, double discountPrice) {
        this.id = id;
        this.order = order;
        this.productName = productName;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;

        this.discount = new ProductDiscount();
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
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
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
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
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

    /**
     * @return the productPrice
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * @return the discountPrice
     */
    public double getDiscountPrice() {
        return discountPrice;
    }

    /**
     * @param discountPrice the discountPrice to set
     */
    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public ProductDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(ProductDiscount discount) {
        this.discount = discount;
    }

    public double getProductFinalPrice() {
        return this.discountPrice > 0 ? this.discountPrice : this.productPrice;
    }

    public String getProductFinalPriceString() {
        return StringCommon.convertDoubleToVND(this.getProductFinalPrice());
    }

    public double getTotal() {
        this.totalPrice = this.getProductFinalPrice() * this.getQuantity();
        return totalPrice;
    }

    public String getTotalString() {
        return StringCommon.convertDoubleToVND(this.getTotal());
    }

    public String getProductPriceString() {
        return StringCommon.convertDoubleToVND(this.productPrice);
    }

    public String getDiscountPriceString() {
        return StringCommon.convertDoubleToVND(this.discountPrice);
    }

}
