/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class AuthDAOImpl implements AuthDAO {

    EmployeeService empService;

    public AuthDAOImpl() {
        this.empService = new EmployeeServiceImpl();
    }

    @Override
    public Employee login(String username, String password) {
        Employee emp = null;
        try {
            emp = empService.findByUsernameAndPassword(username, password);
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AuthDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return emp;
    }

    @Override
    public Employee findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
