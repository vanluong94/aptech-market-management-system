/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.util.List;
import vn.aptech.quanlybanhang.entities.Category;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @author vanluong
 */
public interface CategoryDAO extends BaseDAO<Category> {

    List<Category> searchByName(String name) throws Exception;
}
