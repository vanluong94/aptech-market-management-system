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

        String username = AppScanner.scanStringWithMessage("Nhập tài khoản: ");
        String password = AppScanner.scanStringWithMessage("Nhập mật khẩu: ");

        // kiem tra không được để trống
        Employee employee = new Employee(username, Md5.encode(password));

        Employee emp = authService.login(employee);
        
        if (emp != null) {
            check =1;
            System.out.println("\nĐăng nhập thành công!");

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
                    System.out.println("Tài khoản không có quyền truy cập hợp lệ!");
                    System.exit(0);
                    break;
            }

        } else {
            System.out.println("Nhập sai tài khoản hoặc mật khẩu, vui lòng nhập lại!");
        }

        } while (check == 0);
    }

    @Override
    public String getTitle() {
        return "Đăng Nhập";
    }

}
