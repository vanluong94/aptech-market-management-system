/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import vn.aptech.quanlybanhang.dao.CategoryDAO;
import vn.aptech.quanlybanhang.dao.CategoryDAOImpl;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.utilities.I18n;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;


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
    public boolean create(Category object) throws SQLException, Exception {
        if (Objects.requireNonNull(object.getCategoryName()) == null) {
            throw new IllegalArgumentException(I18n.getMessage("category.error.emptyName"));
        }
        return categoryDAO.create(object);
    }

    @Override
    public boolean deleteById(int id) throws SQLException, Exception {
        if (id < 1) {
            throw new Exception(I18n.getMessage("input.invalidID"));
        }
        return categoryDAO.deleteById(id);
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
            throw new Exception(I18n.getMessage("input.invalidID"));
        }
        return categoryDAO.findById(id);
    }

    @Override
    public List<Category> findAll() throws SQLException {
        return categoryDAO.findAll();
    }

    @Override
    public boolean update(Category object) throws SQLException {
        return categoryDAO.update(object);
    }
    
    @Override
    public List<Category> searchByName(String name) throws SQLException {
        return categoryDAO.searchByName(name);
    }

    @Override
    public PaginatedResults<Category> select(int page) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
