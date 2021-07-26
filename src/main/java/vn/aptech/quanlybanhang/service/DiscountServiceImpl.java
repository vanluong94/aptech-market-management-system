/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.dao.DiscountDAO;
import vn.aptech.quanlybanhang.dao.DiscountDAOImpl;
import vn.aptech.quanlybanhang.entities.Discount;


public class DiscountServiceImpl implements DiscountService {
    
    private final DiscountDAO discountDAO;
            
    public DiscountServiceImpl(){
        this.discountDAO = new DiscountDAOImpl();
    }
    
    
    @Override
    public boolean create(Discount object) throws SQLException, Exception {
        if (object == null) {
            throw new Exception ("Ten chuong trinh khong duoc de trong!");
        }
        return discountDAO.create(object);
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        if (id < 1) {
            try {
                throw new Exception ("ID khong hop le!");
            } catch (Exception ex) {
                Logger.getLogger(DiscountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return discountDAO.deleteById(id);
    }

    @Override
    public Discount findById(int id) throws SQLException, Exception {
        if (id < 1) {
            throw new Exception("ID khong hop le!");
        }
        return discountDAO.findById(id);
    }

    @Override
    public List<Discount> findAll() throws SQLException {
        return discountDAO.findAll();
    }

    public boolean update(Discount object) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
