/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.util.List;
import vn.aptech.quanlybanhang.entities.Employee;

/**
 *
 * @author Admin
 */
public interface EmployeeService extends BaseService<Employee> {

    boolean updateById(Employee object, int id) throws Exception;

    Employee findByUsernameAndPassword(String username, String password) throws Exception;

    List<Employee> findByNameEmployee(String username) throws Exception;

    boolean existsByUsername(String username) throws Exception;
    
    boolean empHasData(Employee emp) throws Exception;
}
