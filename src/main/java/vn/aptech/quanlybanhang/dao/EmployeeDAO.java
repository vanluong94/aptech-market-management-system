/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.entities.Product;

/**
 *
 * @author Admin
 */
public interface EmployeeDAO extends BaseDAO<Employee> {

    boolean updateById(Employee object, int id) throws SQLException;

    Employee findByUsernameAndPassword(String username, String password) throws SQLException;
    List<Employee> findByNameEmployee(String username)throws SQLException;
}
