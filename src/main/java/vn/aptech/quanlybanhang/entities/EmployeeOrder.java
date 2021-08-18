/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class EmployeeOrder {

    private int id;
    private Employee employee;
    private Order order;

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
}
