/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

import vn.aptech.quanlybanhang.utilities.I18n;

/**
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class Customer {

    private int id;
    private String name;
    private String phone;
    private String address;
    private int discount;
    private int salePoint;
    private Employee employee;

    public Customer() {
        employee = new Employee();
    }

    public Customer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer(String name, String phone, String address, Employee employee) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.employee = employee;
    }

    public Customer(int id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
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
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getSalePoint() {

        return salePoint;
    }

    public void setSalePoint(int salePoint) {
        this.salePoint = salePoint;
    }

    public void showOne() {
        System.out.println("====== " + I18n.getEntityMessage("customer", "entity.title.detail") + " ======");
        System.out.println(I18n.getMessage("customer.name") + ": " + name);
        System.out.println(I18n.getMessage("customer.addr") + ": " + address);
        System.out.println(I18n.getMessage("customer.phone") + ": " + phone);
        System.out.println(I18n.getMessage("customer.id") + ": " + id);
        System.out.println(I18n.getMessage("customer.emp") + ": " + employee.getId());
        System.out.println("=================================");
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
