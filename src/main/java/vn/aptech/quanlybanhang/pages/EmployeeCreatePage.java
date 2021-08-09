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
            System.out.print("Nhap ten nhan vien : ");
            String employeeName = sc.nextLine();
            while (employeeName.length() == 0) {
                System.out.println("Ten khong duoc de trong !");
                System.out.print("Nhap ten nhan vien : ");
                employeeName = sc.nextLine();
            }
            System.out.print("Nhap dia chi nhan vien : ");
            String employeeAddress = sc.nextLine();
            while (employeeAddress.length() == 0) {
                System.out.println("Dia chi khong duoc de trong !");
                System.out.print("Nhap dia chi nhan vien : ");
                employeeAddress = sc.nextLine();
            }
            System.out.print("Nhap so dien thoai : ");
            String employeePhone = sc.nextLine();
            while (employeePhone.length() == 0) {
                System.out.println("So dien thoai khong duoc de trong !");
                System.out.print("Nhap so dien thoai : ");
                employeePhone = sc.nextLine();
            }
            System.out.printf("Nhap chuc vu (chi nhap 1=%s, 2=%s hoac 3=%s): \n", Department.ROLE_ADMIN, Department.ROLE_EMPLOYEE_CASHER, Department.ROLE_EMPLOYEE_INVENTORY);
            int employeeDepartment = sc.nextInt();
            while (employeeDepartment == 0) {
                System.out.println("Chuc vu khong duoc de trong !");
                System.out.print("Nhap chuc vu: ");
                employeeDepartment = sc.nextInt();
            }
            while (employeeDepartment != Department.ROLE_ADMIN.getValue() 
                    && employeeDepartment != Department.ROLE_EMPLOYEE_CASHER.getValue() 
                    && employeeDepartment != Department.ROLE_EMPLOYEE_INVENTORY.getValue()) {
                System.out.printf("Chi nhap 1=%s, 2=%s hoac 3=%s !\n", Department.ROLE_ADMIN, Department.ROLE_EMPLOYEE_CASHER, Department.ROLE_EMPLOYEE_INVENTORY);
                System.out.print("Nhap chuc vu : ");
                employeeDepartment = sc.nextInt();
            }
            sc.nextLine();
            System.out.print("Tao Usename: ");
            String employeeUsername = sc.nextLine();
            while (employeeUsername.length() == 0) {
                System.out.println("Username khong duoc de trong !");
                System.out.print("Tao Usename: ");
                employeeUsername = sc.nextLine();
            }
            while (employeeUsername.length() < 6) {
                System.out.println("Username tu 6 ki tu tro len !");
                System.out.print("Tao Usename: ");
                employeeUsername = sc.nextLine();
            }
            System.out.print("Tao Password : ");
            String employeePassword = sc.nextLine();
            while (employeePassword.length() == 0) {
                System.out.println("Password khong duoc de trong !");
                System.out.print("Tao Password: ");
                employeePassword = sc.nextLine();
            }
            while (employeePassword.length() < 6) {
                System.out.println("Password phai tu 6 ki tu tro len !");
                System.out.print("Tao Password: ");
                employeePassword = sc.nextLine();
            }
            Employee employee = new Employee(employeeName, employeeAddress, employeePhone, Department.fromInt(employeeDepartment), employeeUsername, Md5.encode(employeePassword));
            if (employeeService.create(employee)) {
                System.out.println("Them nhan vien thanh cong!");
            } else {
                System.out.println("Da xay ra loi!");
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeCreatePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Them nhan vien";
    }
    
}
