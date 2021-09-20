/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.dao;

import java.util.List;
import vn.aptech.quanlybanhang.entities.Employee;

/**
 *
 * @author Admin
 */
public interface EmployeeDAO extends BaseDAO<Employee> {

    boolean updateById(Employee object, int id) throws Exception;

    Employee findByUsernameAndPassword(String username, String password) throws Exception;

    List<Employee> findByNameEmployee(String username) throws Exception;
    
    boolean existsByUsername(String username) throws Exception;
    
    boolean empHasData(Employee emp) throws Exception;
}
