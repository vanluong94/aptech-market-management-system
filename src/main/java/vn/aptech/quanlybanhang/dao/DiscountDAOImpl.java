/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import vn.aptech.quanlybanhang.entities.Discount;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.utilities.DBConnection;


public class DiscountDAOImpl implements DiscountDAO {
    
    private final static String SQL_SELECT_ALL = "SELECT * FROM discounts";
    private final static String SQL_INSERT = "INSERT INTO discounts (discount_name) VALUE (?)";
    private final static String SQL_GET_ONE = "SELECT * FROM discounts WHERE discount_id = ?";
    private final static String SQL_DELETE = "DELETE FROM discounts WHERE discount_id = ?";
    
    @Override
    public boolean saveOrUpdate(Discount object) throws SQLException {
        int rowsAffected = -1;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setString(1, object.getDiscountName());
            rowsAffected = pstmt.executeUpdate();
        }  catch (SQLException ex){
            throw ex;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DiscountDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected > 0;
    }
    @Override
    public boolean deleteById(int id) throws SQLException {
        int rs = -1;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, id);
            rs = pstmt.executeUpdate();
            if (rs > 0) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException e ) {
            throw e;
        }catch(ClassNotFoundException e){
            Logger.getLogger(DiscountDAOImpl.class.getName()).log(Level.SEVERE,null,e);
        }
        return rs > 0;
    }

    @Override
    public Discount findById(int id) throws SQLException {
        Connection conn = null;
        Discount discount = null;
        try { 
            conn =DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) { 
                discount = new Discount();
                discount.setDiscountId(rs.getInt("discount_id"));
                discount.setDiscountName(rs.getString("discount_name"));
            }
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DiscountDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) conn.close();
        }
        return discount;
    }

    @Override
    public List<Discount> findAll() throws SQLException {
        List<Discount> discounts = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Discount discount = new Discount();
                discount.setDiscountId(rs.getInt("discount_id"));
                discount.setDiscountName(rs.getString("discount_name"));
                discounts.add(discount);
            }
        } catch (ClassNotFoundException e) {
            Logger.getLogger(DiscountDAOImpl.class.getSimpleName()).log(Level.SEVERE,null,e);
        }
        return discounts;
    }

    @Override
    public boolean insert(Discount object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Discount object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}