/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.DateCommon;
import vn.aptech.quanlybanhang.constant.Constant;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.entities.OrderItem;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.utilities.DBConnection;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class OrderDAOImpl implements OrderDAO {

    private final static String SQL_INSERT = "INSERT INTO orders(customer_id, employee_id, amount, order_date) VALUE (?, ?, ?, ?)";
    private final static String SQL_INSERT_ORDER_ITEMS = "INSERT INTO order_items(order_id, product_id, product_name, product_quantity, "
            + "product_price, discount_product_id, discount_price) VALUE (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE_QTY_PRODUCT = "UPDATE products SET product_stock = product_stock - ? WHERE product_id = ?";
    private final static String SQL_SELECT_ALL = "SELECT "
            + "  orders.*, "
            + "  employees.employee_name, "
            + "  customers.customer_name "
            + " FROM "
            + "  orders "
            + "  JOIN employees ON employees.employee_id = orders.employee_id "
            + "  LEFT JOIN customers ON customers.customer_id = orders.customer_id "
            + " ORDER BY order_date DESC "
            + " LIMIT ?, ?";
    private final static String SQL_ORDER_DETAIL_OF_CASHIER = "SELECT "
            + "  orders.*, "
            + "  employees.employee_name, "
            + "  customers.customer_name "
            + " FROM "
            + "  orders "
            + "  JOIN employees ON employees.employee_id = orders.employee_id "
            + "  LEFT JOIN customers ON customers.customer_id = orders.customer_id "
            + " WHERE "
            + "  orders.order_id = ? AND employees.employee_id = ?";
    private final static String SQL_GET_TODAY_ORDERS = "SELECT orders.*,employees.employee_name, employees.employee_id,customers.customer_name,customers.customer_id FROM orders JOIN employees ON employees.employee_id = orders.employee_id LEFT JOIN customers ON customers.customer_id = orders.customer_id WHERE date(orders.order_date) = ? AND employees.employee_id = ? ORDER BY order_date DESC LIMIT ?,?";
    private final static String SQL_CASHIER_STATISTICS = "SELECT orders.*,employees.employee_name,customers.customer_name"
            + " FROM orders"
            + " JOIN employees ON employees.employee_id = orders.employee_id"
            + " LEFT JOIN customers ON customers.customer_id = orders.customer_id "
            + " WHERE orders.order_date BETWEEN ? AND ? AND employees.employee_id = ?"
            + " ORDER BY order_date DESC "
            + " LIMIT ?,?";
    private final static String SQL_GET_ONE = "SELECT "
            + " orders.*, "
            + " employees.employee_name, "
            + " customers.customer_name, "
            + " customers.customer_phone "
            + " FROM "
            + " orders "
            + " LEFT JOIN employees ON employees.employee_id = orders.employee_id "
            + " LEFT JOIN customers ON customers.customer_id = orders.customer_id "
            + " WHERE orders.order_id = ?";
    private final static String SQL_GET_BY_CUSTOMER_PHONE = "SELECT "
            + " orders.*, "
            + " employees.employee_name, "
            + " customers.customer_name, "
            + " customers.customer_phone "
            + " FROM customers "
            + " INNER JOIN orders ON customers.customer_id = orders.customer_id "
            + " LEFT JOIN employees ON employees.employee_id = orders.employee_id "
            + " WHERE customers.customer_phone LIKE ? "
            + " ORDER BY order_date DESC "
            + " LIMIT ?,?";

    private final static String SQL_GET_PRODUCTS = "SELECT * FROM order_items WHERE order_id = ?";
    private final static String SQL_GET_ONE_BY_CUSTOMER_ID = "SELECT "
            + " orders.*, "
            + " employees.employee_name, "
            + " customers.customer_name "
            + " FROM "
            + " orders "
            + " JOIN employees ON employees.employee_id = orders.employee_id "
            + " LEFT JOIN customers ON customers.customer_id = orders.customer_id "
            + " WHERE orders.customer_id = ?";

    private final static String SQL_GET_BY_TIME_RANGE = "SELECT "
            + "  orders.*, "
            + "  employees.employee_name, "
            + "  employees.employee_id, "
            + "  customers.customer_name, "
            + "  customers.customer_id "
            + " FROM "
            + "  orders "
            + "  JOIN employees ON employees.employee_id = orders.employee_id "
            + "  LEFT JOIN customers ON customers.customer_id = orders.customer_id "
            + " WHERE "
            + "  date(order_date) BETWEEN ? AND ?  "
            + " ORDER BY order_date DESC "
            + " LIMIT ?, ?";

    @Override
    public boolean create(Order object) throws Exception {
        int rowsAffected = -1;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            if (object.getCustomer().getId() == -1) {
                pstmt.setNull(1, Types.INTEGER);
            } else {
                pstmt.setInt(1, object.getCustomer().getId());
            }
            pstmt.setInt(2, object.getEmployee().getId());
            pstmt.setDouble(3, object.getAmount());
            pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    object.setId(rs.getInt(1)); //lay id tra ve
                    for (OrderItem orderItem : object.getOrderItems()) {
                        pstmt = conn.prepareStatement(SQL_INSERT_ORDER_ITEMS);
                        pstmt.setInt(1, object.getId());
                        pstmt.setInt(2, orderItem.getProduct().getId());
                        pstmt.setString(3, orderItem.getProductName());
                        pstmt.setInt(4, orderItem.getQuantity());
                        pstmt.setDouble(5, orderItem.getProductPrice());
                        if (orderItem.getDiscount().getId() == -1) {
                            pstmt.setNull(6, Types.INTEGER);
                        } else {
                            pstmt.setInt(6, orderItem.getDiscount().getId());
                        }
                        pstmt.setDouble(7, orderItem.getDiscountPrice());

                        rowsAffected = pstmt.executeUpdate();
                        // Trừ số lượng sản phẩm trong kho nữa
                        if (rowsAffected > 0) {
                            pstmt = conn.prepareStatement(SQL_UPDATE_QTY_PRODUCT);
                            pstmt.setInt(1, orderItem.getQuantity());
                            pstmt.setInt(2, orderItem.getProduct().getId());
                            rowsAffected = pstmt.executeUpdate();
                        }
                    }
                }
            }
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } catch (Exception ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean update(Order object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order findById(int id) throws Exception {

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement st = conn.prepareStatement(SQL_GET_ONE);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setAmount(rs.getDouble("amount"));

                order.getEmployee().setName(rs.getString("employee_name"));
                order.getEmployee().setId(rs.getInt("employee_id"));

                order.getCustomer().setName(rs.getString("customer_name"));
                order.getCustomer().setId(rs.getInt("customer_id"));

                return order;
            }
        }
        return null;
    }

    /**
     *
     * @return @throws Exception
     */
    @Override
    public List<Order> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaginatedResults<Order> select(int page) throws Exception {
        PaginatedResults<Order> pagination = new PaginatedResults<>(page, Constant.PER_PAGE);
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            // query items
            PreparedStatement stSelect = conn.prepareStatement(SQL_SELECT_ALL);
            stSelect.setInt(1, pagination.getOffset());
            stSelect.setInt(2, pagination.getPerPage());

            ResultSet rs = stSelect.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setAmount(rs.getDouble("amount"));

                order.getEmployee().setName(rs.getString("employee_name"));
                order.getEmployee().setId(rs.getInt("employee_id"));

                order.getCustomer().setName(rs.getString("customer_name"));
                order.getCustomer().setId(rs.getInt("customer_id"));

                orders.add(order);
            }

            pagination.setResults(orders);

            // query count
            try (Statement st = conn.createStatement()) {
                String sqlCount = PaginatedResults.parseCountSQL(SQL_SELECT_ALL);
                ResultSet countRs = st.executeQuery(sqlCount);
                if (countRs.next()) {
                    pagination.setTotalItems(countRs.getInt(1));
                }
            }
            
        }
        return pagination;
    }

    @Override
    public List<OrderItem> getOrderItems(Order order) throws Exception {
        List<OrderItem> items = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement st = conn.prepareStatement(SQL_GET_PRODUCTS);
            st.setInt(1, order.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem(
                        order,
                        rs.getInt("order_item_id"),
                        rs.getString("product_name"),
                        rs.getInt("product_quantity"),
                        rs.getDouble("product_price"),
                        rs.getDouble("discount_price")
                );

                items.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return items;
    }

    @Override
    public PaginatedResults<Order> todayOrder(int page) throws Exception {
        PaginatedResults<Order> pagination = new PaginatedResults<>(page, Constant.PER_PAGE);
        List<Order> orders = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        ResultSet countRs = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_TODAY_ORDERS);
            pstmt.setDate(1, DateCommon.convertUtilDateToSqlDate(new Date()));
            pstmt.setInt(2, AuthServiceImpl.getCurrentEmployee().getId());
            pstmt.setInt(3, pagination.getOffset());
            pstmt.setInt(4, pagination.getPerPage());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.getCustomer().setName(rs.getString("customer_name"));
                order.getEmployee().setName(rs.getString("employee_name"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setAmount(rs.getDouble("amount"));
                orders.add(order);
            }
            pagination.setResults(orders);
            
            try(PreparedStatement countStmt = conn.prepareStatement(PaginatedResults.parseCountSQL(SQL_GET_TODAY_ORDERS))) {
                countStmt.setDate(1, DateCommon.convertUtilDateToSqlDate(new Date()));
                countStmt.setInt(2, AuthServiceImpl.getCurrentEmployee().getId());
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
    public Order findByCashierId(int id) throws SQLException {

        Order order = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_ORDER_DETAIL_OF_CASHIER);
            pstmt.setInt(1, id);
            pstmt.setInt(2, AuthServiceImpl.getCurrentEmployee().getId());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                order = new Order();
                order.setId(rs.getInt("order_id"));
                order.getCustomer().setName(rs.getString("customer_name"));
                order.getEmployee().setName(rs.getString("employee_name"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setAmount(rs.getDouble("amount"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.maybeCloseConnection();
        }
        return order;
    }

    @Override
    public PaginatedResults<Order> CashierStatistics(int page, Date fromDate, Date toDate) throws Exception {
        PaginatedResults<Order> pagination = new PaginatedResults<>(page, Constant.PER_PAGE);
        List<Order> orders = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        ResultSet countRs = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_CASHIER_STATISTICS);
            pstmt.setDate(1, DateCommon.convertUtilDateToSqlDate(fromDate));
            pstmt.setDate(2, DateCommon.convertUtilDateToSqlDate(toDate));
            pstmt.setInt(3, AuthServiceImpl.getCurrentEmployee().getId());
            pstmt.setInt(4, pagination.getOffset());
            pstmt.setInt(5, pagination.getPerPage());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.getCustomer().setName(rs.getString("customer_name"));
                order.getEmployee().setName(rs.getString("employee_name"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setAmount(rs.getDouble("amount"));
                orders.add(order);
            }
            pagination.setResults(orders);
            
            try(PreparedStatement countStmt = conn.prepareStatement(PaginatedResults.parseCountSQL(SQL_CASHIER_STATISTICS))) {
                countStmt.setDate(1, DateCommon.convertUtilDateToSqlDate(fromDate));
                countStmt.setDate(2, DateCommon.convertUtilDateToSqlDate(toDate));
                countStmt.setInt(3, AuthServiceImpl.getCurrentEmployee().getId());
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
    public PaginatedResults<Order> findByDateRange(Date fromDate, Date toDate, int page) throws Exception {

        PaginatedResults<Order> pagination = new PaginatedResults<>(page, Constant.PER_PAGE);
        List<Order> orders = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            // query items
            PreparedStatement stSelect = conn.prepareStatement(SQL_GET_BY_TIME_RANGE);
            stSelect.setDate(1, DateCommon.convertUtilDateToSqlDate(fromDate));
            stSelect.setDate(2, DateCommon.convertUtilDateToSqlDate(toDate));
            stSelect.setInt(3, pagination.getOffset());
            stSelect.setInt(4, pagination.getPerPage());

            ResultSet rs = stSelect.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setAmount(rs.getDouble("amount"));

                order.getEmployee().setName(rs.getString("employee_name"));
                order.getEmployee().setId(rs.getInt("employee_id"));

                order.getCustomer().setName(rs.getString("customer_name"));
                order.getCustomer().setId(rs.getInt("customer_id"));

                orders.add(order);
            }

            pagination.setResults(orders);

            // query count
            try (PreparedStatement stCount = conn.prepareStatement(PaginatedResults.parseCountSQL(SQL_GET_BY_TIME_RANGE))) {
                stCount.setDate(1, DateCommon.convertUtilDateToSqlDate(fromDate));
                stCount.setDate(2, DateCommon.convertUtilDateToSqlDate(toDate));

                ResultSet countRs = stCount.executeQuery();
                if (countRs.next()) {
                    pagination.setTotalItems(countRs.getInt(1));
                }
            }
            
        }
        return pagination;

    }

    @Override
    public Order findByCustomerId(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement st = conn.prepareStatement(SQL_GET_ONE_BY_CUSTOMER_ID);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setAmount(rs.getDouble("amount"));

                order.getEmployee().setName(rs.getString("employee_name"));
                order.getEmployee().setId(rs.getInt("employee_id"));

                order.getCustomer().setName(rs.getString("customer_name"));
                order.getCustomer().setId(rs.getInt("customer_id"));

                return order;
            }
        }
        return null;
    }

    @Override
    public PaginatedResults<Order> findByCustomerPhone(int page, String phone) throws Exception {
        PaginatedResults<Order> pagination = new PaginatedResults<>(page, Constant.PER_PAGE);
        List<Order> orders = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        ResultSet countRs = null;

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_BY_CUSTOMER_PHONE);
            pstmt.setString(1, "%" + phone + "%");
            pstmt.setInt(2, pagination.getOffset());
            pstmt.setInt(3, pagination.getPerPage());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.getCustomer().setName(rs.getString("customer_name"));
                order.getCustomer().setPhone(rs.getString("customer_phone"));
                order.getEmployee().setName(rs.getString("employee_name"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setAmount(rs.getDouble("amount"));
                orders.add(order);
            }
            pagination.setResults(orders);
            

            try (PreparedStatement stCount = conn.prepareStatement(PaginatedResults.parseCountSQL(SQL_GET_BY_CUSTOMER_PHONE))) {
                stCount.setString(1, "%" + phone + "%");

                countRs = stCount.executeQuery();
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

}
