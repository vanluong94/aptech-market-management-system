/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import vn.aptech.quanlybanhang.entities.Discount;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.DateCommon;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.entities.ProductDiscount;
import vn.aptech.quanlybanhang.utilities.DBConnection;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

public class DiscountDAOImpl implements DiscountDAO {

    private final static String SQL_SELECT_ALL = "SELECT discounts.*, COUNT(d_products.product_id) as products_count "
            + " FROM discounts "
            + " LEFT JOIN discount_product AS d_products ON d_products.discount_id = discounts.discount_id "
            + " GROUP BY discounts.discount_id ";
    private final static String SQL_INSERT = "INSERT INTO discounts (discount_name) VALUE (?)";
    private final static String SQL_GET_ONE = "SELECT * FROM discounts WHERE discount_id = ?";
    private final static String SQL_DELETE = "DELETE FROM discounts WHERE discount_id = ?";
    private final static String SQL_UPDATE = "UPDATE discounts SET discount_name = ? WHERE discount_id = ?";
    private final static String SQL_GET_PRODUCTS = "SELECT discount_product.*, products.* "
            + " FROM discount_product "
            + " JOIN products ON products.product_id = discount_product.product_id "
            + " WHERE discount_id = ? ";
    private final static String SQL_DELETE_PRODUCT = "DELETE FROM discount_product WHERE discount_product_id = ?";
    private final static String SQL_INSERT_PRODUCT = "INSERT INTO discount_product (discount_id, product_id, discount, start_date, end_date) VALUES (?,?,?,?,?) ";
    private final static String SQL_VALIDATE_PRODUCT = "SELECT * FROM discount_product "
            + " WHERE product_id = ? "
            + " AND ( "
            + "     (start_date BETWEEN ? AND ?) "
            + "     OR "
            + "     (end_date BETWEEN ? AND ?)"
            + " ) LIMIT 0, 1";

    @Override
    public boolean create(Discount discount) throws SQLException {
        int rowsAffected = -1;
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);) {

            conn.setAutoCommit(false);

            pstmt.setString(1, discount.getName());
            rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet ids = pstmt.getGeneratedKeys()) {
                    if (ids.next()) {
                        discount.setId(ids.getInt(1));
                    }
                }
            }

            /**
             * insert discount products
             */
            if (discount.getId() > 0) {

                try (PreparedStatement st = conn.prepareStatement(
                        "INSERT INTO discount_product "
                        + " (discount_id, product_id, discount, start_date, end_date) "
                        + " VALUES (?, ?, ?, ?, ?)"
                )) {

                    for (ProductDiscount dProduct : discount.getProductDiscounts()) {
                        st.setInt(1, discount.getId());
                        st.setInt(2, dProduct.getProduct().getId());
                        st.setFloat(3, dProduct.getDiscountPercentage());
                        st.setTimestamp(4, DateCommon.convertDateToTimestamp(dProduct.getStartDate()));
                        st.setTimestamp(5, DateCommon.convertDateToTimestamp(dProduct.getEndDate()));

                        st.addBatch();
                    }

                    // Batch is ready, execute it to insert the data
                    st.executeBatch();
                }

            }

            conn.commit();
        } catch (SQLException ex) {
            throw ex;
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        int rs = -1;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, id);
            rs = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return rs > 0;
    }

    @Override
    public Discount findById(int id) throws SQLException {
        Connection conn = null;
        Discount discount = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                discount = new Discount();
                discount.setId(rs.getInt("discount_id"));
                discount.setName(rs.getString("discount_name"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return discount;
    }

    @Override
    public List<Discount> findAll() throws SQLException {
        List<Discount> discounts = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Discount discount = new Discount();
                discount.setId(rs.getInt("discount_id"));
                discount.setName(rs.getString("discount_name"));
                discount.setProductCount(rs.getInt("products_count"));
                discounts.add(discount);
            }
        } catch (SQLException e) {
            throw e;
        }
        return discounts;
    }

    @Override
    public boolean update(Discount discount) throws SQLException {
        int rs = -1;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE);
            pstmt.setString(1, discount.getName());
            pstmt.setInt(2, discount.getId());

            rs = pstmt.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(DiscountDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs > 0;
    }

    @Override
    public PaginatedResults<Discount> select(int page) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProductDiscount> getDiscountProducts(Discount discount) {
        List<ProductDiscount> products = new ArrayList<>();
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL_GET_PRODUCTS);) {
            pstmt.setInt(1, discount.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("product_price"));
                product.setQuantityInStock(rs.getInt("product_stock"));

                ProductDiscount pDiscount = new ProductDiscount(
                        rs.getInt("discount_product_id"),
                        rs.getInt("discount_id"),
                        product,
                        rs.getFloat("discount"),
                        DateCommon.convertTimestampToDate(rs.getTimestamp("start_date")),
                        DateCommon.convertTimestampToDate(rs.getTimestamp("end_date"))
                );
                products.add(pDiscount);
                product.setDiscount(pDiscount);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscountDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public ProductDiscount findOverlapDiscountProduct(ProductDiscount dProduct) {
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL_VALIDATE_PRODUCT);) {
            pstmt.setInt(1, dProduct.getProduct().getId());
            pstmt.setTimestamp(2, DateCommon.convertDateToTimestamp(dProduct.getStartDate()));
            pstmt.setTimestamp(3, DateCommon.convertDateToTimestamp(dProduct.getEndDate()));
            pstmt.setTimestamp(4, DateCommon.convertDateToTimestamp(dProduct.getStartDate()));
            pstmt.setTimestamp(5, DateCommon.convertDateToTimestamp(dProduct.getEndDate()));

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {

                return new ProductDiscount(
                        rs.getInt("discount_product_id"),
                        rs.getInt("discount_id"),
                        dProduct.getProduct(),
                        rs.getFloat("discount"),
                        DateCommon.convertTimestampToDate(rs.getTimestamp("start_date")),
                        DateCommon.convertTimestampToDate(rs.getTimestamp("end_date"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscountDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean deleteDiscountProduct(ProductDiscount dProduct) {
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_PRODUCT);) {
            pstmt.setInt(1, dProduct.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DiscountDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean createDiscountProduct(ProductDiscount dProduct) {

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setInt(1, dProduct.getDiscountId());
            pstmt.setInt(2, dProduct.getProduct().getId());
            pstmt.setFloat(3, dProduct.getDiscountPercentage());
            pstmt.setTimestamp(4, DateCommon.convertDateToTimestamp(dProduct.getStartDate()));
            pstmt.setTimestamp(5, DateCommon.convertDateToTimestamp(dProduct.getEndDate()));
            System.out.println(pstmt.toString());
            if (pstmt.executeUpdate() > 0) {
                try (ResultSet ids = pstmt.getGeneratedKeys()) {
                    if (ids.next()) {
                        dProduct.setId(ids.getInt(1));
                    }
                }

                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscountDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
