/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;


public class EmployeeDeletePage extends Page {

    @Override
    public void displayContent() {
        try {
            EmployeeService employeeService = new EmployeeServiceImpl();
            
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap ID nhan vien muon xoa : ");
            int employeeId = sc.nextInt();
            if (employeeService.deleteById(employeeId)) {
                System.out.println("Xoa nhan vien thanh cong!");
            } else {
                System.out.println("Da xay ra loi!");
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeDeletePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Xoa Nhan Vien";
    }
    
}
