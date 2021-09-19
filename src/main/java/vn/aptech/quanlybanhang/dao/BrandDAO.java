/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.util.List;
import vn.aptech.quanlybanhang.entities.Brand;

/**
 *
 * @author vanluong
 */
public interface BrandDAO extends BaseDAO<Brand> {

    List<Brand> searchByName(String name) throws Exception;
}
