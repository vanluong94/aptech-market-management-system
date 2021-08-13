/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.dao.EmployeeDAO;
import vn.aptech.quanlybanhang.dao.EmployeeDAOImpl;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Admin
 */
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl() {
        this.employeeDAO = new EmployeeDAOImpl();
    }

    @Override
    public boolean create(Employee object) throws SQLException, Exception {
        if (object == null) {
            throw new Exception("Doi tuong khong duoc de trong");

        }
        return employeeDAO.create(object);
    }

    @Override
    public boolean update(Employee emp) throws SQLException, Exception {
        return employeeDAO.update(emp);
    }

    @Override
    public boolean deleteById(int id) throws SQLException, Exception {
        if (id < 1) {
            try {
                throw new Exception("ID khong hop le!");
            } catch (Exception ex) {
                Logger.getLogger(SupplierServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return employeeDAO.deleteById(id);
    }

    @Override
    public Employee findById(int id) throws SQLException, Exception {
        if (id < 1) {
            throw new Exception("ID khong hop le!");
        }
        return employeeDAO.findById(id);
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        return this.employeeDAO.findAll();
    }

    @Override
    public boolean updateById(Employee object, int id) throws SQLException {
        if (object == null) {
            try {
                throw new Exception("Doi tuong khong duoc de trong");
            } catch (Exception ex) {
                Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (id < 1) {
            try {
                throw new Exception("ID khong hop le!");
            } catch (Exception ex) {
                Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return employeeDAO.updateById(object, id);
    }

    @Override
    public Employee findByUsernameAndPassword(String username, String password) throws SQLException {
        if (username == null || password == null) {
            try {
                throw new Exception("Username va Password khong duoc de trong");
            } catch (Exception ex) {
                Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return employeeDAO.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<Employee> findByNameEmployee(String username) throws SQLException {
        if (username == null) {
            try {
                throw new Exception("Username khong duoc de trong");
            } catch (Exception ex) {
                Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return employeeDAO.findByNameEmployee(username);
    }

    @Override
    public PaginatedResults<Employee> select(int page) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
