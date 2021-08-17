/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.dao.SupplierDAO;
import vn.aptech.quanlybanhang.dao.SupplierDAOImpl;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

public class SupplierServiceImpl implements SupplierService {

    private final SupplierDAO supplierDAO;

    public SupplierServiceImpl() {
        this.supplierDAO = new SupplierDAOImpl();
    }

    @Override
    public boolean create(Supplier object) throws SQLException, Exception {
        if (object == null) {
            throw new Exception("Danh mục không được để trống");

        }
        return supplierDAO.create(object);
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        if (id < 1) {
            try {
                throw new Exception("ID không hợp lệ!");
            } catch (Exception ex) {
                Logger.getLogger(SupplierServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return supplierDAO.deleteById(id);
    }

    @Override
    public Supplier findById(int id) throws SQLException, Exception {
        if (id < 1) {
            throw new Exception("ID không hợp lệ!");
        }
        return supplierDAO.findById(id);
    }

    @Override
    public List<Supplier> findAll() throws SQLException {
        return supplierDAO.findAll();
    }

    @Override
    public boolean update(Supplier id) throws SQLException {
        return this.supplierDAO.update(id);
    }

    @Override
    public List<Supplier> searchByName(String name) throws SQLException, ClassNotFoundException {
        return this.supplierDAO.searchByName(name);
    }

    @Override
    public PaginatedResults<Supplier> select(int page) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
