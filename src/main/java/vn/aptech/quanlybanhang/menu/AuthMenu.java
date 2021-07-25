/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.service.AuthService;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author anhnbt
 */
public class AuthMenu extends Menu {

    private AuthService authService;

    public AuthMenu() {
        this.authService = new AuthServiceImpl();
    }

    @Override
    public void start() {
        System.out.println("Nhap tai khoan: ");
        String username = AppScanner.getScanner().nextLine();
        System.out.println("Nhap mat khau: ");
        String password = AppScanner.getScanner().nextLine();
        // kiem tra khong duoc de trong
        Employee employee = new Employee(username, password);
        Employee emp = authService.login(employee);
        if (emp != null) {
            System.out.println("Dang nhap thanh cong!");
            System.out.println("Chao mung " + username + "!");
            // Kiem tra role neu la admin thi mo menu admin
            // Nguoc lai thi mo menu employee
        } else {
            System.out.println("Nhap sai tai khoan va mat khau. Vui long nhap lai!");
        }
    }

    @Override
    public void handle(int choice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
