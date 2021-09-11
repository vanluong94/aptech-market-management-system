/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.dao.CustomerDAO;
import vn.aptech.quanlybanhang.dao.CustomerDAOImpl;
import vn.aptech.quanlybanhang.entities.Customer;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerServiceImpl() {
        this.customerDAO = new CustomerDAOImpl();
    }

    @Override
    public boolean create(Customer object) throws SQLException, Exception {
        if (object == null) {
            throw new Exception("Doi tuong không được để trống");

        }
        return customerDAO.create(object);
    }

    @Override
    public boolean update(Customer object) throws SQLException, Exception {
        return customerDAO.update(object);
    }

    @Override
    public boolean deleteById(int id) throws SQLException, Exception {
        if (id < 1) {
            throw new Exception("ID không hợp lệ!");
        }
        return customerDAO.deleteById(id);
    }

    @Override
    public Customer findById(int id) throws SQLException, Exception {
        if (id < 1) {
            throw new Exception("ID không hợp lệ!");
        }
        return customerDAO.findById(id);
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        return this.customerDAO.findAll();
    }

    @Override
    public PaginatedResults<Customer> select(int page) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer findByPhone(String phone) throws SQLException {
        return this.customerDAO.findByPhone(phone);
    }

}
