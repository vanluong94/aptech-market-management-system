/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import vn.aptech.quanlybanhang.common.MessageCommon;
import vn.aptech.quanlybanhang.common.MessageContent;
import vn.aptech.quanlybanhang.dao.EmployeeDAOImpl;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;

public class EmployeeSearchPage extends Page {

    @Override
    public void displayContent() {
        int check = 0;
        EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
        do {
            try {
                System.out.println("Nhap vao ten nhan vien can tim kiem: ");
                Scanner sc = new Scanner(System.in);
                String tk = sc.nextLine();
                List<Employee> employees = employeeDAOImpl.findByNameEmployee(tk);
                if (employees.isEmpty()) {
                    System.out.println(MessageCommon.getMessage(MessageContent.OBJECT_NOT_FOUND, tk));
                } else {
                    System.out.println("Ket qua tim kiem voi tu '" + tk + "':");
                    List<Object[]> rows = new ArrayList<Object[]>();
                    for (Employee ep : employees) {
                        Object[] row = {
                            ep.getEmployeeId(),
                            ep.getName(),
                            ep.getAddress(),
                            ep.getPhone(),
                            ep.getDepartment(),
                            ep.getUserName()
                        };
                        rows.add(row);
                    }
                    String[] headers = {"ID", "Name", "Address", "Phone", "Department", "UserName"};
                    TableUI tableUI = new TableUI(headers, rows);
                    tableUI.display();
                }
                String choice = AppScanner.scanStringWithMessage("Bạn có muốn tìm sản phẩm khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            } catch (SQLException e) {
                System.out.println("Exception when Employee.handleSearch: " + e.getMessage());
            }
        } while (check == 0);
    }

    @Override
    public String getTitle() {
        return "Tim kiem Nhan vien";
    }

}
