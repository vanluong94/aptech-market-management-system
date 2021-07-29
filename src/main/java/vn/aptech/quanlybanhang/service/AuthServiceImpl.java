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
    
    private static Employee currentEmployee;

    public AuthServiceImpl() {
        this.authDAO = new AuthDAOImpl();
    }

    @Override
    public Employee login(Employee emp) {
        
        Employee employee = authDAO.login(emp.getUserName(), emp.getPassword());
        
        if(employee != null){
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
    
    public static boolean isLoggedIn(){
        return getCurrentEmployee() != null;
    }
    
}
