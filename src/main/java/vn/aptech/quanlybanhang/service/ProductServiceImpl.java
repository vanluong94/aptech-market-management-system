/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.DateCommon;
import vn.aptech.quanlybanhang.common.ValidateCommon;
import vn.aptech.quanlybanhang.dao.ProductDAO;
import vn.aptech.quanlybanhang.dao.ProductDAOImpl;
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.exception.InputInvalidException;
import vn.aptech.quanlybanhang.utilities.I18n;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl() {
        productDAO = new ProductDAOImpl();
    }

    @Override
    public boolean create(Product product) throws Exception {
        this.validateProductData(product);
        return this.productDAO.create(product);
    }

    @Override
    public boolean update(Product product) throws Exception {
        this.validateProductData(product);
        return this.productDAO.update(product);
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        if (id < 1) {
            try {
                throw new Exception(I18n.getMessage("input.invalidID"));
            } catch (Exception e) {
                Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return this.productDAO.deleteById(id);
    }

    @Override
    public Product findById(int id) throws Exception {
        ValidateCommon.validateNullObject(id, "id");
        return this.productDAO.findById(id);
    }

    @Override
    public List<Product> findAll() throws Exception {
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
            throw new InputInvalidException(I18n.getMessage("product.error.emptyName"));
        }

        if (product.getQuantityInStock() == 0) {
            throw new InputInvalidException(I18n.getMessage("product.error.invalidQty"));
        }

        if (product.getPrice() <= 100) {
            throw new InputInvalidException(I18n.getMessage("product.error.invalidPrice"));
        }
    }

    @Override
    public PaginatedResults<Product> findByCategoryId(int page, int id) throws Exception {
        ValidateCommon.validateNullObject(id, "id");
        return productDAO.findByCategoryId(page, id);
    }

    @Override
    public PaginatedResults<Product> findByName(int page, String name) throws Exception {
        ValidateCommon.validateNullObject(name, "name");
//        List<Product> products = productDAO.findByName(name);
//        if (ArrayListCommon.isNullOrEmpty(products)) {
//            throw new CommonException(Response.DATA_NOT_FOUND);
//        }
        return productDAO.findByName(page, name);
    }

    @Override
    public PaginatedResults<Product> select(int page) throws Exception {
        return productDAO.select(page);
    }

    @Override
    public PaginatedResults<Product> selectOutOfStock(int page) throws Exception {
        return productDAO.selectOutOfStock(page);
    }

    @Override
    public PaginatedResults<Product> findAllByOrderByUnitsOnOrderDesc(int page, String fromDate, String toDate) throws Exception {
        return productDAO.findAllByOrderByUnitsOnOrderDesc(page, fromDate, toDate);
    }

    @Override
    public double getStatisticAmount(Date fromDate, Date toDate) throws Exception {
        java.sql.Date fromDateSql = DateCommon.convertUtilDateToSqlDate(DateCommon.getBeginDay(fromDate));
        java.sql.Date toDateSql = DateCommon.convertUtilDateToSqlDate(DateCommon.getEndDay(toDate));
        return productDAO.getStatisticAmount(fromDateSql, toDateSql);
    }

    @Override
    public Product findFirstProductByBrand(Brand brand) throws Exception {
        return this.productDAO.findFirstProductByBrand(brand);
    }

    @Override
    public Product findFirstProductBySupplier(Supplier sup) throws Exception {
        return this.productDAO.findFirstProductBySupplier(sup);
    }

    @Override
    public Product findFirstProductByCategory(Category cat) throws Exception {
        return this.productDAO.findFirstProductByCategory(cat);
    }

}
