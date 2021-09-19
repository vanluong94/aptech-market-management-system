/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import vn.aptech.quanlybanhang.dao.AuthDAO;
import vn.aptech.quanlybanhang.dao.AuthDAOImpl;
import vn.aptech.quanlybanhang.entities.Employee;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @author vanluong
 */
public class AuthServiceImpl implements AuthService {

    private final AuthDAO authDAO;

    private static Employee currentEmployee;

    public AuthServiceImpl() {
        this.authDAO = new AuthDAOImpl();
    }

    @Override
    public Employee login(Employee emp) {

        Employee employee = authDAO.login(emp.getUsername(), emp.getPassword());

        if (employee != null) {
            setCurrentEmployee(employee);
        }

        return employee;
    }

    public static void logout() {
        setCurrentEmployee(null);
    }

    public static Employee getCurrentEmployee() {
        return currentEmployee;
    }

    private static void setCurrentEmployee(Employee emp) {
        currentEmployee = emp;
    }

    public static boolean isLoggedIn() {
        return currentEmployee != null;
    }

}
