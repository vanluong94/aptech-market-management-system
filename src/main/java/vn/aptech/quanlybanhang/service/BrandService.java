/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Brand;

/**
 *
 * @author vanluong
 */
public interface BrandService extends BaseService<Brand> {

    public List<Brand> searchByName(String search) throws SQLException, ClassNotFoundException;
}
