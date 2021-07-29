/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.service.AuthService;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
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
        
        String username = AppScanner.scanStringWithMessage("Nhap tai khoan: ");
        String password = AppScanner.scanStringWithMessage("Nhap mat khau: ");
        
        // kiem tra khong duoc de trong
        Employee employee = new Employee(username, password);
        
        Employee emp = authService.login(employee);
        if (emp != null) {
            System.out.println("Dang nhap thanh cong!");
            System.out.println("Chao mung " + username + "!");
            // Kiem tra role neu la admin thi mo menu admin
        } else {
            System.out.println("Nhap sai tai khoan va mat khau. Vui long nhap lai!");
        }
        
    }

    @Override
    public String getTitle() {
        return "Dang Nhap";
    }
    
}
