/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.constant.Constant;
import vn.aptech.quanlybanhang.entities.Department;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;

public class EmployeeEditPage extends Page {

    @Override
    public void displayContent() {
        try {
            EmployeeService employeeService = new EmployeeServiceImpl();
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap ID nhan vien muon sua : ");
            int employeeId = -999;
            employeeId = sc.nextInt();
            while (employeeId == -999) {
                System.out.println("Id khong duoc de trong !");
                System.out.print("Nhap ID nhan vien : ");
                employeeId = sc.nextInt();
            }
            sc.nextLine();
            System.out.print("Sua ten nhan vien thanh : ");
            String employeeName = sc.nextLine();
            while (employeeName.length() == 0) {
                System.out.println("Ten khong duoc de trong !");
                System.out.print("Sua ten nhan vien thanh : ");
                employeeName = sc.nextLine();
            }
            System.out.print("Sua dia chi nhan vien thanh : ");
            String employeeAddress = sc.nextLine();
            while (employeeAddress.length() == 0) {
                System.out.println("Dia chi khong duoc de trong !");
                System.out.print("Sua dia chi nhan vien thanh : ");
                employeeAddress = sc.nextLine();
            }
            System.out.print("Sua so dien thoai thanh: ");
            String employeePhone = sc.nextLine();
            while (employeePhone.length() == 0) {
                System.out.println("So dien thoai khong duoc de trong !");
                System.out.print("Sua so dien thoai thanh: ");
                employeePhone = sc.nextLine();
            }
            System.out.printf("Sua chuc vu thanh (chi nhap 1=%s, 2=%s hoac 3=%s): \n", Department.ROLE_ADMIN, Department.ROLE_EMPLOYEE_CASHER, Department.ROLE_EMPLOYEE_INVENTORY);
            int employeeDepartment = sc.nextInt();
            while (employeeDepartment == 0) {
                System.out.println("Chuc vu khong duoc de trong !");
                System.out.print("Sua chuc vu thanh : ");
                employeeDepartment = sc.nextInt();
            }
            while (employeeDepartment != Department.ROLE_ADMIN.getValue() 
                    && employeeDepartment != Department.ROLE_EMPLOYEE_CASHER.getValue() 
                    && employeeDepartment != Department.ROLE_EMPLOYEE_INVENTORY.getValue()) {
                System.out.printf("Chi nhap 1=%s, 2=%s hoac 3=%s !\n", Department.ROLE_ADMIN, Department.ROLE_EMPLOYEE_CASHER, Department.ROLE_EMPLOYEE_INVENTORY);
                System.out.print("Sua chuc vu thanh : ");
                employeeDepartment = sc.nextInt();
            }
            sc.nextLine();
            System.out.print("Tao Usename moi: ");
            String employeeUsername = sc.nextLine();
            while (employeeUsername.length() == 0) {
                System.out.println("Username khong duoc de trong !");
                System.out.print("Tao Usename moi: ");
                employeeUsername = sc.nextLine();
            }
            while (employeeUsername.length() < 6) {
                System.out.println("Username tu 6 ki tu tro len !");
                System.out.print("Tao Usename moi: ");
                employeeUsername = sc.nextLine();
            }
            System.out.print("Tao Password moi: ");
            String employeePassword = sc.nextLine();
            while (employeePassword.length() == 0) {
                System.out.println("Password khong duoc de trong !");
                System.out.print("Tao Password moi: ");
                employeePassword = sc.nextLine();
            }
            while (employeePassword.length() < 6) {
                System.out.println("Password phai tu 6 ki tu tro len !");
                System.out.print("Tao Password moi: ");
                employeePassword = sc.nextLine();
            }

            if (employeeName.length() > 0 
                    && employeeAddress.length() > 0 
                    && employeePhone.length() > 0 
                    && employeeDepartment > 0 
                    && employeeUsername.length() > 5 
                    && employeePassword.length() > 5) {
                Employee employee2 = new Employee(employeeName, employeeAddress, employeePhone, Department.fromInt(employeeDepartment), employeeUsername, employeePassword);
                if (employeeService.updateById(employee2, employeeId)) {
                    System.out.println("Sua nhan vien thanh cong!");
                } else {
                    System.out.println("Da xay ra loi!");
                }
            } else {
                System.out.println("Da nhap sai!");

            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeEditPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Sua Nhan Vien";
    }

}
