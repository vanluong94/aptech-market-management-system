/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;


public class EmployeeCreatePage extends Page {

    @Override
    public void displayContent() {
        
        try {
            EmployeeService employeeService = new EmployeeServiceImpl();
            
            System.out.println("-----------------------------------");
            System.out.println("---------Tao moi nhan vien----------");
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
            System.out.print("Nhap chuc vu (chi nhap ROLE_ADMIN, ROLE_EMPLOYEE_CASHER hoac ROLE_EMPLOYEE_INVENTORY): ");
            String employeeDepartment = sc.nextLine();
            while (employeeDepartment.length() == 0) {
                System.out.println("Chuc vu khong duoc de trong !");
                System.out.print("Nhap chuc vu : ");
                employeeDepartment = sc.nextLine();
            }
            while (!employeeDepartment.equals("ROLE_ADMIN") && !employeeDepartment.equals("ROLE_EMPLOYEE_CASHER") && !employeeDepartment.equals("ROLE_EMPLOYEE_INVENTORY")) {
                System.out.println("chi nhap ROLE_ADMIN, ROLE_EMPLOYEE_CASHER hoac ROLE_EMPLOYEE_INVENTORY !");
                System.out.print("Nhap chuc vu : ");
                employeeDepartment = sc.nextLine();
            }
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
            Employee employee = new Employee(employeeName, employeeAddress, employeePhone, employeeDepartment, employeeUsername, employeePassword);
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
