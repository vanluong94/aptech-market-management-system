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
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;


public class DiscountDeletePage extends Page {

    @Override
    public void displayContent() {
        
        DiscountService discountService = new DiscountServiceImpl();
        ProductService productService = new ProductServiceImpl();
        
        while(true){
            
            try{
                int id = AppScanner.scanIntWithMessage(I18n.getEntityMessage("discount", "entity.scan.id.delete"));
                Discount discount = discountService.findById(id);

                if (discount == null) {
                    I18n.getEntityMessage("discount", "entity.error.idNotFound");
                    continue;
                } 
                
                System.out.println("");
                I18n.print("entity.msg.foundName", I18n.getMessage("discount.label.singular"), discount.getName());
                System.out.println("");
                
                if (AppScanner.confirm(I18n.getEntityMessage("discount", "entity.confirm.delete"))) {
                    if (discountService.discountHasData(discount)){
                        I18n.print("discount.error.preventDelete");
                    } else if (discountService.deleteById(discount.getId())) {
                        I18n.printEntityMessage("discount", "entity.msg.deleted");
                    } else {
                        I18n.printEntityMessage("discount", "entity.error.deleteFailed");
                    }
                }
                
                System.out.println("");
                if (!AppScanner.confirm(I18n.getEntityMessage("discount", "entity.confirm.deleteAnother"))) {
                    System.out.println("");
                    break;
                }
            }catch(InputInvalidException e){
                System.out.println(e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(DiscountDeletePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("discount", "entity.title.delete");
    }
    
}
