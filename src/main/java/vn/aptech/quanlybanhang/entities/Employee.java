package vn.aptech.quanlybanhang.entities;

import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author VuxxLong
 */
public class Employee {

    private int id;
    private String username;
    private String password;
    private String address;
    private String name;
    private String phone;
    private Department department;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee() {
    }

    public Employee(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    public Employee(int employeeId, String userName, String password) {
        this.id = employeeId;
        this.username = userName;
        this.password = password;
    }

    public Employee(String name, String address, String phone, Department department, String username, String password) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.department = department;
        this.username = username;
        this.password = password;
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void showOne() {
        System.out.println("====== " + I18n.getEntityMessage("employee", "entity.title.detail") + " ======");
        System.out.println(I18n.getMessage("employee.name") + ": " + name);
        System.out.println(I18n.getMessage("employee.addr") + ": " + address);
        System.out.println(I18n.getMessage("employee.phone") + ": " + phone);
        System.out.println(I18n.getMessage("employee.dept") + ": " + department);
        System.out.println(I18n.getMessage("employee.id") + ": " + id);
        System.out.println(I18n.getMessage("employee.username") + ": " + username);
        System.out.println("=================================");
    }

    public boolean isAdmin() {
        return this.getDepartment().equals(Department.ROLE_ADMIN);
    }

    public boolean isInventory() {
        return this.getDepartment().equals(Department.ROLE_EMPLOYEE_INVENTORY);
    }

    public boolean isCashier() {
        return this.getDepartment().equals(Department.ROLE_EMPLOYEE_CASHER);
    }
}
