/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.dao.BrandDAOImpl;
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.exception.InputInvalidException;
import vn.aptech.quanlybanhang.utilities.I18n;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author vanluong
 */
public class BrandServiceImpl implements BrandService {

    private final BrandDAOImpl brandDAO;

    public BrandServiceImpl() {
        this.brandDAO = new BrandDAOImpl();
    }

    @Override
    public boolean deleteById(int id) throws SQLException, Exception {
        if (id < 1) {
            throw new InputInvalidException(I18n.getMessage("input.invalidID"));
        }
        return this.brandDAO.deleteById(id);
    }

    @Override
    public Brand findById(int id) throws SQLException, Exception {
        if (id < 1) {
            throw new InputInvalidException(I18n.getMessage("input.invalidID"));
        }
        return this.brandDAO.findById(id);
    }

    @Override
    public List<Brand> findAll() throws SQLException {
        return this.brandDAO.findAll();
    }

    @Override
    public boolean create(Brand brand) throws SQLException {
        return this.brandDAO.create(brand);
    }

    @Override
    public boolean update(Brand object) throws SQLException {
        return this.brandDAO.update(object);
    }

    @Override
    public List<Brand> searchByName(String name) throws SQLException, ClassNotFoundException {
        return this.brandDAO.searchByName(name);
    }

    @Override
    public PaginatedResults<Brand> select(int page) throws SQLException, IllegalArgumentException {
        if (page < 1) {
            throw new IllegalArgumentException(I18n.getMessage("input.invalidPage"));
        }
        return this.brandDAO.select(page);
    }

}
