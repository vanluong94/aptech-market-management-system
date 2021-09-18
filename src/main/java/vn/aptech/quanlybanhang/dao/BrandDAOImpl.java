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
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

public class BrandDAOImpl implements BrandDAO {

    private final static int PER_PAGE = 10;
    private final static String SQL_SELECT_ALL = "SELECT * FROM brands";
    private final static String SQL_DELETE = "DELETE FROM brands WHERE brand_id = ?";
    private final static String SQL_SELECT_ONE = "SELECT * FROM brands WHERE brand_id = ?";
    private final static String SQL_SELECT_ALL_PAGINATION = "SELECT * FROM brands LIMIT ?,?";
    private final static String SQL_COUNT_ALL = "SELECT count(*) as total FROM brands";

    @Override
    public List<Brand> findAll() throws SQLException {
        List<Brand> brands = new ArrayList<>();
        try (
            Connection conn = DBConnection.getConnection();  
            Statement st = conn.createStatement();
        ) {
            
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                brands.add(this.transferRSToBrand(rs));
            }
        } catch (SQLException e) {
            throw e;
        }
        return brands;
    }

    public PaginatedResults<Brand> select(int page) throws SQLException {
        
        PaginatedResults<Brand> pagination = new PaginatedResults<>(page, PER_PAGE);
        
        List<Brand> brands = new ArrayList<>();
        
        try ( 
                Connection conn = DBConnection.getConnection();
                Statement stTotal = conn.createStatement();
        ) {
            
            ResultSet rsTotal = stTotal.executeQuery(SQL_COUNT_ALL);
            if (rsTotal.next()) {
                pagination.setTotalItems(rsTotal.getInt("total"));
            }

            try(PreparedStatement stSelect = conn.prepareStatement(SQL_SELECT_ALL_PAGINATION)){
                stSelect.setInt(1, pagination.getOffset());
                stSelect.setInt(2, pagination.getPerPage());
                ResultSet rsSelect = stSelect.executeQuery();
                
                while(rsSelect.next()){
                    brands.add(this.transferRSToBrand(rsSelect));
                }

                pagination.setResults(brands);
            }
            
        } catch (SQLException e) {
            throw e;
        }
        
        return pagination;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        int rowsAffected = -1;
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement st = conn.prepareStatement(SQL_DELETE);
        ) {
            
            st.setInt(1, id);
            rowsAffected = st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return rowsAffected > 0;
    }

    @Override
    public Brand findById(int id) throws SQLException {
        Brand brand = null;
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement st = conn.prepareStatement(SQL_SELECT_ONE)
        ){
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return transferRSToBrand(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    @Override
    public boolean create(Brand brand) throws SQLException {
        
        String sql = "INSERT INTO brands (brand_name, brand_add) VALUES (?,?)";
        
        try (PreparedStatement st = DBConnection.getConnection().prepareStatement(sql)){
            st.setString(1, brand.getBrandName());
            st.setString(2, brand.getBrandAdd());

            return st.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean update(Brand brand) throws SQLException {
        String sql = "UPDATE brands SET brand_name = ?, brand_add = ? WHERE brand_id = ?";
        
        try (PreparedStatement st = DBConnection.getConnection().prepareStatement(sql)){
            st.setString(1, brand.getBrandName());
            st.setString(2, brand.getBrandAdd());
            st.setInt(3, brand.getBrandId());

            return st.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public List<Brand> searchByName(String name) throws SQLException, ClassNotFoundException {

        List<Brand> foundBrands = new ArrayList<>();
        String sql = "SELECT * FROM brands WHERE brand_name LIKE ?";
        
        try(PreparedStatement st = DBConnection.getConnection().prepareStatement(sql)){
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                foundBrands.add(transferRSToBrand(rs));
            }
        }
        
        return foundBrands;
    }

    protected Brand transferRSToBrand(ResultSet rs) throws SQLException {

        Brand brand = new Brand();

        brand.setBrandId(rs.getInt("brand_id"));
        brand.setBrandName(rs.getString("brand_name"));
        brand.setBrandAdd(rs.getString("brand_add"));

        return brand;

    }

}
