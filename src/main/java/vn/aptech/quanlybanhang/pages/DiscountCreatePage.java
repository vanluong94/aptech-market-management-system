/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.service.DiscountService;
import vn.aptech.quanlybanhang.service.DiscountServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;


public class DiscountCreatePage extends Page {

    @Override
    public void displayContent() {
        
        DiscountService discountService = new DiscountServiceImpl();
        
        System.out.println("Nhap ten chuong trinh giam gia :");
        String discountName = AppScanner.getScanner().nextLine();

        if (discountName.length() > 0) {
            try {
                Discount discount = new Discount(discountName);
                if (discountService.create(discount)) {
                    System.out.println("Them chuong trinh giam gia thanh cong!");
                } else {
                    System.out.println("Da xay ra loi!");
                }
            } catch (Exception ex) {
                Logger.getLogger(DiscountCreatePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getTitle() {
        return "Them Chuong Trinh Giam Gia";
    }
    
}
