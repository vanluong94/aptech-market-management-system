/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public interface ProductDAO extends BaseDAO<Product> {
    List<Product> findByCategoryId(int id) throws SQLException;
    List<Product> findByName(String name) throws SQLException;
    PaginatedResults<Product> selectOutOfStock(int page) throws SQLException;
}
