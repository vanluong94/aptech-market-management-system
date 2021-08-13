/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.ImportProduct;
import vn.aptech.quanlybanhang.utilities.DBConnection;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ImportProductDAOImpl implements ImportProductDAO {
    private final static String SQL_INSERT = "INSERT INTO `import_products` (`supplier_id`, `product_id`, `employee_id`, `product_quantity`,"
            + " `product_price`) VALUES (?, ?, ?, ?, ?);";
    
    
    @Override
    public boolean create(ImportProduct object) throws SQLException {
        int rowsAffected = -1;
        try {
            // Do dùng transaction nên không đóng connection ở method này
            // Việc đóng connection sẽ ở method ProductDAOImpl.create()
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setInt(1, object.getSupplier().getId());
            pstmt.setInt(2, object.getProduct().getId());
            pstmt.setInt(3, object.getEmployee().getEmployeeId());
            pstmt.setInt(4, object.getQuantity());
            pstmt.setDouble(5, object.getPrice());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean update(ImportProduct object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImportProduct findById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImportProduct> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaginatedResults<ImportProduct> select(int page) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
