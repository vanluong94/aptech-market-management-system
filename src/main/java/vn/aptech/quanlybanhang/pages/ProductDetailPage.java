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


public class ProductDetailPage extends Page {

    @Override
    public void displayContent() {
        try {
            ProductService productService = new ProductServiceImpl();
            int productId = AppScanner.scanIntWithMessage("Nhap ID san phan ban muon xem : ");
            Product product = productService.findById(productId);
            if (product == null) {
                System.out.println("Khong tim thay ID San pham phu hop!");
            } else {
                System.out.println(product.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDetailPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Tim san pham trong kho theo ID San pham";
    }
    
}
