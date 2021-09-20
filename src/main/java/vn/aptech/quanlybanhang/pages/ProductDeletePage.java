/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

public class ProductDeletePage extends Page {

    @Override
    public void displayContent() {
        
        ProductService productService = new ProductServiceImpl();
        
        while(true){
            
            try{
                
                int productId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("product", "entity.scan.id.delete"));
                Product product = productService.findById(productId);

                if (product == null) {
                    I18n.printEntityMessage("product", "entity.error.idNotFound");
                    continue;
                } 
                
                System.out.println("");
                I18n.print("entity.msg.foundName", I18n.getMessage("product.label.singular"), product.getName());
                System.out.println("");
                
                if (AppScanner.confirm(I18n.getEntityMessage("product", "entity.confirm.delete"))) {
                    if (productService.productHasOrder(product)){
                        I18n.print("product.error.preventDelete");
                    } else if (productService.deleteById(productId)) {
                        I18n.printEntityMessage("product", "entity.msg.deleted");
                    }else{
                        I18n.printEntityMessage("product", "entity.error.deleteFailed");
                    }
                }
                
                System.out.println("");
                if (!AppScanner.confirm(I18n.getEntityMessage("product", "entity.confirm.deleteAnother"))) {
                    System.out.println("");
                    break;
                }
            } catch (Exception ex) {
                Logger.getLogger(ProductDeletePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("product", "entity.title.delete");
    }

}
