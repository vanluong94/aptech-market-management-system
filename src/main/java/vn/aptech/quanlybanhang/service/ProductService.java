/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.util.Date;
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public interface ProductService extends BaseService<Product> {

    public PaginatedResults<Product> findByCategoryId(int page, int id) throws Exception;

    /**
     *
     * @param page
     * @param name
     * @return
     * @throws java.lang.Exception
     */
    public PaginatedResults<Product> findByName(int page, String name) throws Exception;

    public PaginatedResults<Product> selectOutOfStock(int page) throws Exception;

    PaginatedResults<Product> findAllByOrderByUnitsOnOrderDesc(int page, String fromDate, String toDate) throws Exception;

    double getStatisticAmount(Date fromDate, Date toDate) throws Exception;
    
    Product findFirstProductByBrand(Brand brand) throws Exception;
    Product findFirstProductBySupplier(Supplier sup) throws Exception;
    Product findFirstProductByCategory(Category cat) throws Exception;
}
