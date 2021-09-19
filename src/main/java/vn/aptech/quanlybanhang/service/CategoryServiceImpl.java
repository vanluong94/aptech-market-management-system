/*
 * Do an Java tai HaNoi Aptech
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
     * @throws Exception
     */
    @Override
    public boolean create(Category object) throws Exception {
        if (Objects.requireNonNull(object.getName()) == null) {
            throw new IllegalArgumentException(I18n.getMessage("category.error.emptyName"));
        }
        return categoryDAO.create(object);
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        if (id < 1) {
            throw new Exception(I18n.getMessage("input.invalidID"));
        }
        return categoryDAO.deleteById(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Category findById(int id) throws Exception {
        if (id < 1) {
            throw new Exception(I18n.getMessage("input.invalidID"));
        }
        return categoryDAO.findById(id);
    }

    @Override
    public List<Category> findAll() throws Exception {
        return categoryDAO.findAll();
    }

    @Override
    public boolean update(Category object) throws Exception {
        return categoryDAO.update(object);
    }

    @Override
    public List<Category> searchByName(String name) throws Exception {
        return categoryDAO.searchByName(name);
    }

    @Override
    public PaginatedResults<Category> select(int page) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
