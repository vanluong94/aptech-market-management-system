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

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class PageUpdateProfile extends Page {
    
    private final EmployeeService empService;

    public PageUpdateProfile() {
        this.empService = new EmployeeServiceImpl();
    }
    
    @Override
    public void displayContent() {
        try {
            Employee emp = AuthServiceImpl.getCurrentEmployee();
            System.out.println("ID: " + emp.getEmployeeId());
            System.out.println(I18n.getMessage("employee.username") + ": " + emp.getUserName());
            System.out.println(I18n.getMessage("employee.dept") + ": " + emp.getDepartment());
            
            String name = AppScanner.scanStringWithMessage(I18n.getMessage("employee.name"));
            String address = AppScanner.scanStringWithMessage(I18n.getMessage("employee.addr"));
            String phone = AppScanner.scanStringWithMessage(I18n.getMessage("employee.phone"));
            emp.setName(name);
            emp.setAddress(address);
            emp.setPhone(phone);
            if (empService.update(emp)) {
                I18n.printEntityMessage("employee", "entity.msg.updated");
            } else {
                I18n.printEntityMessage("employee", "entity.error.updateFailed");
            }
        } catch (Exception ex) {
            Logger.getLogger(PageUpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getMessage("title.updateProfile");
    }
    
}
