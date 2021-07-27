/*
 *  Do an Java tai HaNoi Aptech
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
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.utilities.DBConnection;


public class SupplierDAOImpl implements SupplierDAO {

    private final static String SQL_SELECT_ALL = "SELECT * FROM suppliers";
    private final static String SQL_INSERT = "INSERT INTO suppliers (supplier_name,supplier_add) VALUE (?,?)";
    private final static String SQL_GET_ONE = "SELECT * FROM suppliers WHERE supplier_id = ?";
    private final static String SQL_DELETE = "DELETE FROM suppliers WHERE supplier_id = ?";
    
    @Override
    public boolean create(Supplier object) throws SQLException {
        int rowsAffected = -1;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setString(1, object.getSupplierName());
            pstmt.setString(2, object.getSupplierAddress());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        Connection conn = null;
        int rs = -1;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, id);
            rs = pstmt.executeUpdate();
            if (rs>0) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs > 0;
    }

    @Override
    public Supplier findById(int id) throws SQLException {
        Supplier supplier = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                supplier = new Supplier();
                supplier.setSupplierId(rs.getInt("supplier_id"));
                supplier.setSupplierName(rs.getString("supplier_name"));
                supplier.setSupplierAddress(rs.getString("supplier_add"));
            }
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) conn.close();
        }
    return supplier;
    }

    @Override
    public List<Supplier> findAll() throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(rs.getInt("supplier_id"));
                supplier.setSupplierName(rs.getString("supplier_name"));
                supplier.setSupplierAddress(rs.getString("supplier_add"));
                suppliers.add(supplier);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        return suppliers;
    }

    public boolean update(Supplier object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}