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
import vn.aptech.quanlybanhang.entities.ProductDiscount;
import vn.aptech.quanlybanhang.utilities.DBConnection;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

public class DiscountDAOImpl implements DiscountDAO {

    private final static String SQL_SELECT_ALL = "SELECT * FROM discounts";
    private final static String SQL_INSERT = "INSERT INTO discounts (discount_name) VALUE (?)";
    private final static String SQL_GET_ONE = "SELECT * FROM discounts WHERE discount_id = ?";
    private final static String SQL_DELETE = "DELETE FROM discounts WHERE discount_id = ?";
    private final static String SQL_UPDATE = "UPDATE discounts SET discount_name = ? WHERE discount_id = ?";

    @Override
    public boolean create(Discount object) throws SQLException {
        int rowsAffected = -1;
        try (
                Connection conn = DBConnection.getConnection();  
                PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        ) {
            
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
            if(discount.getId() > 0) {
                
                try( PreparedStatement st = conn.prepareStatement(
                        "INSERT INTO discount_product "
                        + " (discount_id, product_id, discount, start_date, end_date) "
                        + " VALUES (?, ?, ?, ?, ?)"
                ) ){
                    
                    for (ProductDiscount dProduct : discount.getProductDiscounts()) {
                        st.setInt(1, discount.getId());
                        st.setInt(2, dProduct.getProductId());
                        st.setFloat(3, dProduct.getDiscount());
                        st.setTimestamp(4, DateCommon.convertDateToSqlTimestamp(dProduct.getStartDate()));  
                        st.setTimestamp(5, DateCommon.convertDateToSqlTimestamp(dProduct.getEndDate()));  
                        
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
        List<Discount> discounts = new ArrayList<Discount>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Discount discount = new Discount();
                discount.setId(rs.getInt("discount_id"));
                discount.setName(rs.getString("discount_name"));
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
        try ( Connection conn = DBConnection.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE);
                pstmt.setString(1, discount.getName());
                pstmt.setInt(2, discount.getId());
                
                rs = pstmt.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(DiscountDAOImpl.class.getName()).log(Level.SEVERE,null,e);
        }
        return rs > 0;
    }

    @Override
    public PaginatedResults<Discount> select(int page) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
