/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;


public class ProductCategoryPage extends Page {

    @Override
    public void displayContent() {
        try {
            Product product = null;
            ProductService productService = new ProductServiceImpl();
            int categoryId = AppScanner.scanIntWithMessage("Nhap ID Loai san pham ban muon xem : ");
            List<Product> products = productService.findByCategoryId(categoryId);
            if (products.isEmpty()) {
                System.out.println("Danh sach trong");
            } else {
                System.out.println(product.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Xem danh sach San Pham theo Loai san pham";
    }
    
    
    @Override
    public String getBreadcrumbPathName(){
        return "Danh sach SP theo Loai";
    }
    
}
