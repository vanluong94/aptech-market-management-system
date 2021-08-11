/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.ValidateCommon;
import vn.aptech.quanlybanhang.dao.ProductDAO;
import vn.aptech.quanlybanhang.dao.ProductDAOImpl;
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
                throw new Exception ("ID khong hop le!");
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
            throw new InputInvalidException("Ten san pham khong duoc bo trong");
        }

        if (product.getQuantityInStock() == 0) {
            throw new InputInvalidException("Hay nhap So luong ton kho cho san pham");
        }

        if (product.getPrice() <= 0) {
            throw new InputInvalidException("Hay nnhap Gia hop le cho san pham");
        }
    }

    @Override
    public List<Product> findByCategoryId(int id) throws SQLException {
        ValidateCommon.validateNullObject(id, "id");
        return productDAO.findByCategoryId(id);
    }

    @Override
    public List<Product> findByName(String name) throws SQLException {
        ValidateCommon.validateNullObject(name, "name");
//        List<Product> products = productDAO.findByName(name);
//        if (ArrayListCommon.isNullOrEmpty(products)) {
//            throw new CommonException(Response.DATA_NOT_FOUND);
//        }
        return productDAO.findByName(name);
    }

    @Override
    public PaginatedResults<Product> select(int page) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
