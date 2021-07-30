/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.menu.AdminMenu;
import vn.aptech.quanlybanhang.menu.MainMenu;
import vn.aptech.quanlybanhang.service.AuthService;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;
import vn.aptech.quanlybanhang.ui.HeaderUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;


public class AuthPage extends Page {
    
    @Override
    public void start(){
        HeaderUI.display(this.getTitle());
        this.displayContent();

    }
    
    @Override
    public void displayContent() {
           
        AuthService authService = new AuthServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();
        
        String username = AppScanner.scanStringWithMessage("Nhap tai khoan: ");
        String password = AppScanner.scanStringWithMessage("Nhap mat khau: ");
        
        // kiem tra khong duoc de trong
        Employee employee = new Employee(username, password);
        
        Employee emp = authService.login(employee);
        if (emp != null) {
            try {
                System.out.println("Dang nhap thanh cong!");
                
                // Kiem tra role neu la admin thi mo menu admin
                Employee checkRole = employeeService.findByUsernameAndPassword(username, password);
                if (checkRole.getDepartment().equals("ROLE_ADMIN")) {
//                    System.out.println("\nBan la Quan Tri Vien !\nBan se duoc chuyen den trang Quan Tri Vien !");
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.start();
                }else{
//                    System.out.println("Chao mung " + username +  "!");
//                    System.out.println("Moi ban den trang nhan vien !");
                    MainMenu menu = new MainMenu();
                    menu.start();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AuthPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Nhap sai tai khoan va mat khau. Vui long nhap lai!");
        }
        
    }

    @Override
    public String getTitle() {
        return "Dang Nhap";
    }
    
}
