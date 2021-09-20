/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.menu.AdminMenu;
import vn.aptech.quanlybanhang.menu.CashierMenu;
import vn.aptech.quanlybanhang.menu.InventoryMenu;
import vn.aptech.quanlybanhang.service.AuthService;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.ui.HeaderUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class AuthPage extends Page {

    private final AuthService authService;

    public AuthPage() {
        this.authService = new AuthServiceImpl();
    }

    @Override
    public void start() {
        HeaderUI.display(this.getTitle());
        this.displayContent();
    }

    @Override
    public void displayContent() {
        Employee emp = null;
        do {
            try {
                System.out.println(I18n.getMessage("page.auth.login.subTitle"));
                String username = AppScanner.scanStringWithMessage(I18n.getMessage("page.auth.login.label.username"));
                String password = AppScanner.scanStringWithMessage(I18n.getMessage("page.auth.login.label.password"));
                emp = authService.login(username, password);
                if (emp != null) {
                    System.out.println(I18n.getMessage("page.auth.login.success"));

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
                            System.out.println(I18n.getMessage("page.auth.login.invalidDept", emp.getName()));
                            System.exit(0);
                            break;
                    }

                } else {
                    System.out.println(I18n.getMessage("page.auth.login.invalid"));
                }
            } catch (Exception ex) {
                System.out.println("Lỗi ngoại lệ: " + ex.getMessage());
            }
        } while (emp == null);
    }

    @Override
    public String getTitle() {
        return I18n.getMessage("page.auth.login.title");
    }

}
