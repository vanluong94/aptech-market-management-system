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


public class SupplierServiceImpl implements SupplierService {

    private final SupplierDAO supplierDAO;

    public SupplierServiceImpl() {
        this.supplierDAO = new SupplierDAOImpl();
    }
    
    @Override
    public boolean saveOrUpdate(Supplier object) throws SQLException, Exception {
        if (object == null) {
            throw new Exception ("Danh muc khong duoc de trong");
            
        }
        return supplierDAO.saveOrUpdate(object);
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        if (id < 1) {
            try {
                throw new Exception ("ID khong hop le!");
            } catch (Exception ex) {
                Logger.getLogger(SupplierServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return supplierDAO.deleteById(id);
    }

    @Override
    public Supplier findById(int id) throws SQLException, Exception {
        if (id < 1) {
            throw new Exception("ID khong hop le!");
        }
        return supplierDAO.findById(id);
    }

    @Override
    public List<Supplier> findAll() throws SQLException {
        return supplierDAO.findAll();
    }
    
}
