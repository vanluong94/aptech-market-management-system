package vn.aptech.quanlybanhang.entities;

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
        System.out.println("====== Chi tiet nhan vien ======");
        System.out.println("Chi tiet nhan vien " + name + " :");
        System.out.println("Ho ten : " + name);
        System.out.println("Dia chi: " + address);
        System.out.println("So dien thoai: " + phone);
        System.out.println("Chuc vu : " + department);
        System.out.println("ID : " + employeeId);
        System.out.println("UserName : " + userName);
        System.out.println("Password : " + password);
        System.out.println("--------------------------------");
    }
}
