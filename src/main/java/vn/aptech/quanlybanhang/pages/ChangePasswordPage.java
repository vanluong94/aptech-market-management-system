/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ChangePasswordPage extends Page {

    private final EmployeeService employeeService;

    public ChangePasswordPage() {
        this.employeeService = new EmployeeServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            do {
                String password = AppScanner.scanStringWithMessage("Mật khẩu hiện tại*: ", false);
                Employee employee = AuthServiceImpl.getCurrentEmployee();
                if (!employee.getPassword().equals(password)) {
                    System.out.println("Mật khẩu hiện tại không đúng");
                } else {
                    String newPassword = AppScanner.scanStringWithMessage("Mật khẩu mới*: ", false);
                    String confirmPassword = AppScanner.scanStringWithMessage("Nhập lại mật khẩu mới: ", false);

                    if (!newPassword.equals(confirmPassword)) {
                        System.out.println("Mật khẩu xác nhận không đúng");
                    } else {
                        employee.setPassword(newPassword);
                        if (employeeService.update(employee)) {
                            System.out.println("Đổi mật khẩu thành công");
                            break;
                        } else {
                            System.out.println("Đã xảy ra lỗi");
                        }
                    }
                }
            } while (true);
        } catch (Exception ex) {
            Logger.getLogger(ChangePasswordPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Doi mat khau";
    }

}
