/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.common.ArrayListCommon;
import vn.aptech.quanlybanhang.common.CommonException;
import vn.aptech.quanlybanhang.common.MessageCommon;
import vn.aptech.quanlybanhang.common.MessageContent;
import vn.aptech.quanlybanhang.common.Response;
import vn.aptech.quanlybanhang.common.ValidateCommon;
import vn.aptech.quanlybanhang.dao.ProductDAO;
import vn.aptech.quanlybanhang.dao.ProductDAOImpl;
import vn.aptech.quanlybanhang.entities.Product;

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
        ValidateCommon.validateNullObject(product.getBrand(), "brand");
        ValidateCommon.validateNullObject(product.getCategory(), "category");
        ValidateCommon.validateNullObject(product.getEmployee(), "employee");
        ValidateCommon.validateNullObject(product.getName(), "name");
        ValidateCommon.validateNullObject(product.getPrice(), "price");
        ValidateCommon.validateNullObject(product.getQuantityInStock(), "quantityInStock");
        return this.productDAO.create(product);
    }

    @Override
    public boolean update(Product product) throws SQLException, Exception {
        ValidateCommon.validateNullObject(product.getBrand(), "brand");
        ValidateCommon.validateNullObject(product.getCategory(), "category");
        ValidateCommon.validateNullObject(product.getEmployee(), "employee");
        ValidateCommon.validateNullObject(product.getName(), "name");
        ValidateCommon.validateNullObject(product.getPrice(), "price");
        ValidateCommon.validateNullObject(product.getQuantityInStock(), "quantityInStock");
        return this.productDAO.update(product);
    }

    @Override
    public boolean deleteById(int id) throws SQLException, Exception {
        ValidateCommon.validateNullObject(id, "id");
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
}
