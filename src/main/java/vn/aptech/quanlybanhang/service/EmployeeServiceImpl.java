/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.dao.EmployeeDAO;
import vn.aptech.quanlybanhang.dao.EmployeeDAOImpl;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.utilities.I18n;
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
    public boolean create(Employee object) throws Exception {
        if (object == null) {
            throw new Exception(I18n.getMessage("app.error.object.null"));
        }
        return employeeDAO.create(object);
    }

    @Override
    public boolean update(Employee emp) throws Exception {
        return employeeDAO.update(emp);
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        if (id < 1) {
            throw new Exception(I18n.getMessage("input.invalidID"));
        }
        return employeeDAO.deleteById(id);
    }

    @Override
    public Employee findById(int id) throws Exception {
        if (id < 1) {
            throw new Exception(I18n.getMessage("input.invalidID"));
        }
        return employeeDAO.findById(id);
    }

    @Override
    public List<Employee> findAll() throws Exception {
        return this.employeeDAO.findAll();
    }

    @Override
    public boolean updateById(Employee object, int id) throws Exception {
        if (object == null) {
            throw new Exception(I18n.getMessage("app.error.object.null"));
        }
        if (id < 1) {
            try {
                throw new Exception(I18n.getMessage("input.invalidID"));
            } catch (Exception ex) {
                Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return employeeDAO.updateById(object, id);
    }

    @Override
    public Employee findByUsernameAndPassword(String username, String password) throws Exception {
        if (username == null || password == null) {
            throw new Exception("Username va Password không được để trống");

        }

        return employeeDAO.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<Employee> findByNameEmployee(String username) throws Exception {
        if (username == null) {
            throw new Exception("Username không được để trống");
        }

        return employeeDAO.findByNameEmployee(username);
    }

    @Override
    public PaginatedResults<Employee> select(int page) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
