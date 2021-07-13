/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.Scanner;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.service.AuthService;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;

/**
 *
 * @author anhnbt
 */
public class AuthMenu implements BaseMenu {

    private AuthService authService;

    public AuthMenu() {
        this.authService = new AuthServiceImpl();
    }
    
    @Override
    public void displayMenu() {
        System.out.println("=============");
        System.out.println("Dang nhap he thong");
        System.out.println("=============");
    }

    @Override
    public void start(Scanner scanner) {
        System.out.println("Nhap tai khoan: ");
        String username = scanner.nextLine();
        System.out.println("Nhap mat khau: ");
        String password = scanner.nextLine();
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
    
}
