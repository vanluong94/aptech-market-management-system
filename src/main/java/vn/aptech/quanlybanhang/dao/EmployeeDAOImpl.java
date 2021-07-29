/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.utilities.DBConnection;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private final static String SQL_SELECT_ALL = "SELECT * FROM employees";
    private final static String SQL_INSERT = "INSERT INTO employees(employee_name,employee_add,employee_phone,department,username,password) VALUE(?,?,?,?,?,?)";
    private final static String SQL_GET_ONE = "SELECT * FROM employees WHERE employee_id = ?";
    private final static String SQL_DELETE = "DELETE FROM employees WHERE employee_id = ?";
    private final static String SQL_UPDATE = "UPDATE employees SET employee_name = ?, employee_add = ?, employee_phone = ?,department = ?, username = ?, password = ?  WHERE employee_id = ?";
    private final static String SQL_GET_ONE_BY_USERNAME_PASSWORD = "SELECT * FROM employees WHERE username = ? AND password = ?";

    @Override
    public boolean create(Employee object) throws SQLException {
        int rowsAffected = -1;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setString(1, object.getName());
            pstmt.setString(2, object.getAddress());
            pstmt.setString(3, object.getPhone());
            pstmt.setString(4, object.getDepartment());
            pstmt.setString(5, object.getUserName());
            pstmt.setString(6, object.getPassword());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean update(Employee object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return rs > 0;
    }

    @Override
    public Employee findById(int id) throws SQLException {
        Employee employee = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setName(rs.getString("employee_name"));
                employee.setAddress(rs.getString("employee_add"));
                employee.setPhone(rs.getString("employee_phone"));
                employee.setDepartment(rs.getString("department"));
                employee.setUserName(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setName(rs.getString("employee_name"));
                employee.setAddress(rs.getString("employee_add"));
                employee.setPhone(rs.getString("employee_phone"));
                employee.setDepartment(rs.getString("department"));
                employee.setUserName(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
                employees.add(employee);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return employees;
    }

    @Override
    public boolean updateById(Employee object, int id) throws SQLException {
        int rs = -1;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE);
            pstmt.setString(1, object.getName());
            pstmt.setString(2, object.getAddress());
            pstmt.setString(3, object.getPhone());
            pstmt.setString(4, object.getDepartment());
            pstmt.setString(5, object.getUserName());
            pstmt.setString(6, object.getPassword());
            pstmt.setInt(7, id);
            rs = pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return rs > 0;
    }

    @Override
    public Employee findByUsernameAndPassword(String username, String password) throws SQLException {
        Employee employee = null;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE_BY_USERNAME_PASSWORD);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setName(rs.getString("employee_name"));
                employee.setAddress(rs.getString("employee_add"));
                employee.setPhone(rs.getString("employee_phone"));
                employee.setDepartment(rs.getString("department"));
                employee.setUserName(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return employee;
    }

}
