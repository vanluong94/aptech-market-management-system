/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.ValidateCommon;
import vn.aptech.quanlybanhang.dao.ProductDAO;
import vn.aptech.quanlybanhang.dao.ProductDAOImpl;
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.exception.InputInvalidException;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl() {
        productDAO = new ProductDAOImpl();
    }

    @Override
    public boolean create(Product product) throws SQLException, Exception {
        this.validateProductData(product);
        return this.productDAO.create(product);
    }

    @Override
    public boolean update(Product product) throws SQLException, Exception {
        this.validateProductData(product);
        return this.productDAO.update(product);
    }

    @Override
    public boolean deleteById(int id) throws SQLException, Exception {
        if (id<1) {
            try {
                throw new Exception ("ID không hợp lệ!");
            } catch (Exception e) {
                  Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return this.productDAO.deleteById(id);
    }

    @Override
    public Product findById(int id) throws SQLException, Exception {
        ValidateCommon.validateNullObject(id, "id");
//        Product product = this.productDAO.findById(id);
//        if (product == null) {
//            throw new CommonException(Response.OBJECT_NOT_FOUND, MessageCommon.getMessage(MessageContent.OBJECT_NOT_FOUND, "id"));
//        }
        return this.productDAO.findById(id);
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return this.productDAO.findAll();
    }

    public void validateProductData(Product product) throws InputInvalidException {
        
        ValidateCommon.validateNullObject(product.getBrand(), "brand");
        ValidateCommon.validateNullObject(product.getCategory(), "category");
        ValidateCommon.validateNullObject(product.getEmployee(), "employee");
        ValidateCommon.validateNullObject(product.getName(), "name");
        ValidateCommon.validateNullObject(product.getPrice(), "price");
        ValidateCommon.validateNullObject(product.getQuantityInStock(), "quantityInStock");
        
        if (product.getName().trim().length() == 0) {
            throw new InputInvalidException("Tên Sản phẩm không được bỏ trống");
        }

        if (product.getQuantityInStock() == 0) {
            throw new InputInvalidException("Hãy nhập số lượng tồn kho cho Sản phẩm");
        }

        if (product.getPrice() <= 0) {
            throw new InputInvalidException("Hãy nhập giá hợp lệ cho Sản phẩm");
        }
    }

    @Override
    public PaginatedResults<Product> findByCategoryId(int page,int id) throws SQLException {
        ValidateCommon.validateNullObject(id, "id");
        return productDAO.findByCategoryId(page,id);
    }

    @Override
    public PaginatedResults<Product> findByName(int page,String name) throws SQLException {
        ValidateCommon.validateNullObject(name, "name");
//        List<Product> products = productDAO.findByName(name);
//        if (ArrayListCommon.isNullOrEmpty(products)) {
//            throw new CommonException(Response.DATA_NOT_FOUND);
//        }
        return productDAO.findByName(page,name);
    }

    @Override
    public PaginatedResults<Product> select(int page) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaginatedResults<Product> selectOutOfStock(int page) throws SQLException {
        return productDAO.selectOutOfStock(page);
    }

    @Override
    public PaginatedResults<Product> findAllByOrderByUnitsOnOrderDesc(int page, String fromDate, String toDate) throws SQLException {
        return productDAO.findAllByOrderByUnitsOnOrderDesc(page, fromDate, toDate);
    }

}
