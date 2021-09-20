/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import vn.aptech.quanlybanhang.common.CommonException;
import vn.aptech.quanlybanhang.common.Response;
import vn.aptech.quanlybanhang.common.ValidateCommon;
import vn.aptech.quanlybanhang.dao.AuthDAO;
import vn.aptech.quanlybanhang.dao.AuthDAOImpl;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.utilities.I18n;
import vn.aptech.quanlybanhang.utilities.Md5;

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
    public Employee login(String username, String password) {
        if (username == null || password == null) {
            throw new CommonException(I18n.getMessage("page.auth.login.required"));
        }
        if (!ValidateCommon.isValidUsername(username)) {
            throw new CommonException(I18n.getMessage("employee.error.username.invalid"));
        }
        Employee employee = authDAO.login(username, Md5.encode(password));
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
