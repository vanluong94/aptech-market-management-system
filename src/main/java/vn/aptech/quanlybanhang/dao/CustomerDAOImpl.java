/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Customer;
import vn.aptech.quanlybanhang.utilities.DBConnection;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public class CustomerDAOImpl implements CustomerDAO {

    private final static String SQL_SELECT_ALL = "select customers.*, employees.employee_name from customers inner join employees on customers.employee_id = employees.employee_id";
    private final static String SQL_INSERT = "INSERT INTO customers(customer_name, customer_phone, customer_add, employee_id) VALUE(?,?,?,?)";
    private final static String SQL_GET_ONE = "select customers.*, employees.employee_name from customers inner join employees on customers.employee_id = employees.employee_id WHERE customer_id = ?";
    private final static String SQL_FIND_BY_PHONE = "select customers.*, employees.employee_name from customers inner join employees on customers.employee_id = employees.employee_id WHERE customer_phone = ?";
    private final static String SQL_DELETE = "DELETE FROM customers WHERE customer_id = ?";
    private final static String SQL_UPDATE = "UPDATE customers SET customer_name = ?, customer_phone = ?, customer_add = ? WHERE customer_id = ?";

    @Override

    public boolean create(Customer object) throws SQLException {
        int rowsAffected = -1;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setString(1, object.getName());
            pstmt.setString(2, object.getPhone());
            pstmt.setString(3, object.getAddress());
            pstmt.setInt(4, object.getEmployee().getEmployeeId());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        int rowsAffected = -1;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPhone());
            pstmt.setString(3, customer.getAddress());
            pstmt.setInt(4, customer.getId());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
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

        } catch (SQLException e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return rs > 0;
    }

    @Override
    public Customer findById(int id) throws SQLException {
        Customer customer = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setPhone(rs.getString("customer_phone"));
                customer.setAddress(rs.getString("customer_add"));
                customer.getEmployee().setEmployeeId(rs.getInt("employee_id"));
                customer.getEmployee().setName(rs.getString("employee_name"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        List<Customer> customers = new ArrayList<Customer>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setPhone(rs.getString("customer_phone"));
                customer.setAddress(rs.getString("customer_add"));
                customer.getEmployee().setEmployeeId(rs.getInt("employee_id"));
                customer.getEmployee().setName(rs.getString("employee_name"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            throw e;
        }
        return customers;
    }

    @Override
    public PaginatedResults<Customer> select(int page) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Customer findByPhone(String phone) throws SQLException {
        Customer customer = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_FIND_BY_PHONE);
            pstmt.setString(1, phone);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setPhone(rs.getString("customer_phone"));
                customer.setAddress(rs.getString("customer_add"));
                customer.getEmployee().setEmployeeId(rs.getInt("employee_id"));
                customer.getEmployee().setName(rs.getString("employee_name"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return customer;
    }

}
