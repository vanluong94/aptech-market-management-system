/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.util.List;
import vn.aptech.quanlybanhang.dao.CustomerDAO;
import vn.aptech.quanlybanhang.dao.CustomerDAOImpl;
import vn.aptech.quanlybanhang.entities.Customer;
import vn.aptech.quanlybanhang.utilities.I18n;
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
    public boolean create(Customer object) throws Exception {
        if (object == null) {
            throw new Exception(I18n.getMessage("app.error.object.null"));

        }
        return customerDAO.create(object);
    }

    @Override
    public boolean update(Customer object) throws Exception {
        return customerDAO.update(object);
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        if (id < 1) {
            throw new Exception(I18n.getMessage("input.invalidID"));
        }
        return customerDAO.deleteById(id);
    }

    @Override
    public Customer findById(int id) throws Exception {
        if (id < 1) {
            throw new Exception(I18n.getMessage("input.invalidID"));
        }
        return customerDAO.findById(id);
    }

    @Override
    public List<Customer> findAll() throws Exception {
        return this.customerDAO.findAll();
    }

    @Override
    public PaginatedResults<Customer> select(int page) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer findByPhone(String phone) throws Exception {
        return this.customerDAO.findByPhone(phone);
    }

}
