/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.DateCommon;
import vn.aptech.quanlybanhang.constant.Constant;
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.entities.ImportProduct;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.service.ImportProductService;
import vn.aptech.quanlybanhang.service.ImportProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.DBConnection;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public class ProductDAOImpl implements ProductDAO {

    private final static String SQL_SELECT_ALL = "SELECT products.*,suppliers.supplier_id,suppliers.supplier_name,brands.brand_name,brands.brand_id,categories.category_id,categories.category_name,employees.employee_name, employees.employee_id"
            + " FROM products"
            + " LEFT JOIN brands ON brands.brand_id = products.brand_id"
            + " LEFT JOIN categories ON categories.category_id = products.category_id"
            + " LEFT JOIN employees ON employees.employee_id = products.employee_id"
            + " LEFT JOIN suppliers ON suppliers.supplier_id = products.supplier_id"
            + " ORDER BY products.product_id DESC"
            + " LIMIT ?, ? ";

    private final static String SQL_GET_ONE
            = " SELECT "
            + "     products.*,"
            + "     d_products.*,"
            + "     suppliers.supplier_name,"
            + "     brands.brand_name,"
            + "     categories.category_name,"
            + "     employees.employee_name"
            + " FROM products"
            + " JOIN brands ON brands.brand_id = products.brand_id"
            + " JOIN categories ON categories.category_id = products.category_id"
            + " JOIN employees ON employees.employee_id = products.employee_id"
            + " JOIN suppliers ON suppliers.supplier_id = products.supplier_id"
            + " LEFT JOIN discount_product as d_products ON ( "
            + "     d_products.product_id = products.product_id "
            + "     AND ? BETWEEN d_products.start_date "
            + "     AND d_products.end_date "
            + " )"
            + " WHERE products.product_id = ?";

    private final static String SQL_GET_BY_CATEGORY_ID = "SELECT products.*,suppliers.supplier_id,suppliers.supplier_name,brands.brand_name,brands.brand_id,categories.category_id,categories.category_name,employees.employee_name,employees.employee_id"
            + " FROM products"
            + " JOIN brands ON brands.brand_id = products.brand_id"
            + " JOIN categories ON categories.category_id = products.category_id"
            + " JOIN employees ON employees.employee_id = products.employee_id"
            + " JOIN suppliers ON suppliers.supplier_id = products.supplier_id"
            + " WHERE products.category_id = ? "
            + " LIMIT ?,?";

    private final static String SQL_INSERT = "INSERT INTO `products` (`brand_id`, `category_id`, `employee_id`, `supplier_id`, `product_name`,"
            + " `product_price`, `product_stock`) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private final static String SQL_DELETE = "DELETE FROM products WHERE product_id = ?";
    private final static String SQL_SELECT_POPULAR_ORDER = "SELECT products.*, categories.category_name, brands.brand_name,"
            + " employees.employee_name,"
            + " suppliers.supplier_name,"
            + " SUM(order_items.product_quantity) AS unitsOnOrder"
            + " FROM order_items"
            + " INNER JOIN orders ON orders.order_id = order_items.order_id"
            + " RIGHT JOIN products ON products.product_id = order_items.product_id"
            + " INNER JOIN brands ON products.brand_id = brands.brand_id"
            + " INNER JOIN categories ON products.category_id = categories.category_id"
            + " INNER JOIN employees ON products.employee_id = employees.employee_id"
            + " INNER JOIN suppliers ON products.supplier_id = suppliers.supplier_id"
            + " WHERE orders.order_date BETWEEN ? AND ?"
            + " GROUP BY products.product_id"
            + " ORDER BY unitsOnOrder DESC"
            + " LIMIT ?, ?";
    private final static String SQL_SELECT_POPULAR_ORDER_COUNT = "SELECT COUNT(DISTINCT order_items.product_id) FROM order_items"
            + " INNER JOIN orders ON orders.order_id = order_items.order_id"
            + " RIGHT JOIN products ON products.product_id = order_items.product_id"
            + " INNER JOIN brands ON products.brand_id = brands.brand_id"
            + " INNER JOIN categories ON products.category_id = categories.category_id"
            + " INNER JOIN employees ON products.employee_id = employees.employee_id"
            + " INNER JOIN suppliers ON products.supplier_id = suppliers.supplier_id"
            + " WHERE orders.order_date BETWEEN ? AND ?";
    private final static String SQL_SELECT_OUT_STOCK = "SELECT "
            + " products.*, categories.category_name, brands.brand_name, employees.employee_name, suppliers.supplier_name"
            + " FROM products"
            + " LEFT JOIN brands ON products.brand_id = brands.brand_id"
            + " LEFT JOIN categories ON products.category_id = categories.category_id"
            + " LEFT JOIN employees ON products.employee_id = employees.employee_id"
            + " LEFT JOIN suppliers ON products.supplier_id = suppliers.supplier_id"
            + " WHERE product_stock = 0"
            + " LIMIT ?,?";
    private final static String SQL_STATISTIC_AMOUNT = "SELECT SUM(amount) AS amount"
            + " FROM orders WHERE date(order_date) BETWEEN ? AND ?";

    private final static String SQL_GET_BY_NAME = "SELECT "
            + " products.*, categories.category_name, brands.brand_name, employees.employee_name, suppliers.supplier_name"
            + " FROM products"
            + " LEFT JOIN brands ON products.brand_id = brands.brand_id"
            + " LEFT JOIN categories ON products.category_id = categories.category_id"
            + " LEFT JOIN employees ON products.employee_id = employees.employee_id"
            + " LEFT JOIN suppliers ON products.supplier_id = suppliers.supplier_id"
            + " WHERE products.product_name LIKE ? "
            + " LIMIT ?,?";
    private final ImportProductService importProductService;

    public ProductDAOImpl() {
        this.importProductService = new ImportProductServiceImpl();
    }

    /**
     *
     * @param object
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public boolean create(Product object) throws Exception {
        int rowsAffected = -1;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, object.getBrand().getId());
            pstmt.setInt(2, object.getCategory().getId());
            pstmt.setInt(3, object.getEmployee().getId());
            pstmt.setInt(4, object.getSupplier().getId());
            pstmt.setString(5, object.getName());
            pstmt.setDouble(6, object.getPrice());
            pstmt.setInt(7, object.getQuantityInStock());
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
    public boolean update(Product product) throws Exception {
        int rowsAffected = -1;

        String updateSQL = "UPDATE `products` SET "
                + "`brand_id` = ?"
                + ", `category_id` = ?"
                + ", `employee_id` = ?"
                + ", `product_name` = ?"
                + ", `product_price` = ?"
                + ", `product_stock` = ?"
                + ", `updated_date` = ?"
                + " WHERE product_id = ?;";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement st = conn.prepareStatement(updateSQL);
            st.setInt(1, product.getBrand().getId());
            st.setInt(2, product.getCategory().getId());
            st.setInt(3, product.getEmployee().getId());
            st.setString(4, product.getName());
            st.setDouble(5, product.getPrice());
            st.setInt(6, product.getQuantityInStock());
            st.setString(7, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s")));
            st.setInt(8, product.getId());

            rowsAffected = st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }

        return rowsAffected > -1;
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement delPDiscountStmt = conn.prepareStatement("DELETE FROM discount_product WHERE product_id = ?");
                PreparedStatement delPImportStmt = conn.prepareStatement("DELETE FROM import_products WHERE product_id = ?");
                PreparedStatement delProductStmt = conn.prepareStatement(SQL_DELETE);
        ) {
            
            conn.setAutoCommit(false);
            delProductStmt.setInt(1, id);
            delPDiscountStmt.setInt(1, id);
            delPImportStmt.setInt(1, id);
            
            delPDiscountStmt.executeUpdate();
            delPImportStmt.executeUpdate();
            
            if (delProductStmt.executeUpdate() > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
            }
            
            return false;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Product findById(int id) throws Exception {
        Product product = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE);
            pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(2, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                product = this.mapRersultSetToObject(rs);
                product.getCategory().setName(rs.getString("category_name"));
                product.getEmployee().setName(rs.getString("employee_name"));
                product.getSupplier().setName(rs.getString("supplier_name"));
                product.getBrand().setName(rs.getString("brand_name"));

                if (rs.getInt("d_products.discount_product_id") > 0) {
                    product.getDiscount().setId(rs.getInt("d_products.discount_product_id"));
                    product.getDiscount().setDiscountPercentage(rs.getFloat("d_products.discount"));
                    product.getDiscount().setDiscountId(rs.getInt("d_products.discount_id"));
                    product.getDiscount().setStartDate(rs.getDate("d_products.start_date"));
                    product.getDiscount().setEndDate(rs.getDate("d_products.end_date"));
                }
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.getBrand().setName(rs.getString("brand_name"));
                product.getCategory().setName(rs.getString("category_name"));
                product.getSupplier().setName(rs.getString("supplier_name"));
                product.getEmployee().setName(rs.getString("employee_name"));
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
    public PaginatedResults<Product> findByCategoryId(int page, int id) throws Exception {
        PaginatedResults<Product> pagination = new PaginatedResults<>(page, Constant.PER_PAGE);
        List<Product> products = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        ResultSet countRs = null;

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_BY_CATEGORY_ID);
            pstmt.setInt(1, id);
            pstmt.setInt(2, pagination.getOffset());
            pstmt.setInt(3, pagination.getPerPage());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.getBrand().setName(rs.getString("brand_name"));
                product.getCategory().setName(rs.getString("category_name"));
                product.getSupplier().setName(rs.getString("supplier_name"));
                product.getEmployee().setName(rs.getString("employee_name"));
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
            pagination.setResults(products);
            
            try(PreparedStatement countStmt = conn.prepareStatement(PaginatedResults.parseCountSQL(SQL_GET_BY_CATEGORY_ID))) {
                countStmt.setInt(1, id);
                countRs = countStmt.executeQuery();
                if (countRs.next()) {
                    pagination.setTotalItems(countRs.getInt(1));
                }
            } 
            
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (countRs != null) {
                countRs.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return pagination;
    }

    @Override
    public PaginatedResults<Product> findByName(int page, String name) throws Exception {
        PaginatedResults<Product> pagination = new PaginatedResults<>(page, Constant.PER_PAGE);
        List<Product> products = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        ResultSet countRs = null;

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_BY_NAME);
            pstmt.setString(1, "%" + name + "%");
            pstmt.setInt(2, pagination.getOffset());
            pstmt.setInt(3, pagination.getPerPage());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.getBrand().setName(rs.getString("brand_name"));
                product.getCategory().setName(rs.getString("category_name"));
                product.getSupplier().setName(rs.getString("supplier_name"));
                product.getEmployee().setName(rs.getString("employee_name"));
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
            pagination.setResults(products);
            
            try(PreparedStatement countStmt = conn.prepareStatement(PaginatedResults.parseCountSQL(SQL_GET_BY_NAME))) {
                countStmt.setString(1, "%" + name + "%");
                countRs = countStmt.executeQuery();
                if (countRs.next()) {
                    pagination.setTotalItems(countRs.getInt(1));
                }
            } 
            
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (countRs != null) {
                countRs.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return pagination;
    }

    @Override
    public PaginatedResults<Product> selectOutOfStock(int page) throws Exception {

        PaginatedResults<Product> pagination = new PaginatedResults<>(page, Constant.PER_PAGE);
        List<Product> products = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            // query items
            PreparedStatement stSelect = conn.prepareStatement(SQL_SELECT_OUT_STOCK);
            stSelect.setInt(1, pagination.getOffset());
            stSelect.setInt(2, pagination.getPerPage());
            ResultSet rs = stSelect.executeQuery();

            while (rs.next()) {
                Product product = this.mapRersultSetToObject(rs);

                product.getBrand().setName(rs.getString("brand_name"));
                product.getSupplier().setName(rs.getString("supplier_name"));
                product.getCategory().setName(rs.getString("category_name"));
                product.getEmployee().setName(rs.getString("employee_name"));

                products.add(product);
            }

            pagination.setResults(products);

            // query count
            try(Statement st = conn.createStatement()) {
                String sqlCount = PaginatedResults.parseCountSQL(SQL_SELECT_OUT_STOCK);
                ResultSet countRs = st.executeQuery(sqlCount);
                if (countRs.next()) {
                    pagination.setTotalItems(countRs.getInt(1));
                }
            }
            
        }

        return pagination;
    }

    private Product mapRersultSetToObject(ResultSet rs) throws Exception {
        Product product = new Product();
        try {
            product.setId(rs.getInt("product_id"));
            product.getBrand().setId(rs.getInt("brand_id"));
            product.getSupplier().setId(rs.getInt("supplier_id"));
            product.getCategory().setId(rs.getInt("category_id"));
            product.getEmployee().setId(rs.getInt("employee_id"));
            product.setName(rs.getString("product_name"));
            product.setPrice(rs.getDouble("product_price"));
            product.setQuantityInStock(rs.getInt("product_stock"));
            product.setCreatedAt(rs.getTimestamp("created_date"));
            product.setUpdatedAt(rs.getTimestamp("updated_date"));
        } catch (SQLException ex) {
            throw ex;
        }
        return product;
    }

    @Override
    public PaginatedResults<Product> select(int page) throws Exception {
        
        PaginatedResults<Product> pagination = new PaginatedResults<>(page, Constant.PER_PAGE);
        List<Product> products = new ArrayList<>();

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement st = conn.prepareStatement(SQL_SELECT_ALL);
        ) {
            
            st.setInt(1, pagination.getOffset());
            st.setInt(2, pagination.getPerPage());
            
            // query items
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product product = this.mapRersultSetToObject(rs);

                product.getBrand().setName(rs.getString("brand_name"));
                product.getSupplier().setName(rs.getString("supplier_name"));
                product.getCategory().setName(rs.getString("category_name"));
                product.getEmployee().setName(rs.getString("employee_name"));

                products.add(product);
            }

            pagination.setResults(products);

            // query count
            String sqlCount = PaginatedResults.parseCountSQL(SQL_SELECT_ALL);
            try(Statement stCount = conn.createStatement()){
                ResultSet countRs = stCount.executeQuery(sqlCount);
                if (countRs.next()) {
                    pagination.setTotalItems(countRs.getInt(1));
                }
            }
        }

        return pagination;
    }

    @Override
    public PaginatedResults<Product> findAllByOrderByUnitsOnOrderDesc(int page, String fromDate, String toDate) throws SQLException {
        PaginatedResults<Product> pagination = new PaginatedResults<>(page, Constant.PER_PAGE);
        List<Product> products = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        ResultSet countRs = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_POPULAR_ORDER);
            pstmt.setDate(1, new java.sql.Date(DateCommon.convertStringToDateByPattern(fromDate, Constant.DATE_FORMAT).getTime()));
            pstmt.setDate(2, new java.sql.Date(DateCommon.convertStringToDateByPattern(toDate, Constant.DATE_FORMAT).getTime()));
            pstmt.setInt(3, pagination.getOffset());
            pstmt.setInt(4, pagination.getPerPage());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = this.mapRersultSetToObject(rs);
                product.setUnitsOnOrder(rs.getInt("unitsOnOrder"));
                product.getBrand().setName(rs.getString("brand_name"));
                product.getSupplier().setName(rs.getString("supplier_name"));
                product.getCategory().setName(rs.getString("category_name"));
                product.getEmployee().setName(rs.getString("employee_name"));
                products.add(product);
            }

            pagination.setResults(products);
            pstmt = conn.prepareStatement(SQL_SELECT_POPULAR_ORDER_COUNT);
            pstmt.setDate(1, new java.sql.Date(DateCommon.convertStringToDateByPattern(fromDate, Constant.DATE_FORMAT).getTime()));
            pstmt.setDate(2, new java.sql.Date(DateCommon.convertStringToDateByPattern(toDate, Constant.DATE_FORMAT).getTime()));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pagination.setTotalItems(rs.getInt(1));
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (countRs != null) {
                countRs.close();
            }
            if (st != null) {
                st.close();
            }
        }

        return pagination;
    }

    @Override
    public double getStatisticAmount(java.sql.Date fromDate, java.sql.Date toDate) throws Exception {
        double amount = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try (Connection conn = DBConnection.getConnection()) {
            pstmt = conn.prepareStatement(SQL_STATISTIC_AMOUNT);
            pstmt.setDate(1, fromDate);
            pstmt.setDate(2, toDate);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                amount = rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return amount;
    }

    @Override
    public Product findFirstProductByBrand(Brand brand) throws Exception {
        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM products WHERE brand_id = ? LIMIT 0,1");
        ){
            st.setInt(1, brand.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return this.mapRersultSetToObject(rs);
            }
        }
        return null;
    }

    @Override
    public Product findFirstProductBySupplier(Supplier sup) throws Exception {
        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM products WHERE supplier_id = ? LIMIT 0,1");
        ){
            st.setInt(1, sup.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return this.mapRersultSetToObject(rs);
            }
        }
        return null;
    }

    @Override
    public Product findFirstProductByCategory(Category cat) throws Exception {
        try(
                Connection conn = DBConnection.getConnection();
                PreparedStatement st = conn.prepareStatement("SELECT * FROM products WHERE category_id = ? LIMIT 0,1");
        ){
            st.setInt(1, cat.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return this.mapRersultSetToObject(rs);
            }
        }
        return null;
    }

    @Override
    public boolean productHasOrder(Product product) throws Exception {
        try(
            Connection conn = DBConnection.getConnection();
            PreparedStatement st = conn.prepareStatement("SELECT * FROM order_items WHERE product_id = ? LIMIT 0,1");
        ){
            st.setInt(1, product.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }

}
