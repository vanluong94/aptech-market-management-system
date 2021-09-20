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
import vn.aptech.quanlybanhang.entities.Department;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.utilities.DBConnection;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Admin
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private final static String SQL_SELECT_ALL = "SELECT * FROM employees";
    private final static String SQL_INSERT = "INSERT INTO employees(employee_name,employee_add,employee_phone,department,username,password) VALUE(?,?,?,?,?,?)";
    private final static String SQL_GET_ONE = "SELECT * FROM employees WHERE employee_id = ?";
    private final static String SQL_DELETE = "DELETE FROM employees WHERE employee_id = ?";
    private final static String SQL_UPDATE = "UPDATE employees SET employee_name = ?, employee_add = ?, employee_phone = ?, "
            + "department = ?, username = ?, password = ? WHERE employee_id = ?";
    private final static String SQL_GET_ONE_BY_USERNAME_PASSWORD = "SELECT * FROM employees WHERE username = ? AND password = ?";
    private final static String SQL_FIND_BY_NAME = "SELECT * FROM employees WHERE employee_name LIKE ?";
    private final static String SQL_EXISTS_BY_USERNAME = "SELECT count(employee_id) FROM employees WHERE username = ?";

    @Override
    public boolean create(Employee object) throws Exception {
        int rowsAffected = -1;
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setString(1, object.getName());
            pstmt.setString(2, object.getAddress());
            pstmt.setString(3, object.getPhone());
            pstmt.setString(4, object.getDepartment().name());
            pstmt.setString(5, object.getUsername());
            pstmt.setString(6, object.getPassword());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return rowsAffected > 0;
    }

    /**
     *
     * @param emp
     * @return
     * @throws SQLException
     */
    @Override
    public boolean update(Employee emp) throws Exception {
        int rowsAffected = -1;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE);
            pstmt.setString(1, emp.getName());
            pstmt.setString(2, emp.getAddress());
            pstmt.setString(3, emp.getPhone());
            pstmt.setString(4, emp.getDepartment().name());
            pstmt.setString(5, emp.getUsername());
            pstmt.setString(6, emp.getPassword());
            pstmt.setInt(7, emp.getId());
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        int rs = -1;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, id);
            rs = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        }
        return rs > 0;
    }

    @Override
    public Employee findById(int id) throws Exception {
        Employee employee = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getInt("employee_id"));
                employee.setName(rs.getString("employee_name"));
                employee.setAddress(rs.getString("employee_add"));
                employee.setPhone(rs.getString("employee_phone"));
                employee.setDepartment(Department.valueOf(rs.getString("department")));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("employee_id"));
                employee.setName(rs.getString("employee_name"));
                employee.setAddress(rs.getString("employee_add"));
                employee.setPhone(rs.getString("employee_phone"));
                employee.setDepartment(Department.valueOf(rs.getString("department")));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw e;
        }
        return employees;
    }

    @Override
    public boolean updateById(Employee object, int id) throws Exception {
        int rs = -1;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE);
            pstmt.setString(1, object.getName());
            pstmt.setString(2, object.getAddress());
            pstmt.setString(3, object.getPhone());
            pstmt.setString(4, object.getDepartment().name());
            pstmt.setString(5, object.getUsername());
            pstmt.setString(6, object.getPassword());
            pstmt.setInt(7, id);
            rs = pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return rs > 0;
    }

    @Override
    public Employee findByUsernameAndPassword(String username, String password) throws Exception {
        Employee employee = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ONE_BY_USERNAME_PASSWORD);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getInt("employee_id"));
                employee.setName(rs.getString("employee_name"));
                employee.setAddress(rs.getString("employee_add"));
                employee.setPhone(rs.getString("employee_phone"));
                employee.setDepartment(Department.valueOf(rs.getString("department")));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return employee;
    }

    @Override
    public List<Employee> findByNameEmployee(String username) throws Exception {
        List<Employee> employees = new ArrayList<>();
        if (username.trim().length() > 0) {
            String a = "%" + username + "%";
            try (Connection conn = DBConnection.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement(SQL_FIND_BY_NAME);
                pstmt.setString(1, a);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Employee employee = new Employee();
                    employee.setId(rs.getInt("employee_id"));
                    employee.setName(rs.getString("employee_name"));
                    employee.setAddress(rs.getString("employee_add"));
                    employee.setPhone(rs.getString("employee_phone"));
                    employee.setDepartment(Department.valueOf(rs.getString("department")));
                    employee.setUsername(rs.getString("username"));
                    employee.setPassword(rs.getString("password"));
                    employees.add(employee);
                }
            } catch (SQLException e) {
                throw e;
            }
        }
        return employees;
    }

    @Override
    public PaginatedResults<Employee> select(int page) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existsByUsername(String username) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(SQL_EXISTS_BY_USERNAME);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int numberOfRows = rs.getInt(1);
                return numberOfRows > 0;
            }
        } catch (SQLException e) {
            throw e;
        }
        return false;
    }

}
