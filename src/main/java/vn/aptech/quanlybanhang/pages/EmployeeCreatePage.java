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
import vn.aptech.quanlybanhang.utilities.Md5;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class EmployeeCreatePage extends Page {

    @Override
    public void displayContent() {
        
        try {
            EmployeeService employeeService = new EmployeeServiceImpl();
            
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập tên Nhân viên : ");
            String employeeName = sc.nextLine();
            while (employeeName.length() == 0) {
                System.out.println("Tên không được để trống !");
                System.out.print("Nhập tên Nhân viên : ");
                employeeName = sc.nextLine();
            }
            System.out.print("Nhập Địa chỉ Nhân viên : ");
            String employeeAddress = sc.nextLine();
            while (employeeAddress.length() == 0) {
                System.out.println("Địa chỉ không được để trống !");
                System.out.print("Nhập Địa chỉ Nhân viên : ");
                employeeAddress = sc.nextLine();
            }
            System.out.print("Nhập số điện thoại : ");
            String employeePhone = sc.nextLine();
            while (employeePhone.length() == 0) {
                System.out.println("Số điện thoại không được để trống !");
                System.out.print("Nhập số điện thoại : ");
                employeePhone = sc.nextLine();
            }
            System.out.printf("Nhập chức vụ (chỉ nhập 1=%s, 2=%s hoac 3=%s): \n", Department.ROLE_ADMIN, Department.ROLE_EMPLOYEE_CASHER, Department.ROLE_EMPLOYEE_INVENTORY);
            int employeeDepartment = sc.nextInt();
            while (employeeDepartment == 0) {
                System.out.println("Chức vụ không được để trống !");
                System.out.print("Nhập chức vụ: ");
                employeeDepartment = sc.nextInt();
            }
            while (employeeDepartment != Department.ROLE_ADMIN.getValue() 
                    && employeeDepartment != Department.ROLE_EMPLOYEE_CASHER.getValue() 
                    && employeeDepartment != Department.ROLE_EMPLOYEE_INVENTORY.getValue()) {
                System.out.printf("Chi nhập 1=%s, 2=%s hoac 3=%s !\n", Department.ROLE_ADMIN, Department.ROLE_EMPLOYEE_CASHER, Department.ROLE_EMPLOYEE_INVENTORY);
                System.out.print("Nhập chức vụ : ");
                employeeDepartment = sc.nextInt();
            }
            sc.nextLine();
            System.out.print("Tạo Username: ");
            String employeeUsername = sc.nextLine();
            while (employeeUsername.length() == 0) {
                System.out.println("Username không được để trống !");
                System.out.print("Tạo Usename: ");
                employeeUsername = sc.nextLine();
            }
            while (employeeUsername.length() < 6) {
                System.out.println("Username từ 6 ký tự trở lên !");
                System.out.print("Tạo Usename: ");
                employeeUsername = sc.nextLine();
            }
            System.out.print("Tạo Password : ");
            String employeePassword = sc.nextLine();
            while (employeePassword.length() == 0) {
                System.out.println("Password không được để trống !");
                System.out.print("Tạo Password: ");
                employeePassword = sc.nextLine();
            }
            while (employeePassword.length() < 6) {
                System.out.println("Password phải từ 6 ký tự trở lên !");
                System.out.print("Tạo Password: ");
                employeePassword = sc.nextLine();
            }
            Employee employee = new Employee(employeeName, employeeAddress, employeePhone, Department.fromInt(employeeDepartment), employeeUsername, Md5.encode(employeePassword));
            if (employeeService.create(employee)) {
                System.out.println("Thêm Nhân viên thành công!");
            } else {
                System.out.println("Đã xảy ra lỗi!");
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeCreatePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Thêm Nhân viên";
    }
    
}
