/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Department;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;
import vn.aptech.quanlybanhang.utilities.Md5;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class EmployeeEditPage extends Page {

    @Override
    public void displayContent() {
        try {
            EmployeeService employeeService = new EmployeeServiceImpl();
            Scanner sc = new Scanner(System.in);

            int employeeId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("employee", "entity.scan.id.edit"));
            String employeeName = AppScanner.scanStringWithi18Message("employee.scan.name");
            String employeeAddress = AppScanner.scanStringWithi18Message("employee.scan.addr");
            String employeePhone = AppScanner.scanStringWithi18Message("employee.scan.phone");

            int employeeDepartment = 0;
            do {
                employeeDepartment = AppScanner.scanIntWithMessage(
                        I18n.getMessage("employee.scan.dept")
                        + String.format("(1=%s; 2=%s; 3=%s", Department.ROLE_ADMIN, Department.ROLE_EMPLOYEE_CASHER, Department.ROLE_EMPLOYEE_INVENTORY)
                );

                if (employeeDepartment < 1 || employeeDepartment > 3) {
                    I18n.print("employee.error.invalidDept");
                }
            } while (employeeDepartment < 1 || employeeDepartment > 3);

            String employeeUsername;
            do {
                employeeUsername = AppScanner.scanStringWithi18Message("employee.scan.username");

                if (employeeUsername.length() < 6) {
                    I18n.print("employee.error.invalidUsernameLength");
                }
            } while (employeeUsername.length() < 6);

            String employeePassword;
            do {
                employeePassword = AppScanner.scanStringWithi18Message("employee.scan.password");

                if (employeeUsername.length() < 6) {
                    I18n.print("employee.error.invalidPassLength");
                }
            } while (employeeUsername.length() < 6);

            Employee employee = new Employee(employeeName, employeeAddress, employeePhone, Department.fromInt(employeeDepartment), employeeUsername, Md5.encode(employeePassword));
            
            if (employeeService.updateById(employee, employeeId)) {
                I18n.printEntityMessage("employee", "entity.msg.updated");
            } else {
                I18n.printEntityMessage("employee", "entity.error.updateFailed");
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeEditPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("employee", "entity.title.edit");
    }

}
