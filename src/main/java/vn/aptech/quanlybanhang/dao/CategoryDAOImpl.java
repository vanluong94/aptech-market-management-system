/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.utilities.DBConnection;


public class CategoryDAOImpl implements CategoryDAO {
    
    private final static String SQL_INSERT = "INSERT INTO categories (categoryName) VALUES (?)";
    private final static String SQL_SELECT_ALL = "SELECT * FROM categories";
    private final static String SQL_GET_ONE = "SELECT * FROM categories WHERE category_id = ?";

    @Override
    public boolean saveOrUpdate(Category object) throws SQLException {
        int rowsAffected = -1;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt;
            pstmt = conn.prepareCall(SQL_INSERT);
            pstmt.setString(1, object.getCategoryName());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category findById(int id) throws SQLException {
        // Tìm chi tiết danh mục
        Category category = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
               category = new Category();
               category.setCategoryId(rs.getInt("category_id"));
               category.setCategoryName(rs.getString("category_name"));
            }
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) conn.close();
        }
        return category;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setCategoryName(rs.getString("categoryName"));
                categories.add(category);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
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
