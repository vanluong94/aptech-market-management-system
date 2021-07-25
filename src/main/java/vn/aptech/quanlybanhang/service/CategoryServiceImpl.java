/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.dao.CategoryDAO;
import vn.aptech.quanlybanhang.dao.CategoryDAOImpl;
import vn.aptech.quanlybanhang.entities.Category;


public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl() {
        this.categoryDAO = new CategoryDAOImpl();
    }
    
    /**
     *
     * @param object
     * @return
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean saveOrUpdate(Category object) throws SQLException, Exception {
        if (object == null) {
            throw new Exception("Danh mục không được trống.");
        }
        return categoryDAO.saveOrUpdate(object);
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public Category findById(int id) throws SQLException, Exception {
        if (id < 1) {
            throw new Exception("ID không được để trống!");
        }
        return categoryDAO.findById(id);
    }

    @Override
    public List<Category> findAll() throws SQLException {
        return categoryDAO.findAll();
    }

    @Override
    public boolean insert(Category object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Category object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
