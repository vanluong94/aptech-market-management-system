/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.service.DiscountService;
import vn.aptech.quanlybanhang.service.DiscountServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;


public class DiscountDeletePage extends Page {

    @Override
    public void displayContent() {
        
        try {
            DiscountService discountService = new DiscountServiceImpl();
            
            System.out.println("Nhap ID chuong trinh giam gia muon xoa :");
            int discountId = AppScanner.getScanner().nextInt();
            if (discountService.deleteById(discountId)) {
                System.out.println("Xoa thanh cong Chuong trinh giam gia ");
            } else {
                System.out.println("Da xay ra loi!");
            }
        } catch (Exception ex) {
            Logger.getLogger(DiscountDeletePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Xoa Chuong trinh giam gia";
    }
    
}
