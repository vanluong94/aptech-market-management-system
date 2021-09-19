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
import vn.aptech.quanlybanhang.entities.ImportProduct;
import vn.aptech.quanlybanhang.utilities.DBConnection;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ImportProductDAOImpl implements ImportProductDAO {

    private final static String SQL_SELECT_ALL = "SELECT * FROM v_import_product;";
    private final static String SQL_INSERT = "INSERT INTO `import_products` (`supplier_id`, `product_id`, `employee_id`, `product_quantity`,"
            + " `product_price`) VALUES (?, ?, ?, ?, ?);";

    @Override
    public boolean create(ImportProduct object) throws Exception {
        int rowsAffected = -1;
        try {
            // Do dùng transaction nên không đóng connection ở method này
            // Việc đóng connection sẽ ở method ProductDAOImpl.create()
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setInt(1, object.getSupplier().getId());
            pstmt.setInt(2, object.getProduct().getId());
            pstmt.setInt(3, object.getEmployee().getId());
            pstmt.setInt(4, object.getQuantity());
            pstmt.setDouble(5, object.getPrice());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean update(ImportProduct object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImportProduct findById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImportProduct> findAll() throws Exception {
        List<ImportProduct> importProducts = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ImportProduct importProduct = new ImportProduct();
                importProduct.setId(rs.getInt("import_product_id"));

                importProduct.getSupplier().setId(rs.getInt("supplier_id"));
                importProduct.getSupplier().setName(rs.getString("supplier_name"));

                importProduct.getEmployee().setId(rs.getInt("employee_id"));
                importProduct.getEmployee().setName(rs.getString("employee_name"));

                importProduct.setPrice(rs.getDouble("product_price"));
                importProduct.setQuantity(rs.getInt("product_quantity"));
                importProduct.setCreatedAt(rs.getTimestamp("date_added"));
                importProducts.add(importProduct);
            }
        } catch (Exception e) {
            throw e;
        }
        return importProducts;
    }

    @Override
    public PaginatedResults<ImportProduct> select(int page) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
