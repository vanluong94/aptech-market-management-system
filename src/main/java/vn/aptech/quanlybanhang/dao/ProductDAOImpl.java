/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.utilities.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    private final static String SQL_SELECT_ALL = "SELECT * FROM products";
    private final static String SQL_GET_ONE = "SELECT * FROM products WHERE product_id = ?";
    private final static String SQL_GET_BY_CATEGORY_ID = "SELECT * FROM products WHERE category_id = ?";
    private final static String SQL_INSERT = "INSERT INTO `products` (`brand_id`, `category_id`, `employee_id`, `product_name`,"
            + " `product_price`, `product_stock`) VALUES (?, ?, ?, ?, ?, ?);";
    private final static String SQL_DELETE = "DELETE FROM products WHERE product_id = ?";

    /**
     *
     * @param object
     * @return
     * @throws SQLException
     */
    @Override
    public boolean create(Product object) throws SQLException {
        int rowsAffected = -1;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setInt(1, object.getBrand().getBrandId());
            pstmt.setInt(2, object.getCategory().getCategoryId());
            pstmt.setInt(3, object.getEmployee().getEmployeeId());
            pstmt.setString(4, object.getName());
            pstmt.setDouble(5, object.getPrice());
            pstmt.setInt(6, object.getQuantityInStock());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean update(Product object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        int temp = -1;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, id);
            temp = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return temp > 0;
    }

    @Override
    public Product findById(int id) throws SQLException {
        Product product = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("product_id"));
//                product.getBrand().setBrandId(rs.getInt("brand_id"));
//                product.getCategory().setCategoryId(rs.getInt("category_id"));
//                product.getEmployee().setEmployeeId(rs.getInt("employee_id"));
                product.setName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("product_price"));
                product.setQuantityInStock(rs.getInt("product_stock"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<Product>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
//                product.getBrand().setBrandId(rs.getInt("brand_id"));
//                product.getCategory().setCategoryId(rs.getInt("category_id"));
//                product.getEmployee().setEmployeeId(rs.getInt("employee_id"));
                product.setName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("product_price"));
                product.setQuantityInStock(rs.getInt("product_stock"));
                products.add(product);
            }
        } catch (Exception e) {
            throw e;
        }
        return products;
    }

    @Override
    public List<Product> findByCategoryId(int id) throws SQLException {
        List<Product> products = new ArrayList<Product>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_BY_CATEGORY_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
//                product.getBrand().setBrandId(rs.getInt("brand_id"));
//                product.getCategory().setCategoryId(rs.getInt("category_id"));
//                product.getEmployee().setEmployeeId(rs.getInt("employee_id"));
                product.setName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("product_price"));
                product.setQuantityInStock(rs.getInt("product_stock"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw e;
        }
        return products;
    }

    /**
     *
     * @param name
     * @return
     * @throws SQLException
     */
    @Override
    public List<Product> findByName(String name) throws SQLException {
        List<Product> products = new ArrayList<Product>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM products where product_name LIKE ?");
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
//                product.getBrand().setBrandId(rs.getInt("brand_id"));
//                product.getCategory().setCategoryId(rs.getInt("category_id"));
//                product.getEmployee().setEmployeeId(rs.getInt("employee_id"));
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("product_price"));
                product.setQuantityInStock(rs.getInt("product_stock"));
                Timestamp createdAt = rs.getTimestamp("created_date");
                product.setCreatedAt(new java.util.Date(createdAt.getTime()));
                Timestamp updatedAt = rs.getTimestamp("updated_date");
                product.setUpdatedAt(new java.util.Date(updatedAt.getTime()));
                products.add(product);
            }
        } catch (SQLException e) {
            throw e;
        }
        return products;
    }

}
