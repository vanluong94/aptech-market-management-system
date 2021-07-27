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
        System.out.print("Chon 1 tuy chon: ");
        Scanner sc = new Scanner(System.in);
        try {
            switch (choice) {
                case 1:
                    System.out.println("Danh sach nhan vien");
                    this.handleDisplayAllEmployee();
                    System.out.println("[1].Them nhan vien \n[2].Xem chi tiet nhan vien\n[3].Sua 1 nhan vien\n[4].Xoa 1 nhan vien\n[5].Ve menu chinh\n[6].Exit");
                    System.out.print("Chon 1 tuy chon: ");
                    int choice2 = sc.nextInt();
                    switch (choice2) {
                        case 1:
                            this.handleCreateEmployee();
                            System.out.println("1.Ve menu chinh\n2.Exit ");
                            System.out.print("Chon 1 tuy chon: ");
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
                            System.out.print("Chon 1 tuy chon: ");
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
                            System.out.print("Chon 1 tuy chon: ");
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
                            System.out.print("Chon 1 tuy chon: ");
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
                    System.out.print("Chon 1 tuy chon: ");
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
                    System.out.println("1.Ve menu chinh\n2.Exit ");
                    System.out.print("Chon 1 tuy chon: ");
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
                    System.out.println("1.Ve menu chinh\n2.Exit ");
                    System.out.print("Chon 1 tuy chon: ");
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
                    System.out.println("1.Ve menu chinh\n2.Exit ");
                    System.out.print("Chon 1 tuy chon: ");
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
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten nhan vien : ");
        String employeeName = sc.nextLine();
        if (employeeName.length() == 0) {
            System.out.println("Ten khong duoc de trong !");
            System.out.print("Nhap ten nhan vien : ");
            employeeName = sc.nextLine();
        }
        System.out.print("Nhap dia chi nhan vien : ");
        String employeeAddress = sc.nextLine();
        if (employeeAddress.length() == 0) {
            System.out.println("Dia chi khong duoc de trong !");
            System.out.print("Nhap dia chi nhan vien : ");
            employeeAddress = sc.nextLine();
        }
        System.out.print("Nhap so dien thoai : ");
        String employeePhone = sc.nextLine();
        if (employeePhone.length() == 0) {
            System.out.println("So dien thoai khong duoc de trong !");
            System.out.print("Nhap so dien thoai : ");
            employeePhone = sc.nextLine();
        }
        System.out.print("Nhap chuc vu (chi nhap ROLE_ADMIN, ROLE_EMPLOYEE_CASHER hoac ROLE_EMPLOYEE_INVENTORY): ");
        String employeeDepartment = sc.nextLine();
        if (employeeDepartment.length() == 0) {
            System.out.println("Chuc vu khong duoc de trong !");
            System.out.print("Nhap chuc vu : ");
            employeeDepartment = sc.nextLine();
        }
        if (!employeeDepartment.equals("ROLE_ADMIN")||!employeeDepartment.equals("ROLE_EMPLOYEE_CASHER") || !employeeDepartment.equals("ROLE_EMPLOYEE_INVENTORY") ) {
            System.out.println("chi nhap ROLE_ADMIN, ROLE_EMPLOYEE_CASHER hoac ROLE_EMPLOYEE_INVENTORY !");
            System.out.print("Nhap chuc vu : ");
            employeeDepartment = sc.nextLine();
        }
        System.out.print("Tao Usename: ");
        String employeeUsername = sc.nextLine();
        if (employeeUsername.length() == 0) {
            System.out.println("Username khong duoc de trong !");
            System.out.print("Tao Usename: ");
            employeeUsername = sc.nextLine();
        }
        if (employeeUsername.length() < 6) {
            System.out.println("Username tu 6 ki tu tro len !");
            System.out.print("Tao Usename: ");
            employeeUsername = sc.nextLine();
        }
        System.out.print("Tao Password : ");
        String employeePassword = sc.nextLine();
        if (employeePassword.length() == 0) {
            System.out.println("Password khong duoc de trong !");
            System.out.print("Tao Password: ");
            employeePassword = sc.nextLine();
        }
        if (employeePassword.length() < 6) {
            System.out.println("Password phai tu 6 ki tu tro len !");
            System.out.print("Tao Password: ");
            employeePassword = sc.nextLine();
        }

      //  if (employeeName.length() > 0 && employeeAddress.length() > 0 && employeePhone.length() > 0 && employeeDepartment.length() > 0 && employeeUsername.length() > 5 && employeePassword.length() > 6) {
            Employee employee = new Employee(employeeName, employeeAddress, employeePhone, employeeDepartment, employeeUsername, employeePassword);
            if (employeeService.create(employee)) {
                System.out.println("Them nhan vien thanh cong!");
            } else {
                System.out.println("Da xay ra loi!");
            }
    //    }
//        else {
//            System.out.println("Nhap sai!");
//        }
    }

    public void handleViewEmployeeOne() throws Exception {
        System.out.println("---------------------------------");
        System.out.println("-------Chi tiet nhan vien--------");
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ID nhan vien : ");
        int employeeId = sc.nextInt();
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
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ID nhan vien muon xoa : ");
        int employeeId = sc.nextInt();
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
        int employeeId = -999;
         employeeId = sc.nextInt();
        if (employeeId == -999) {
            System.out.println("Id khong duoc de trong !");
            System.out.print("Nhap ID nhan vien : ");
            employeeId = sc.nextInt();
        }
        sc.nextLine();
        System.out.print("Sua ten nhan vien thanh : ");
        String employeeName = sc.nextLine();
        if (employeeName.length() == 0) {
            System.out.println("Ten khong duoc de trong !");
            System.out.print("Sua ten nhan vien thanh : ");
            employeeName = sc.nextLine();
        }
        System.out.print("Sua dia chi nhan vien thanh : ");
        String employeeAddress = sc.nextLine();
        if (employeeAddress.length() == 0) {
            System.out.println("Dia chi khong duoc de trong !");
            System.out.print("Sua dia chi nhan vien thanh : ");
            employeeAddress = sc.nextLine();
        }
        System.out.print("Sua so dien thoai thanh: ");
        String employeePhone = sc.nextLine();
        if (employeePhone.length() == 0) {
            System.out.println("So dien thoai khong duoc de trong !");
            System.out.print("Sua so dien thoai thanh: ");
            employeePhone = sc.nextLine();
        }
        System.out.print("Sua chuc vu thanh (chi nhap ROLE_ADMIN, ROLE_EMPLOYEE_CASHER hoac ROLE_EMPLOYEE_INVENTORY): ");
        String employeeDepartment = sc.nextLine();
        if (employeeDepartment.length() == 0) {
            System.out.println("Chuc vu khong duoc de trong !");
            System.out.print("Sua chuc vu thanh : ");
            employeeDepartment = sc.nextLine();
        }
        if (!employeeDepartment.equals("ROLE_ADMIN")||!employeeDepartment.equals("ROLE_EMPLOYEE_CASHER") || !employeeDepartment.equals("ROLE_EMPLOYEE_INVENTORY") ) {
            System.out.println("chi nhap ROLE_ADMIN, ROLE_EMPLOYEE_CASHER hoac ROLE_EMPLOYEE_INVENTORY !");
            System.out.print("Nhap chuc vu : ");
            employeeDepartment = sc.nextLine();
        }
        System.out.print("Tao Usename: ");
        String employeeUsername = sc.nextLine();
        if (employeeUsername.length() == 0) {
            System.out.println("Username khong duoc de trong !");
            System.out.print("Tao Usename: ");
            employeeUsername = sc.nextLine();
        }
        if (employeeUsername.length() < 6) {
            System.out.println("Username tu 6 ki tu tro len !");
            System.out.print("Tao Usename: ");
            employeeUsername = sc.nextLine();
        }
        System.out.print("Tao Password : ");
        String employeePassword = sc.nextLine();
        if (employeePassword.length() == 0) {
            System.out.println("Password khong duoc de trong !");
            System.out.print("Tao Password: ");
            employeePassword = sc.nextLine();
        }
        if (employeePassword.length() < 6) {
            System.out.println("Password phai tu 6 ki tu tro len !");
            System.out.print("Tao Password: ");
            employeePassword = sc.nextLine();
        }

      if (employeeName.length() > 0 && employeeAddress.length() > 0 && employeePhone.length() > 0 && employeeDepartment.length() > 0 && employeeUsername.length() > 5 && employeePassword.length() > 6) {
            Employee employee2 = new Employee(employeeName, employeeAddress, employeePhone, employeeDepartment, employeeUsername, employeePassword);
            if (employeeService.updateById(employee2, employeeId)) {
                System.out.println("Sua nhan vien thanh cong!");
            } else {
                System.out.println("Da xay ra loi!");
            }
        } 
        
        else {
            System.out.println("Da nhap sai!");

        }
    }
    
    public void checkName(int name){
        if (name<0) {
            System.out.println("Ten khong duoc de trong!");
        }
    }
}
