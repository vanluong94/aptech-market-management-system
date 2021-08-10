/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.menu.AdminMenu;
import vn.aptech.quanlybanhang.menu.CashierMenu;
import vn.aptech.quanlybanhang.menu.InventoryMenu;
import vn.aptech.quanlybanhang.service.AuthService;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.ui.HeaderUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.Md5;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class AuthPage extends Page {

    @Override
    public void start() {
        HeaderUI.display(this.getTitle());
        this.displayContent();

    }

    @Override
    public void displayContent() {
        int check = 0;
        do {            
            AuthService authService = new AuthServiceImpl();

        String username = AppScanner.scanStringWithMessage("Nhap tai khoan: ");
        String password = AppScanner.scanStringWithMessage("Nhap mat khau: ");

        // kiem tra khong duoc de trong
        Employee employee = new Employee(username, Md5.encode(password));

        Employee emp = authService.login(employee);
        
        if (emp != null) {
            check =1;
            System.out.println("Dang nhap thanh cong!");

            // Mo menu theo role tuong ung
            switch (emp.getDepartment().name()) {
                case "ROLE_ADMIN":
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.start();
                    break;
                case "ROLE_EMPLOYEE_CASHER":
                    CashierMenu cashierMenu = new CashierMenu();
                    cashierMenu.start();
                    break;
                case "ROLE_EMPLOYEE_INVENTORY":
                    InventoryMenu inventoryMenu = new InventoryMenu();
                    inventoryMenu.start();
                    break;
                default:
                    System.out.println("Tài khoản không có quy�?n truy cập hợp lệ.");
                    System.exit(0);
                    break;
            }

        } else {
            System.out.println("Nhap sai tai khoan va mat khau. Vui long nhap lai!");
        }

        } while (check == 0);
    }

    @Override
    public String getTitle() {
        return "Dang Nhap";
    }

}
