
package vn.aptech.quanlybanhang.entities;

/**
 *
 * @author VuxxLong
 */
public class Employee {
    private int employeeId;
    private String userName;
    private String password;
    private String employee_add,employee_name,employee_phone,department;

    public String getEmployee_add() {
        return employee_add;
    }

    public void setEmployee_add(String employee_add) {
        this.employee_add = employee_add;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_phone() {
        return employee_phone;
    }

    public void setEmployee_phone(String employee_phone) {
        this.employee_phone = employee_phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
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
    public Employee(String employee_name, String employee_add, String employee_phone, String department, String username,String password ){
        this.employee_name = employee_name;
        this.employee_add = employee_add;
        this.employee_phone = employee_phone;
        this.department = department;
        this.userName = username;
        this.password = password;
    }
}
