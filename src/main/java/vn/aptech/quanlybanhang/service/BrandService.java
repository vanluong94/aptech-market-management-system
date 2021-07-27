/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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