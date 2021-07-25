/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.dao.BrandDAOImpl;
import vn.aptech.quanlybanhang.entities.Brand;


public class BrandServiceImpl implements BrandService {
    
    private final BrandDAOImpl brandDAO;
    
    public BrandServiceImpl(){
        this.brandDAO = new BrandDAOImpl();
    }

    @Override
    public boolean saveOrUpdate(Brand object) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return this.brandDAO.deleteById(id);
    }

    @Override
    public Brand findById(int id) throws SQLException, Exception {
        return this.brandDAO.findById(id);
    }

    @Override
    public List<Brand> findAll() throws SQLException {
        return this.brandDAO.findAll();
    }

    @Override
    public boolean insert(Brand brand) throws SQLException {
        return this.brandDAO.insert(brand);
    }

    @Override
    public boolean update(Brand object) throws SQLException {
        return this.brandDAO.update(object);
    }
    
    public List<Brand> searchByName(String name) throws SQLException, ClassNotFoundException{
        return this.brandDAO.searchByName(name);
    }
    
}
