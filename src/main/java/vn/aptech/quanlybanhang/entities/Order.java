/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class Order extends BaseEntity {

    private int id;
    private double amount;
    private Date orderDate;
    private Customer customer;
    private Employee employee;
    private List<OrderItem> orderItems;

    public Order() {
        this.customer = new Customer();
        this.employee = new Employee();
        this.orderItems = new ArrayList<>();
        this.orderDate = new Date();
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
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
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
}
