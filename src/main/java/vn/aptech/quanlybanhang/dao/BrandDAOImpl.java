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
    private final static String SQL_DELETE = "DELETE FROM brands WHERE brand_id = ?";
    private final static String SQL_SELECT_ONE = "SELECT * FROM brands WHERE brand_id = ?";

    @Override
    public List<Brand> findAll() throws SQLException {
        List<Brand> brands = new ArrayList<Brand>();
        try (Connection conn = DBConnection.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                brands.add(this.transferRSToBrand(rs));
            }
        } catch (SQLException e) {
            throw e;
        }
        return brands;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        int rowsAffected = -1;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement st = conn.prepareStatement(SQL_DELETE);
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
        try {
            PreparedStatement st = DBConnection.getConnection().prepareStatement(SQL_SELECT_ONE);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return transferRSToBrand(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.maybeCloseConnection();
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
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.maybeCloseConnection();
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
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.maybeCloseConnection();
        }

        return false;
    }

    @Override
    public List<Brand> searchByName(String name) throws SQLException, ClassNotFoundException {

        List<Brand> foundBrands = new ArrayList<Brand>();
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
