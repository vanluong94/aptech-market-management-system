/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public interface ProductDAO extends BaseDAO<Product> {

    PaginatedResults<Product> findByCategoryId(int page, int id) throws Exception;

    PaginatedResults<Product> findByName(int page, String name) throws Exception;

    PaginatedResults<Product> selectOutOfStock(int page) throws Exception;

    PaginatedResults<Product> findAllByOrderByUnitsOnOrderDesc(int page, String fromDate, String toDate) throws Exception;

    double getStatisticAmount(java.sql.Date fromDate, java.sql.Date toDate) throws Exception;
}
