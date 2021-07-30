/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;


public class EmployeeDetailPage extends Page {

    @Override
    public void displayContent() {
        try {
            EmployeeService employeeService = new EmployeeServiceImpl();
            System.out.print("Nhap ID nhan vien : ");
            int employeeId = AppScanner.getScanner().nextInt();
            Employee employee = employeeService.findById(employeeId);
            if (employee == null) {
                System.out.println("Khong tim thay ID nhan vien");
            } else {
                employee.showOne();
                
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeDetailPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Xem chi tiet Nhan Vien";
    }
    
}
