/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.menu.ProductMenu;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;


public class ProductEditPage extends Page {

    @Override
    public void displayContent() {
        
        ProductService productService = new ProductServiceImpl();
        
        ProductSearchPage searchPage = new ProductSearchPage();
        searchPage.displayContent();
        
        String choice = null;
        do {
            try {
                int id = AppScanner.scanIntWithMessage(I18n.getEntityMessage("product", "entity.scan.id.edit"));
                Product product = productService.findById(id);
                if (product == null) {
                    I18n.getEntityMessage("product", "entity.error.idNotFound");
                } else {
                    I18n.print("product.msg.update");
                    
                    String name = AppScanner.scanStringWithMessage(I18n.getMessage("product.scan.name"));
                    double price = AppScanner.scanDoubleWithMessage(I18n.getMessage("product.scan.price"));
                    int qty = AppScanner.scanIntWithMessage(I18n.getMessage("product.scan.qty"));
                    if (name.length() > 0) {
                        product.setName(name);
                    }
                    if (price > 0) {
                        product.setPrice(price);
                    }
                    if (qty > 0) {
                        product.setQuantityInStock(qty);
                    }
                    if (productService.update(product)) {
                        I18n.printEntityMessage("product", "entity.msg.updated");
                    } else {
                        I18n.printEntityMessage("product", "entity.error.updateFailed");
                    }
                }
                choice = AppScanner.scanStringWithMessage(I18n.getEntityMessage("product", "entity.confirm.editAnother"));
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            } catch (Exception ex) {
                Logger.getLogger(ProductMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while ("y".equalsIgnoreCase(choice));
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("product", "entity.title.edit");
    }
    
}
