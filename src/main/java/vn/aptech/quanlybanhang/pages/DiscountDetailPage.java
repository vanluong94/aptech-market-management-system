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

/**
 *
 * @author vanluong
 */
public class DiscountDetailPage extends Page {

    @Override
    public void displayContent() {
        
        try {
            DiscountService discountService = new DiscountServiceImpl();
            
            System.out.println("Nhập ID chương trình giảm giá muốn kiem tra :");
            int discountId = AppScanner.getScanner().nextInt();
            Discount discount = discountService.findById(discountId);
            if (discount == null) {
                System.out.println("Không tìm thấy ID Chương trình giảm giá phù hợp!");
            } else {
                System.out.println(discount.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(DiscountDetailPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Xem chi tiết Chương trình giảm giá";
    }
    
}
