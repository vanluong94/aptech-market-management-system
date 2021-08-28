/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;

public class ProductListingPage extends Page {

    @Override
    public void displayContent() {
        try {
            ProductService productService = new ProductServiceImpl();
            List<Product> products = productService.findAll();
            if (products.isEmpty()) {
                System.out.println("Danh sách trống");
            } else {
                for (Product product : products) {
                    System.out.println(product.toString());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDetailPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Danh sách Sản phẩm";
    }

}
