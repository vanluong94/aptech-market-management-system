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

/**
 * @author vanluong
 */
public class CategoryDAOImpl implements CategoryDAO {

    private final static String SQL_INSERT = "INSERT INTO categories (category_name) VALUES (?)";
    private final static String SQL_GET_ONE = "SELECT * FROM categories WHERE category_id = ?";
    private final static String SQL_DELETE = "DELETE FROM categories WHERE category_id = ?";

    @Override
    public boolean create(Category object) throws SQLException {
        int rowsAffected = -1;
        try ( Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt;
            pstmt = conn.prepareCall(SQL_INSERT);
            pstmt.setString(1, object.getCategoryName());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        int rowsAffected = -1;
        try ( PreparedStatement st = DBConnection.getConnection().prepareStatement(SQL_DELETE)) {
            st.setInt(1, id);
            rowsAffected = st.executeUpdate();
        }
        return rowsAffected > 0;
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
        List<Category> categories = new ArrayList<>();
        try ( Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT categories.*, COUNT(product_id) as products_count"
                    + " FROM categories"
                    + " LEFT JOIN products ON categories.category_id = products.category_id"
                    + " GROUP BY categories.category_id";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();

                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setProductsCount(rs.getInt("products_count"));

                categories.add(category);
            }
        } catch (SQLException e) {
            throw e;
        }
        return categories;
    }

    @Override
    public boolean update(Category category) throws SQLException {
        int rowsAffected = -1;
        String sql = "UPDATE categories SET category_name = ? WHERE category_id = ?";

        try ( PreparedStatement st = DBConnection.getConnection().prepareStatement(sql)) {
            st.setString(1, category.getCategoryName());
            st.setInt(2, category.getCategoryId());

            rowsAffected = st.executeUpdate();
        }

        return rowsAffected > 0;
    }

    @Override
    public List<Category> searchByName(String name) throws SQLException {

        List<Category> categories = new ArrayList<>();
        String sql = "SELECT categories.*, COUNT(product_id) as products_count"
                + " FROM categories"
                + " LEFT JOIN products ON categories.category_id = products.category_id"
                + " WHERE categories.category_name LIKE ?"
                + " GROUP BY categories.category_id";

        try ( PreparedStatement st = DBConnection.getConnection().prepareStatement(sql)) {
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Category category = new Category();

                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setProductsCount(rs.getInt("products_count"));

                categories.add(category);
            }
        }

        return categories;
    }

}
