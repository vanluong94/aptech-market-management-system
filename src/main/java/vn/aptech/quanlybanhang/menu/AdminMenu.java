/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;

import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author Admin
 */
public class AdminMenu extends Menu {

    private final String TITLE = "DAY LA TRANG QUAN TRI NHAN VIEN";
    private final int[] CHOICES = {1, 2, 3, 4, 5, 0}; // for validation purpose
    private final String[] MENU_ITEMS = {
        "1. Danh sach nhan vien",
        "2. Them nhan vien",
        "3. Xem chi tiet nhan vien",
        "4. Sua 1 nhan vien",
        "5. Xoa 1 nhan vien",
        "0. Thoat",};

    private final EmployeeService employeeService;

    public AdminMenu() {
        this.employeeService = new EmployeeServiceImpl();
        this.setMenuItems(this.MENU_ITEMS);
        this.setTitle(this.TITLE);
        this.setChoices(this.CHOICES);
    }

    @Override
    public void handle(int choice) {
        Scanner sc = new Scanner(System.in);
        try {
            switch (choice) {
                case 1:
                    System.out.println("Danh sach nhan vien");
                    this.handleDisplayAllEmployee();
                    break;
                case 2:
                    System.out.println("Nhap ten nhan vien :");
                    String employeeName = AppScanner.getScanner().nextLine();
                    System.out.println("Nhap dia chi nhan vien :");
                    String employeeAddress = AppScanner.getScanner().nextLine();
                    System.out.println("Nhap so dien thoai :");
                    String employeePhone = AppScanner.getScanner().nextLine();
                    System.out.println("Nhap chuc vu (chi nhap ROLE_ADMIN, ROLE_EMPLOYEE_CASHER hoac ROLE_EMPLOYEE_INVENTORY):");
                    String employeeDepartment = AppScanner.getScanner().nextLine();
                    System.out.println("Tao Usename:");
                    String employeeUsername = AppScanner.getScanner().nextLine();
                    System.out.println("Tao Password :");
                    String employeePassword = AppScanner.getScanner().nextLine();

                    if (employeeName.length() > 0 && employeeAddress.length() > 0 && employeePhone.length() > 0 && employeeDepartment.length() > 0 && employeeUsername.length() > 5 && employeePassword.length() > 6) {
                        Employee employee = new Employee(employeeName, employeeAddress, employeePhone, employeeDepartment, employeeUsername, employeePassword);
                        if (employeeService.create(employee)) {
                            System.out.println("Them nhan vien thanh cong!");
                        } else {
                            System.out.println("Da xay ra loi!");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Nhap ID nhan vien : ");
                    int employeeId = AppScanner.getScanner().nextInt();
                    Employee employee = employeeService.findById(employeeId);
                    if (employee == null) {
                        System.out.println("Khong tim thay ID nhan vien");
                    } else {
                        System.out.println(employee.toString());
                    }
                    break;
                case 4:
                    System.out.println("Nhap ID nhan vien muon sua : ");
                    employeeId = AppScanner.getScanner().nextInt();
                    System.out.println("Nhap ten nhan vien :");
                    employeeName = AppScanner.getScanner().nextLine();
                    sc.nextLine();
                    System.out.println("Nhap dia chi nhan vien :");
                    employeeAddress = AppScanner.getScanner().nextLine();
                    System.out.println("Nhap so dien thoai :");
                    employeePhone = AppScanner.getScanner().nextLine();
                    System.out.println("Nhap chuc vu (chi nhap ROLE_EMPLOYEE_CASHER hoac ROLE_EMPLOYEE_INVENTORY):");
                    employeeDepartment = AppScanner.getScanner().nextLine();
                    System.out.println("Tao Usename:");
                    employeeUsername = AppScanner.getScanner().nextLine();
                    System.out.println("Tao Password :");
                    employeePassword = AppScanner.getScanner().nextLine();

                    if (employeeName.length() > 0 && employeeAddress.length() > 0 && employeePhone.length() > 0 && employeeDepartment.length() > 0 && employeeUsername.length() > 5 && employeePassword.length() > 6) {
                        Employee employee2 = new Employee(employeeName, employeeAddress, employeePhone, employeeDepartment, employeeUsername, employeePassword);
                        if (employeeService.updateById(employee2, employeeId)) {
                            System.out.println("Sua nhan vien thanh cong!");
                        } else {
                            System.out.println("Da xay ra loi!");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Nhap ID nhan vien muon xoa : ");
                    employeeId = AppScanner.getScanner().nextInt();
                    if (employeeService.deleteById(employeeId)) {
                        System.out.println("Xoa nhan vien thanh cong!");
                    } else {
                        System.out.println("Da xay ra loi!");
                    }
                    break;
                case 0:
                    System.exit(0);
                    break;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void handleDisplayAllEmployee() throws SQLException {

        List<Employee> employees = employeeService.findAll();
        List<Object[]> rows = new ArrayList<>();

        // transfer data to table row
        for (Employee employee : employees) {
            Object[] row = {
                employee.getEmployeeId(),
                employee.getEmployee_name(),
                employee.getEmployee_add(),
                employee.getEmployee_phone(),
                employee.getDepartment(),
                employee.getUserName(),
                employee.getPassword()
            };

            rows.add(row);
        }

        String[] headers = {"ID", "Name", "Address", "Phone", "Department", "Username", "Password"};

        TableUI theTable = new TableUI(headers, rows);
        theTable.display();

    }

}
