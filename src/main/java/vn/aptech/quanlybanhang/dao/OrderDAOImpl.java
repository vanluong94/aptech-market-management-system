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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.entities.OrderItem;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
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
            + "product_price) VALUE (?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE_QTY_PRODUCT = "UPDATE products SET product_stock = product_stock - ? WHERE product_id = ?";
    private final static String SQL_ORDER_DETAIL_OF_CASHIER = "SELECT "
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
            + "  orders.order_id = ? AND employees.employee_id = ?";
    static LocalDate myTime = LocalDate.now();
    private final static String SQL_GET_BY_DATE = "SELECT orders.*,employees.employee_name, employees.employee_id,customers.customer_name,customers.customer_id FROM orders JOIN employees ON employees.employee_id = orders.employee_id LEFT JOIN customers ON customers.customer_id = orders.customer_id WHERE orders.order_date LIKE '" + myTime + "%' AND employees.employee_id = ?";
    private final static String SQL_GET_ONE = "SELECT "
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
            + "  orders.order_id = ? ";

    private final static String SQL_GET_PRODUCTS = "SELECT * FROM order_items WHERE order_id = ?";

    private final ProductService productService;

    public OrderDAOImpl() {
        this.productService = new ProductServiceImpl();
    }

    @Override
    public boolean create(Order object) throws SQLException {
        int rowsAffected = -1;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, object.getCustomer().getId());
            pstmt.setInt(2, object.getEmployee().getEmployeeId());
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
                        // Set nốt Discount và Discount Price
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
    public boolean update(Order object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order findById(int id) throws SQLException {
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
                order.getEmployee().setEmployeeId(rs.getInt("employee_id"));

                order.getCustomer().setName(rs.getString("customer_name"));
                order.getCustomer().setId(rs.getInt("customer_id"));

                return order;
            }
        }
        return null;
    }

    @Override
    public List findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaginatedResults<Order> select(int page) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> todayOrder() throws SQLException {
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_BY_DATE);
            pstmt.setInt(1, AuthServiceImpl.getCurrentEmployee().getEmployeeId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.getCustomer().setName(rs.getString("customer_name"));
                order.getEmployee().setName(rs.getString("employee_name"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setAmount(rs.getDouble("amount"));
                orders.add(order);
            }
        } catch (Exception e) {
            throw e;
        }
        return orders;
    }

    @Override
    public Order findByCashierId(int id) throws SQLException {

        Order order = null;
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_ORDER_DETAIL_OF_CASHIER);
            pstmt.setInt(1, id);
            pstmt.setInt(2, AuthServiceImpl.getCurrentEmployee().getEmployeeId());
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

    public List<OrderItem> getOrderItems(Order order) {
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

}
