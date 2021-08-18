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
            System.out.println("Tài khoản: " + emp.getUserName());
            System.out.println("Chức vụ: " + emp.getDepartment());
            String name = AppScanner.scanStringWithMessage("Họ tên: ", false);
            AppScanner.getScanner().nextLine();
            String address = AppScanner.scanStringWithMessage("Địa chỉ: ", false);
            String phone = AppScanner.scanStringWithMessage("Số điện thoại: ", false);
            emp.setName(name);
            emp.setAddress(address);
            emp.setPhone(phone);
            if (empService.update(emp)) {
                System.out.println("Cập nhật thành công");
            } else {
                System.out.println("Đã xảy ra lỗi");
            }
        } catch (Exception ex) {
            Logger.getLogger(PageUpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Cập nhật thông tin";
    }
    
}
