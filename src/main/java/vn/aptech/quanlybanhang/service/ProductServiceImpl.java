/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.dao.ProductDAO;
import vn.aptech.quanlybanhang.dao.ProductDAOImpl;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.exception.InputInvalidException;

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
        if (id < 1) {
            throw new InputInvalidException("ID khong hop le");
        }
        return this.productDAO.deleteById(id);
    }

    @Override
    public Product findById(int id) throws SQLException, Exception {

        if (id < 1) {
            throw new InputInvalidException("ID khong hop le");
        }
        return this.productDAO.findById(id);
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return this.productDAO.findAll();
    }

    public void validateProductData(Product product) throws InputInvalidException {
        if (product.getName().trim().length() == 0) {
            throw new InputInvalidException("Ten san pham khong duoc bo trong");
        }

        if (product.getEmployee() == null) {
            throw new InputInvalidException("Hay dang nhap de tiep tuc");
        }

        if (product.getCategory() == null) {
            throw new InputInvalidException("Hay chon Danh muc cho san pham");
        }

        if (product.getBrand() == null) {
            throw new InputInvalidException("Hay chon Thuong hieu cho san pham");
        }

        if (product.getQuantityInStock() == 0) {
            throw new InputInvalidException("Hay nhap So luong ton kho cho san pham");
        }

        if (product.getPrice() <= 0) {
            throw new InputInvalidException("Hay nnhap Gia hop le cho san pham");
        }
    }

    @Override
    public List<Product> findByCategoryId(int id) throws SQLException, ClassNotFoundException {
        if (id < 1) {
            try {
                throw new Exception("ID khong hop le!");
            } catch (Exception ex) {
                Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return productDAO.findByCategoryId(id);
    }

    @Override
    public List<Product> findByName(String name) throws SQLException {
        return productDAO.findByName(name);
    }
}
