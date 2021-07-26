/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.utilities.DBConnection;

public class BrandDAOImpl implements BrandDAO {
    
    private final static int PER_PAGE = 10;
    
    private final static String SQL_SELECT_ALL = "SELECT * FROM brands";
    
    @Override
    public ArrayList<Brand> findAll() throws SQLException {

        ArrayList<Brand> brands = new ArrayList<Brand>();
        try {
            Statement st = DBConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL);
            
            while(rs.next()){
                brands.add(this.transferRSToBrand(rs));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return brands;

    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        try {
            String sql = "DELETE FROM brands WHERE brand_id = ?";
            PreparedStatement st = DBConnection.getConnection().prepareStatement(sql);
            st.setInt(1, id);
            
            return st.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Brand findById(int id) throws SQLException {
        
        try {
            String sql = "SELECT * FROM brands WHERE brand_id = ?";
            PreparedStatement st = DBConnection.getConnection().prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()){
                return transferRSToBrand(rs);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }

    @Override
    public boolean create(Brand brand) throws SQLException {
        try {
            String sql = "INSERT INTO brands (brand_name, brand_add) VALUES (?,?)";
            PreparedStatement st = DBConnection.getConnection().prepareStatement(sql);
            st.setString(1, brand.getBrandName());
            st.setString(2, brand.getBrandAdd());
            
            return st.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean update(Brand brand) throws SQLException {
        try {
            String sql = "UPDATE brands SET brand_name = ?, brand_add = ? WHERE brand_id = ?";
            
            PreparedStatement st = DBConnection.getConnection().prepareStatement(sql);
            st.setString(1, brand.getBrandName());
            st.setString(2, brand.getBrandAdd());
            st.setInt(3, brand.getBrandId());
            
            return st.executeUpdate() > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    @Override
    public List<Brand> searchByName(String name) throws SQLException, ClassNotFoundException{
        
        List<Brand> foundBrands = new ArrayList<>();
        
        String sql = "SELECT * FROM brands WHERE brand_name LIKE ?";
        PreparedStatement st = DBConnection.getConnection().prepareStatement(sql);
        st.setString(1, "%" + name + "%");
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            foundBrands.add(transferRSToBrand(rs));
        }
        
        return foundBrands;
    }
    
    protected Brand transferRSToBrand(ResultSet rs) throws SQLException{
        
        Brand brand = new Brand();
        
        brand.setBrandId(rs.getInt("brand_id"));
        brand.setBrandName(rs.getString("brand_name"));
        brand.setBrandAdd(rs.getString("brand_add"));
        
        return brand;
        
    }

}
