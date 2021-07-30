/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Product;

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
}
