/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.service.DiscountService;
import vn.aptech.quanlybanhang.service.DiscountServiceImpl;

/**
 *
 * @author vanluong
 */
public class DiscountListingPage extends Page {

    @Override
    public void displayContent() {
        try {
            DiscountService discountService = new DiscountServiceImpl();
            
            List<Discount> discounts = discountService.findAll();
            if (discounts.isEmpty()) {
                System.out.println("Danh sach trong");
            } else {
                for (Discount discount : discounts) {
                    System.out.println(discount.toString());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscountListingPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Danh sach Chuong trinh giam gia";
    }
    
}
