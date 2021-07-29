/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.utilities.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    private final static String SQL_GET_BY_CATEGORY_ID = "SELECT * FROM products WHERE category_id = ?";
    private final static String SQL_GET_ONE = "SELECT * FROM products WHERE product_id = ?";

    @Override
    public boolean create(Product object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Product object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product findById(int id) throws SQLException {
        Product product = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("product_id"));
                product.getBrand().setBrandId(rs.getInt("brand_id"));
                product.getCategory().setCategoryId(rs.getInt("category_id"));
                product.getEmployee().setEmployeeId(rs.getInt("employee_id"));
                product.setName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("product_price"));
                product.setQuantityInStock(rs.getInt("product_stock"));
            }
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Product> findByCategoryId(int id) throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_BY_CATEGORY_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.getBrand().setBrandId(rs.getInt("brand_id"));
                product.getCategory().setCategoryId(rs.getInt("category_id"));
                product.getEmployee().setEmployeeId(rs.getInt("employee_id"));
                product.setName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("product_price"));
                product.setQuantityInStock(rs.getInt("product_stock"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return products;
    }

}
