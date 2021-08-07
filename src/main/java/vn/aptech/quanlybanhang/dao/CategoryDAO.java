/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Category;

/**
 *
 * @author anhnbt
 * @author vanluong
 */
public interface CategoryDAO extends BaseDAO<Category> {
    List<Category> searchByName(String name) throws SQLException;
}
