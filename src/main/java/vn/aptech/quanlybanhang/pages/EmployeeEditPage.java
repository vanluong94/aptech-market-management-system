/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import vn.aptech.quanlybanhang.common.StringCommon;
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

    private final EmployeeService employeeService;

    public EmployeeEditPage() {
        this.employeeService = new EmployeeServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            int employeeId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("employee", "entity.scan.id.edit"));
            Employee employee = employeeService.findById(employeeId);
            if (employee == null) {
                System.out.println(I18n.getMessage("entity.error.idNotFound"));
                return;
            }
            String name = AppScanner.scanStringWithi18Message("employee.scan.name");
            String address = AppScanner.scanStringWithi18Message("employee.scan.addr");
            String phone = AppScanner.scanStringWithi18Message("employee.scan.phone");

            int department = 0;
            do {
                department = AppScanner.scanIntWithMessage(
                        I18n.getMessage("employee.scan.dept")
                        + String.format("(1=%s; 2=%s; 3=%s)", 
                                StringCommon.getDepartmentString(Department.ROLE_ADMIN), 
                                StringCommon.getDepartmentString(Department.ROLE_EMPLOYEE_CASHER), 
                                StringCommon.getDepartmentString(Department.ROLE_EMPLOYEE_INVENTORY))
                );

                if (department < 1 || department > 3) {
                    I18n.print("employee.error.invalidDept");
                }
            } while (department < 1 || department > 3);

            String password;
            do {
                password = AppScanner.scanStringWithi18Message("employee.scan.password");

                if (password.length() < 6) {
                    I18n.print("employee.error.invalidPassLength");
                }
            } while (password.length() < 6);

            employee.setName(name);
            employee.setAddress(address);
            employee.setPhone(phone);
            employee.setDepartment(Department.fromInt(department));
            employee.setPassword(Md5.encode(password));

            if (employeeService.update(employee)) {
                I18n.printEntityMessage("employee", "entity.msg.updated");
            } else {
                I18n.printEntityMessage("employee", "entity.error.updateFailed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("employee", "entity.title.edit");
    }

}
