/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.common.StringCommon;
import vn.aptech.quanlybanhang.constant.Constant;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class Order extends BaseEntity {

    private int id;
    private double amount;
    private Timestamp orderDate;
    private Customer customer;
    private Employee employee;
    private List<OrderItem> orderItems;

    public Order() {
        this.customer = new Customer();
        this.employee = new Employee();
        this.orderItems = new ArrayList<>();
        this.orderDate = new Timestamp(new java.util.Date().getTime());
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
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the orderDate
     */
    public Timestamp getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     *
     * @param employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @return the orderItems
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "[ID] " + getId() + "\n"
                + "[date] " + orderDate + "\n";
    }

    public String getAmountString() {
        return StringCommon.convertDoubleToVND(this.amount);
    }

    public String getDatetimeString() {
        SimpleDateFormat SDFormat = new SimpleDateFormat(Constant.DATE_TIME_SIMPLE_FORMAT);
        return SDFormat.format(this.orderDate);
    }
}
