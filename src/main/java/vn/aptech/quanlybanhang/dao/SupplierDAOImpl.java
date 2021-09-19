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
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

public class SupplierDAOImpl implements SupplierDAO {

    private final static String SQL_SELECT_ALL = "SELECT * FROM suppliers";
    private final static String SQL_INSERT = "INSERT INTO suppliers (supplier_name,supplier_add) VALUE (?,?)";
    private final static String SQL_GET_ONE = "SELECT * FROM suppliers WHERE supplier_id = ?";
    private final static String SQL_DELETE = "DELETE FROM suppliers WHERE supplier_id = ?";
    private final static String SQL_UPDATE = "UPDATE suppliers SET supplier_name = ?, supplier_add =? WHERE supplier_id = ?";
    private final static String SQL_SEARCH_BY_NAME = "SELECT * FROM suppliers WHERE supplier_name LIKE ?";

    @Override
    public boolean create(Supplier object) throws Exception {
        int rowsAffected = -1;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setString(1, object.getName());
            pstmt.setString(2, object.getAddress());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        Connection conn = null;
        int rs = -1;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, id);
            rs = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return rs > 0;
    }

    @Override
    public Supplier findById(int id) throws Exception {
        Supplier supplier = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                supplier = new Supplier();
                supplier.setId(rs.getInt("supplier_id"));
                supplier.setName(rs.getString("supplier_name"));
                supplier.setAddress(rs.getString("supplier_add"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return supplier;
    }

    @Override
    public List<Supplier> findAll() throws Exception {
        List<Supplier> suppliers = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt("supplier_id"));
                supplier.setName(rs.getString("supplier_name"));
                supplier.setAddress(rs.getString("supplier_add"));
                suppliers.add(supplier);
            }
        } finally {
            DBConnection.maybeCloseConnection();
        }

        return suppliers;
    }

    @Override
    public boolean update(Supplier supplier) throws Exception {
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(SQL_UPDATE)) {
            pstmt.setString(1, supplier.getName());
            pstmt.setString(2, supplier.getAddress());
            pstmt.setInt(3, supplier.getId());

            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            DBConnection.maybeCloseConnection();
        }

        return false;
    }

    @Override
    public List<Supplier> searchByName(String Name) throws Exception {
        List<Supplier> foundSuppliers = new ArrayList<>();
        try (PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(SQL_SEARCH_BY_NAME)) {
            pstmt.setString(1, "%" + Name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt("supplier_id"));
                supplier.setName(rs.getString("supplier_name"));
                supplier.setAddress(rs.getString("supplier_add"));
                foundSuppliers.add(supplier);
            }
        }
        return foundSuppliers;
    }

    @Override
    public PaginatedResults<Supplier> select(int page) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
