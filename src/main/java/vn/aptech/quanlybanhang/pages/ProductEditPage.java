/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.menu.ProductMenu;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;


public class ProductEditPage extends Page {

    @Override
    public void displayContent() {
        
        ProductService productService = new ProductServiceImpl();
        
        String choice = null;
        do {
            try {
                int id = AppScanner.scanIntWithMessage(I18n.getEntityMessage("product", "entity.scan.id.edit"));
                Product product = productService.findById(id);
                if (product == null) {
                    I18n.printEntityMessage("product", "entity.error.idNotFound");
                } else {
                    
                    // show product information first
                    List<Product> products = new ArrayList<>();
                    products.add(product);
                    TableUI table = Product.toTable(products);
                    table.display();
                    
                    // confirm update name
                    if (AppScanner.confirm(I18n.getMessage("product.confirm.update", I18n.getMessage("product.name")))) {
                        product.setName(AppScanner.scanStringWithMessage(I18n.getMessage("product.scan.name")));
                    }
                    
                    if (AppScanner.confirm(I18n.getMessage("product.confirm.update", I18n.getMessage("product.price")))) {
                        double price = 0;
                        while (price <= 100) {
                            price = AppScanner.scanDoubleWithMessage(I18n.getMessage("product.scan.price"));

                            if (price <= 100) {
                                I18n.print("product.error.invalidPrice");
                            } else {
                                product.setPrice(price);
                            }
                        }
                    }
                    
                    if (AppScanner.confirm(I18n.getMessage("product.confirm.update", I18n.getMessage("product.qty")))) {
                        int qty = -1;
                        while (qty < 0) {
                            qty = AppScanner.scanIntWithMessage(I18n.getMessage("product.scan.qty"), true); // quantity can be = 0

                            if (qty < 0) {
                                I18n.print("product.error.invalidQty");
                            } else {
                                product.setQuantityInStock(qty);
                            }
                        }
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
