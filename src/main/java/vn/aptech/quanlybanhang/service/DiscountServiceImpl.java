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
import vn.aptech.quanlybanhang.entities.ProductDiscount;
import vn.aptech.quanlybanhang.utilities.I18n;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

public class DiscountServiceImpl implements DiscountService {

    private final DiscountDAO discountDAO;

    public DiscountServiceImpl() {
        this.discountDAO = new DiscountDAOImpl();
    }

    @Override
    public boolean create(Discount object) throws SQLException, Exception {
        if (object == null) {
            throw new Exception(I18n.getMessage("app.error.object.null"));
        }
        return discountDAO.create(object);
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        if (id < 1) {
            try {
                throw new Exception(I18n.getMessage("input.invalidID"));
            } catch (Exception ex) {
                Logger.getLogger(DiscountServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return discountDAO.deleteById(id);
    }

    @Override
    public Discount findById(int id) throws SQLException, Exception {
        if (id < 1) {
            throw new Exception(I18n.getMessage("input.invalidID"));
        }
        return discountDAO.findById(id);
    }

    @Override
    public List<Discount> findAll() throws SQLException {
        return discountDAO.findAll();
    }

    @Override
    public boolean update(Discount object) throws SQLException, Exception {
        return this.discountDAO.update(object);
    }

    @Override
    public PaginatedResults<Discount> select(int page) throws SQLException, Exception {
        return this.discountDAO.select(page);
    }

    @Override
    public List<ProductDiscount> getDiscountProducts(Discount discount) {
        return this.discountDAO.getDiscountProducts(discount);
    }

    @Override
    public boolean deleteDiscountProduct(ProductDiscount dProduct) {
        return this.discountDAO.deleteDiscountProduct(dProduct);
    }

    @Override
    public boolean createDiscountProduct(ProductDiscount dProduct) {
        return this.discountDAO.createDiscountProduct(dProduct);
    }

    @Override
    public ProductDiscount findOverlapDiscountProduct(ProductDiscount dProduct) {
        return this.discountDAO.findOverlapDiscountProduct(dProduct);
    }

}
