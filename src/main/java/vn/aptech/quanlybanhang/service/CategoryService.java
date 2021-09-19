/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Category;

/**
 *
 * @author anhnbt
 */
public interface CategoryService extends BaseService<Category> {

    List<Category> searchByName(String name) throws Exception;
}
