/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.dao;

import vn.aptech.quanlybanhang.entities.Employee;

/**
 *
 * @author anhnbt
 */
public interface AuthDAO {
    Employee login(String username, String password);
    
    Employee findById(int id);
}
