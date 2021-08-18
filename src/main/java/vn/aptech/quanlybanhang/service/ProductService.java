/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public interface ProductService extends BaseService<Product> {

    public List<Product> findByCategoryId(int id) throws SQLException;

    /**
     *
     * @param name
     * @return
     * @throws java.sql.SQLException
     */
    public List<Product> findByName(String name) throws SQLException;
    
    public PaginatedResults<Product> selectOutOfStock(int page) throws SQLException;
}
