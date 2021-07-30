/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.service.AuthService;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
/**
 *
 * @author anhnbt
 */
public class AuthMenu extends Menu {

    private final AuthService authService;
    private final EmployeeService employeeService;
    public AuthMenu() {
        this.authService = new AuthServiceImpl();
        this.employeeService = new EmployeeServiceImpl();
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
            
            try {
                // Kiem tra role neu la admin thi mo menu admin
                Employee checkRole = employeeService.findByUsernameAndPassword(username, password);
                if (checkRole.getDepartment().equals("ROLE_ADMIN")) {
                    System.out.println("\nBan la Quan Tri Vien !\nBan se duoc chuyen den trang Quan Tri Vien !");
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.start();
                }
                else{
                System.out.println("Chao mung " + username +  "!");
                    System.out.println("Moi ban den trang nhan vien !");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AuthMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            System.out.println("Nhap sai tai khoan va mat khau. Vui long nhap lai!");
        }
    }

    @Override
    public void handle(int choice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String registerMenuTitle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
