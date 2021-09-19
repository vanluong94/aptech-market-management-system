/*
 * Do an Java tai HaNoi Aptech
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
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 * @author vanluong
 */
public class CategoryDAOImpl implements CategoryDAO {

    private final static String SQL_INSERT = "INSERT INTO categories (category_name) VALUES (?)";
    private final static String SQL_GET_ONE = "SELECT categories.*, COUNT(product_id) as products_count"
            + " FROM categories"
            + " LEFT JOIN products ON categories.category_id = products.category_id"
            + " WHERE categories.category_id = ?"
            + " GROUP BY categories.category_id";
    private final static String SQL_DELETE = "DELETE FROM categories WHERE category_id = ?";
    private final static String SQL_UPDATE = "UPDATE categories SET category_name = ? WHERE category_id = ?";
    private final static String SQL_GET_ALL = "SELECT categories.*, COUNT(product_id) as products_count"
            + " FROM categories"
            + " LEFT JOIN products ON categories.category_id = products.category_id"
            + " GROUP BY categories.category_id";
    private final static String SQL_SEARCH = "SELECT categories.*, COUNT(product_id) as products_count"
            + " FROM categories"
            + " LEFT JOIN products ON categories.category_id = products.category_id"
            + " WHERE categories.category_name LIKE ?"
            + " GROUP BY categories.category_id";

    @Override
    public boolean create(Category object) throws Exception {
        int rowsAffected = -1;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt;
            pstmt = conn.prepareCall(SQL_INSERT);
            pstmt.setString(1, object.getName());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        int rowsAffected = -1;
        try (PreparedStatement st = DBConnection.getConnection().prepareStatement(SQL_DELETE)) {
            st.setInt(1, id);
            rowsAffected = st.executeUpdate();
        }
        return rowsAffected > 0;
    }

    @Override
    public Category findById(int id) throws Exception {
        Category category = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));
                category.setProductCount(rs.getInt("products_count"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return category;
    }

    @Override
    public List<Category> findAll() throws Exception {
        List<Category> categories = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();

                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));
                category.setProductCount(rs.getInt("products_count"));

                categories.add(category);
            }
        } catch (SQLException e) {
            throw e;
        }
        return categories;
    }

    @Override
    public boolean update(Category category) throws Exception {
        int rowsAffected = -1;
        try (PreparedStatement st = DBConnection.getConnection().prepareStatement(SQL_UPDATE)) {
            st.setString(1, category.getName());
            st.setInt(2, category.getId());
            rowsAffected = st.executeUpdate();
        }
        return rowsAffected > 0;
    }

    @Override
    public List<Category> searchByName(String name) throws Exception {

        List<Category> categories = new ArrayList<>();
        try (PreparedStatement st = DBConnection.getConnection().prepareStatement(SQL_SEARCH)) {
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("category_name"));
                category.setProductCount(rs.getInt("products_count"));
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public PaginatedResults<Category> select(int page) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
