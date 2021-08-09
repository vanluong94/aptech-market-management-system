/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    List<Category> searchByName(String name) throws SQLException;
}
