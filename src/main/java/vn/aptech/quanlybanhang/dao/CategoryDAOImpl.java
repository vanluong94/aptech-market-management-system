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
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.utilities.DBConnection;

public class CategoryDAOImpl implements CategoryDAO {

    private final static String SQL_INSERT = "INSERT INTO categories (categoryName) VALUES (?)";
    private final static String SQL_SELECT_ALL = "SELECT * FROM categories";
    private final static String SQL_GET_ONE = "SELECT * FROM categories WHERE category_id = ?";

    @Override
    public boolean create(Category object) throws SQLException {
        int rowsAffected = -1;
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt;
            pstmt = conn.prepareCall(SQL_INSERT);
            pstmt.setString(1, object.getCategoryName());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            DBConnection.maybeCloseConnection();
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category findById(int id) throws SQLException {
        Category category = null;
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return category;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> categories = new ArrayList<Category>();
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            DBConnection.maybeCloseConnection();
        }
        return categories;
    }

    @Override
    public boolean update(Category object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
