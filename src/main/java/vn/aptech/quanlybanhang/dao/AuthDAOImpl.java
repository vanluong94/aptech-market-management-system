/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;

public class AuthDAOImpl implements AuthDAO {

    @Override
    public Employee login(String username, String password) {
        Employee emp = null;
        try {
            EmployeeService empService = new EmployeeServiceImpl();
            emp = empService.findByUsernameAndPassword(username, password);
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return emp;
    }

    @Override
    public Employee findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
