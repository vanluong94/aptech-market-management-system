/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public interface ProductService extends BaseService<Product> {

    public PaginatedResults<Product> findByCategoryId(int page,int id) throws SQLException;

    /**
     *
     * @param name
     * @return
     * @throws java.sql.SQLException
     */
    public PaginatedResults<Product> findByName(int page,String name) throws SQLException;
    
    public PaginatedResults<Product> selectOutOfStock(int page) throws SQLException;

    PaginatedResults<Product> findAllByOrderByUnitsOnOrderDesc(int page, String fromDate, String toDate) throws SQLException;
}
