package vn.aptech.quanlybanhang.entities;

import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author VuxxLong
 */
public class Employee {

    private int employeeId;
    private String userName;
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
        this.userName = userName;
        this.password = password;
    }

    public Employee(int employeeId, String userName, String password) {
        this.employeeId = employeeId;
        this.userName = userName;
        this.password = password;
    }

    public Employee(String name, String address, String phone, Department department, String username, String password) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.department = department;
        this.userName = username;
        this.password = password;
    }

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
        System.out.println(I18n.getMessage("employee.id") + ": " + employeeId);
        System.out.println(I18n.getMessage("employee.username") + ": " + userName);
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
