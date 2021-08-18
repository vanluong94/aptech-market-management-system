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
        
        System.out.println("Nhập tên chương trình giảm giá :");
        String discountName = AppScanner.getScanner().nextLine();

        if (discountName.length() > 0) {
            try {
                Discount discount = new Discount(discountName);
                if (discountService.create(discount)) {
                    System.out.println("Thêm chương trình giảm giá thành công!");
                } else {
                    System.out.println("Đã xảy ra lỗi!");
                }
            } catch (Exception ex) {
                Logger.getLogger(DiscountCreatePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getTitle() {
        return "Thêm Chương trình giảm giá";
    }
    
}
