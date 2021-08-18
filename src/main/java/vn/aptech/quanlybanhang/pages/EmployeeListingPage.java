/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;


public class EmployeeListingPage extends Page {

    @Override
    public void displayContent() {
        
        try {
            EmployeeService employeeService = new EmployeeServiceImpl();
            
            List<Employee> employees = employeeService.findAll();
            List<Object[]> rows = new ArrayList<>();
            
            // transfer data to table row
            for (Employee employee : employees) {
                Object[] row = {
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getAddress(),
                    employee.getPhone(),
                    employee.getDepartment(),
                    employee.getUserName(),
                };
                
                rows.add(row);
            }
            
            String[] headers = {"ID", "Name", "Address", "Phone", "Department", "Username"};
            
            TableUI theTable = new TableUI(headers, rows);
            theTable.display();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeListingPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Danh sach nhan vien";
    }
    
}
