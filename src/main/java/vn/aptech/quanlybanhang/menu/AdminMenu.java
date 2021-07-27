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
                    System.out.println("[1].Them nhan vien \n[2].Xem chi tiet nhan vien\n[3].Sua 1 nhan vien\n[4].Xoa 1 nhan vien\n[5].Ve menu chinh\n[6].Exit");
                    int choice2 = sc.nextInt();
                    switch (choice2) {
                        case 1:
                            this.handleCreateEmployee();
                            System.out.println("1.Ve menu chinh\n2.Exit ");
                            int choice3 = sc.nextInt();
                            if (choice3 == 1) {
                                break;
                            }
                            if (choice3 == 2) {
                                System.exit(0);
                            }

                        case 2:
                            this.handleViewEmployeeOne();
                            System.out.println("1.Ve menu chinh\n2.Exit ");
                            choice3 = sc.nextInt();
                            if (choice3 == 1) {
                                break;
                            }
                            if (choice3 == 2) {
                                System.exit(0);
                            }

                        case 3:
                            this.handleUpdateEmployee();
                            System.out.println("1.Ve menu chinh\n2.Exit ");
                            choice3 = sc.nextInt();
                            if (choice3 == 1) {
                                break;
                            }
                            if (choice3 == 2) {
                                System.exit(0);
                            }
                        case 4:
                            this.handleDeleteEmployeeOne();
                            System.out.println("1.Ve menu chinh\n2.Exit ");
                            choice3 = sc.nextInt();
                            if (choice3 == 1) {
                                break;
                            }
                            if (choice3 == 2) {
                                System.exit(0);
                            }
                        case 5:
                            break;
                        case 6:
                            System.out.println("Tam Biet!");
                            System.exit(0);
                    }
                    break;
                case 2:
                    this.handleCreateEmployee();
                    System.out.println("1.Ve menu chinh\n2.Exit ");
                    int choice3 = sc.nextInt();
                    if (choice3 == 1) {
                        break;
                    }
                    if (choice3 == 2) {
                        System.out.println("Tam Biet!");
                        System.exit(0);
                    }
                case 3:
                    this.handleViewEmployeeOne();
                    choice3 = sc.nextInt();
                    if (choice3 == 1) {
                        break;
                    }
                    if (choice3 == 2) {
                        System.out.println("Tam Biet!");
                        System.exit(0);
                    }
                case 4:
                    this.handleUpdateEmployee();
                    choice3 = sc.nextInt();
                    if (choice3 == 1) {
                        break;
                    }
                    if (choice3 == 2) {
                        System.out.println("Tam Biet!");
                        System.exit(0);
                    }
                case 5:
                    this.handleDeleteEmployeeOne();
                    choice3 = sc.nextInt();
                    if (choice3 == 1) {
                        break;
                    }
                    if (choice3 == 2) {
                        System.out.println("Tam Biet!");
                        System.exit(0);
                    }
                case 0:
                    System.out.println("Tam Biet!");
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

    public void handleCreateEmployee() throws Exception {
        System.out.println("-----------------------------------");
        System.out.println("---------Tao moi nhan vien----------");
        System.out.print("Nhap ten nhan vien : ");
        String employeeName = AppScanner.getScanner().nextLine();
        System.out.print("Nhap dia chi nhan vien : ");
        String employeeAddress = AppScanner.getScanner().nextLine();
        System.out.print("Nhap so dien thoai : ");
        String employeePhone = AppScanner.getScanner().nextLine();
        System.out.print("Nhap chuc vu (chi nhap ROLE_ADMIN, ROLE_EMPLOYEE_CASHER hoac ROLE_EMPLOYEE_INVENTORY): ");
        String employeeDepartment = AppScanner.getScanner().nextLine();
        System.out.print("Tao Usename: ");
        String employeeUsername = AppScanner.getScanner().nextLine();
        System.out.print("Tao Password : ");
        String employeePassword = AppScanner.getScanner().nextLine();

        if (employeeName.length() > 0 && employeeAddress.length() > 0 && employeePhone.length() > 0 && employeeDepartment.length() > 0 && employeeUsername.length() > 5 && employeePassword.length() > 6) {
            Employee employee = new Employee(employeeName, employeeAddress, employeePhone, employeeDepartment, employeeUsername, employeePassword);
            if (employeeService.create(employee)) {
                System.out.println("Them nhan vien thanh cong!");
            } else {
                System.out.println("Da xay ra loi!");
            }
        } else {
            System.out.println("Nhap sai!");
        }
    }

    public void handleViewEmployeeOne() throws Exception {
        System.out.println("---------------------------------");
        System.out.println("-------Chi tiet nhan vien--------");
        System.out.print("Nhap ID nhan vien : ");
        int employeeId = AppScanner.getScanner().nextInt();
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            System.out.println("Khong tim thay ID nhan vien");
        } else {
            System.out.println(employee.toString());
        }
    }

    public void handleDeleteEmployeeOne() throws Exception {
        System.out.println("--------------------------------");
        System.out.println("---------Xoa nhan vien----------");
        System.out.print("Nhap ID nhan vien muon xoa : ");
        int employeeId = AppScanner.getScanner().nextInt();
        if (employeeService.deleteById(employeeId)) {
            System.out.println("Xoa nhan vien thanh cong!");
        } else {
            System.out.println("Da xay ra loi!");
        }
    }

    public void handleUpdateEmployee() throws SQLException {
        System.out.println("--------------------------------");
        System.out.println("---------Sua nhan vien----------");
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ID nhan vien muon sua : ");
        int employeeId = AppScanner.getScanner().nextInt();
        System.out.print("Sua ten nhan vien thanh : ");
        String employeeName = AppScanner.getScanner().nextLine();
        sc.nextLine();
        System.out.print("Sua dia chi nhan vien thanh : ");
        String employeeAddress = AppScanner.getScanner().nextLine();
        System.out.print("Sua so dien thoai thanh : ");
        String employeePhone = AppScanner.getScanner().nextLine();
        System.out.print("Sua chuc vu thanh(chi nhap ROLE_EMPLOYEE_CASHER hoac ROLE_EMPLOYEE_INVENTORY): ");
        String employeeDepartment = AppScanner.getScanner().nextLine();
        System.out.print("Sua Usename:");
        String employeeUsername = AppScanner.getScanner().nextLine();
        System.out.print("Sua Password : ");
        String employeePassword = AppScanner.getScanner().nextLine();

        if (employeeName.length() > 0 && employeeAddress.length() > 0 && employeePhone.length() > 0 && employeeDepartment.length() > 0 && employeeUsername.length() > 5 && employeePassword.length() > 6) {
            Employee employee2 = new Employee(employeeName, employeeAddress, employeePhone, employeeDepartment, employeeUsername, employeePassword);
            if (employeeService.updateById(employee2, employeeId)) {
                System.out.println("Sua nhan vien thanh cong!");
            } else {
                System.out.println("Da xay ra loi!");
            }
        } else {
            System.out.println("Da nhap sai!");

        }
    }
}
