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
import vn.aptech.quanlybanhang.utilities.I18n;


public class DiscountDeletePage extends Page {

    @Override
    public void displayContent() {
        
        try {
            DiscountService discountService = new DiscountServiceImpl();
            
            int discountId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("discount", "entity.scan.id.delete"));
            if (discountService.deleteById(discountId)) {
                I18n.printEntityMessage("discount", "entity.msg.deleted");
            } else {
                I18n.printEntityMessage("discount", "entity.error.deleteFailed");
            }
        } catch (Exception ex) {
            Logger.getLogger(DiscountDeletePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("discount", "entity.title.delete");
    }
    
}
