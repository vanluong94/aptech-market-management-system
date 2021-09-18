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
import vn.aptech.quanlybanhang.utilities.I18n;
import vn.aptech.quanlybanhang.utilities.Md5;

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
                String password = AppScanner.scanStringWithMessage(I18n.getMessage("page.changePass.scan.currentPass"));
                Employee employee = AuthServiceImpl.getCurrentEmployee();
                if (!employee.getPassword().equals(Md5.encode(password))) {
                    I18n.print("page.changePass.error.wrongPass");
                } else {
                    String newPassword = AppScanner.scanStringWithMessage(I18n.getMessage("page.changePass.scan.newPass"));
                    String confirmPassword = AppScanner.scanStringWithMessage(I18n.getMessage("page.changePass.scan.repeatPass"));

                    if (!newPassword.equals(confirmPassword)) {
                        I18n.print("page.changePass.error.mismatchPass");
                    } else {
                        employee.setPassword(Md5.encode(newPassword));
                        if (employeeService.update(employee)) {
                            I18n.print("page.changePass.msg.success");
                            break;
                        } else {
                            I18n.print("page.changePass.msg.failed");
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
        return I18n.getMessage("page.changePass.title");
    }

}
