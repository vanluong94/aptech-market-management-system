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
public class EmployeeEditPage extends Page {

    @Override
    public void displayContent() {
        try {
            EmployeeService employeeService = new EmployeeServiceImpl();
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập ID Nhân viên muốn sửa : ");
            int employeeId = -999;
            employeeId = sc.nextInt();
            while (employeeId == -999) {
                System.out.println("Id không được để trống !");
                System.out.print("Nhập ID Nhân viên : ");
                employeeId = sc.nextInt();
            }
            sc.nextLine();
            System.out.print("Sửa tên Nhân viên thanh : ");
            String employeeName = sc.nextLine();
            while (employeeName.length() == 0) {
                System.out.println("Tên không được để trống !");
                System.out.print("Sửa tên Nhân viên thanh : ");
                employeeName = sc.nextLine();
            }
            System.out.print("Sửa Địa chỉ Nhân viên thanh : ");
            String employeeAddress = sc.nextLine();
            while (employeeAddress.length() == 0) {
                System.out.println("Địa chỉ không được để trống !");
                System.out.print("Sửa Địa chỉ Nhân viên thanh : ");
                employeeAddress = sc.nextLine();
            }
            System.out.print("Sửa số điện thoại thanh: ");
            String employeePhone = sc.nextLine();
            while (employeePhone.length() == 0) {
                System.out.println("Số điện thoại không được để trống !");
                System.out.print("Sửa số điện thoại thanh: ");
                employeePhone = sc.nextLine();
            }
            System.out.printf("Sửa chức vụ thanh (chi nhập 1=%s, 2=%s hoac 3=%s): \n", Department.ROLE_ADMIN, Department.ROLE_EMPLOYEE_CASHER, Department.ROLE_EMPLOYEE_INVENTORY);
            int employeeDepartment = sc.nextInt();
            while (employeeDepartment == 0) {
                System.out.println("Chức vụ không được để trống !");
                System.out.print("Sửa chức vụ thanh : ");
                employeeDepartment = sc.nextInt();
            }
            while (employeeDepartment != Department.ROLE_ADMIN.getValue() 
                    && employeeDepartment != Department.ROLE_EMPLOYEE_CASHER.getValue() 
                    && employeeDepartment != Department.ROLE_EMPLOYEE_INVENTORY.getValue()) {
                System.out.printf("Chi nhập 1=%s, 2=%s hoac 3=%s !\n", Department.ROLE_ADMIN, Department.ROLE_EMPLOYEE_CASHER, Department.ROLE_EMPLOYEE_INVENTORY);
                System.out.print("Sửa chức vụ thanh : ");
                employeeDepartment = sc.nextInt();
            }
            sc.nextLine();
            System.out.print("Tạo Usename mới: ");
            String employeeUsername = sc.nextLine();
            while (employeeUsername.length() == 0) {
                System.out.println("Username không được để trống !");
                System.out.print("Tạo Usename mới: ");
                employeeUsername = sc.nextLine();
            }
            while (employeeUsername.length() < 6) {
                System.out.println("Username từ 6 ký tự trở lên !");
                System.out.print("Tạo Usename mới: ");
                employeeUsername = sc.nextLine();
            }
            System.out.print("Tạo Password mới: ");
            String employeePassword = sc.nextLine();
            while (employeePassword.length() == 0) {
                System.out.println("Password không được để trống !");
                System.out.print("Tạo Password mới: ");
                employeePassword = sc.nextLine();
            }
            while (employeePassword.length() < 6) {
                System.out.println("Password phải từ 6 ký tự trở lên !");
                System.out.print("Tạo Password mới: ");
                employeePassword = sc.nextLine();
            }

            if (employeeName.length() > 0 
                    && employeeAddress.length() > 0 
                    && employeePhone.length() > 0 
                    && employeeDepartment > 0 
                    && employeeUsername.length() > 5 
                    && employeePassword.length() > 5) {
                Employee employee2 = new Employee(employeeName, employeeAddress, employeePhone, Department.fromInt(employeeDepartment), employeeUsername, Md5.encode(employeePassword));
                if (employeeService.updateById(employee2, employeeId)) {
                    System.out.println("Sửa Nhân viên thành công!");
                } else {
                    System.out.println("Đã xảy ra lỗi!");
                }
            } else {
                System.out.println("Đã nhập sai!");

            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeEditPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Sửa Nhân viên";
    }

}
