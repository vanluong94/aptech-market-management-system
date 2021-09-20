/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

public class ProductDeletePage extends Page {

    @Override
    public void displayContent() {
        try {
            ProductService productService = new ProductServiceImpl();
            int productId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("product", "entity.scan.id.delete"));
            if (productService.deleteById(productId)) {
                I18n.printEntityMessage("product", "entity.msg.deleted");
            }else{
                I18n.printEntityMessage("product", "entity.error.deleteFailed");
            }
        } catch (Exception e) {
              Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("product", "entity.title.delete");
    }

}
