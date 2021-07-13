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
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.utilities.DBConnection;


public class AuthDAOImpl implements AuthDAO {

    @Override
    public Employee login(String username, String password) {
        Employee emp = null;
        try {
            Connection conn = DBConnection.getConnection();
            String queryString = "SELECT * FROM employees WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(queryString);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                emp = new Employee();
                emp.setEmployeeId(rs.getInt("employeeId"));
                emp.setUserName(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emp;
    }

    @Override
    public Employee findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
