/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.exception.InputInvalidException;
import vn.aptech.quanlybanhang.service.DiscountService;
import vn.aptech.quanlybanhang.service.DiscountServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;


public class DiscountEditPage extends Page {

    @Override
    public void displayContent() {
        DiscountService discountService = new DiscountServiceImpl();
        boolean retry;
        do {
            retry = false;
            try {
                int id = AppScanner.scanIntWithMessage("Nhap ID Chuong trinh khuyen mai muon thay doi : ");
                Discount discount = discountService.findById(id);
                if (discount == null) {
                    System.out.println("ID khong hop le!");
                    retry = true;
                }else{
                    System.out.println("\nNhap ten moi cho Chuong trinh giam gia moi.");
                    String reName = AppScanner.scanStringWithMessage("Nhap ten Chuong trinh giam gia moi : ");
                    
                    if (reName.length() > 0) {
                        discount.setName(reName);
                    }
                    discountService.update(discount);
                    System.out.println("Doi ten thanh cong!");
                }
            } catch (InputInvalidException e) {
                System.out.println(e.getMessage());
                retry = true;
            }catch (Exception e){
                Logger.getLogger(DiscountEditPage.class.getName()).log(Level.SEVERE, null, e);
            }
        } while (retry);
        
    }

    @Override
    public String getTitle() {
        return "Sua Chuong trinh Giam gia";
    }
    
}
