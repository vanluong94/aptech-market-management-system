/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.service;

import vn.aptech.quanlybanhang.dao.AuthDAO;
import vn.aptech.quanlybanhang.dao.AuthDAOImpl;
import vn.aptech.quanlybanhang.entities.Employee;


public class AuthServiceImpl implements AuthService {
    
    private AuthDAO authDAO;

    public AuthServiceImpl() {
        this.authDAO = new AuthDAOImpl();
    }

    @Override
    public Employee login(Employee emp) {
        return authDAO.login(emp.getUserName(), emp.getPassword());
    }
    
}
