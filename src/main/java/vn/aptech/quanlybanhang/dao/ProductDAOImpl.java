/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.ImportProduct;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.ImportProductService;
import vn.aptech.quanlybanhang.service.ImportProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.DBConnection;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class ProductDAOImpl implements ProductDAO {

    private final static int PER_PAGE = 10;

    private final static String SQL_SELECT_ALL = "SELECT * FROM products";
    private final static String SQL_GET_ONE = "SELECT * FROM products WHERE product_id = ?";
    private final static String SQL_GET_BY_CATEGORY_ID = "SELECT * FROM products WHERE category_id = ?";
    private final static String SQL_INSERT = "INSERT INTO `products` (`brand_id`, `category_id`, `employee_id`, `product_name`,"
            + " `product_price`, `product_stock`) VALUES (?, ?, ?, ?, ?, ?);";
    private final static String SQL_DELETE = "DELETE FROM products WHERE product_id = ?";
    private final static String SQL_SELECT_OUT_STOCK = "SELECT "
            + " products.*, categories.category_name, brands.brand_name, employees.employee_name, suppliers.supplier_name"
            + " FROM products"
            + " LEFT JOIN brands ON products.brand_id = brands.brand_id"
            + " LEFT JOIN categories ON products.category_id = categories.category_id"
            + " LEFT JOIN employees ON products.employee_id = employees.employee_id"
            + " LEFT JOIN suppliers ON products.supplier_id = suppliers.supplier_id"
            + " WHERE product_stock = 0"
            + " LIMIT ?,?";
    
    private final ImportProductService importProductService;

    public ProductDAOImpl() {
        this.importProductService = new ImportProductServiceImpl();
    }

    /**
     *
     * @param object
     * @return
     * @throws SQLException
     */
    @Override
    public boolean create(Product object) throws SQLException {
        int rowsAffected = -1;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, object.getBrand().getBrandId());
            pstmt.setInt(2, object.getCategory().getCategoryId());
            pstmt.setInt(3, object.getEmployee().getEmployeeId());
            pstmt.setString(4, object.getName());
            pstmt.setDouble(5, object.getPrice());
            pstmt.setInt(6, object.getQuantityInStock());
            rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    object.setId(rs.getInt(1));
                    ImportProduct importProduct = new ImportProduct();
                    importProduct.setEmployee(object.getEmployee());
                    importProduct.setSupplier(object.getSupplier());
                    importProduct.setProduct(object);
                    importProduct.setQuantity(object.getQuantityInStock());
                    importProduct.setPrice(object.getPrice());
                    if (!importProductService.create(importProduct)) {
                        rowsAffected = -1;
                    }
                }
            }
            conn.commit();
        } catch (SQLException e) {
            throw e;
        } catch (Exception ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        int rowEffected = -1;

        String updateSQL = "UPDATE `products` SET "
                + "`brand_id` = ?"
                + ", `category_id` = ?"
                + ", `employee_id` = ?"
                + ", `product_name` = ?"
                + ", `product_price` = ?"
                + ", `product_stock` = ?"
                + ", `updated_date` = ?"
                + "WHERE product_id = ?;";

        try (PreparedStatement st = DBConnection.getConnection().prepareStatement(updateSQL)) {

            st.setInt(1, product.getBrand().getBrandId());
            st.setInt(2, product.getCategory().getCategoryId());
            st.setInt(3, product.getEmployee().getEmployeeId());
            st.setString(4, product.getName());
            st.setDouble(5, product.getPrice());
            st.setInt(6, product.getQuantityInStock());
            st.setString(7, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s")));
            st.setInt(8, product.getId());

            rowEffected = st.executeUpdate();
        }

        return rowEffected > -1;
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


    @Override
    public PaginatedResults<Product> selectOutOfStock(int page) throws SQLException {

        PaginatedResults<Product> pagination = new PaginatedResults<>(page, PER_PAGE);
        List<Product> products = new ArrayList<>();

        try ( Connection conn = DBConnection.getConnection()) {
            
            // query items
            PreparedStatement stSelect = conn.prepareStatement(SQL_SELECT_OUT_STOCK);
            stSelect.setInt(1, pagination.getOffset());
            stSelect.setInt(2, pagination.getPerPage());
            ResultSet rs = stSelect.executeQuery();

            while (rs.next()) {
                Product product = this.mapRersultSetToObject(rs);

                product.getBrand().setBrandName(rs.getString("brand_name"));
                product.getSupplier().setName(rs.getString("supplier_name"));
                product.getCategory().setCategoryName(rs.getString("category_name"));
                product.getEmployee().setName(rs.getString("employee_name"));

                products.add(product);
            }

            pagination.setResults(products);

            // query count
            String sqlCount = PaginatedResults.parseCountSQL(SQL_SELECT_OUT_STOCK);
            Statement st = DBConnection.getConnection().createStatement();
            ResultSet countRs = st.executeQuery(sqlCount);
            if (countRs.next()) {
                pagination.setTotalItems(countRs.getInt(1));
            }
        }

        return pagination;
    }

    private Product mapRersultSetToObject(ResultSet rs) throws SQLException {
        Product product = new Product();
        try {
            product.setId(rs.getInt("product_id"));
            product.getBrand().setBrandId(rs.getInt("brand_id"));
            product.getSupplier().setId(rs.getInt("supplier_id"));
            product.getCategory().setCategoryId(rs.getInt("category_id"));
            product.getEmployee().setEmployeeId(rs.getInt("employee_id"));
            product.setName(rs.getString("product_name"));
            product.setPrice(rs.getDouble("product_price"));
            product.setQuantityInStock(rs.getInt("product_stock"));
        } catch (SQLException ex) {
            throw ex;
        }
        return product;
    }
    
    @Override
    public PaginatedResults<Product> select(int page) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
