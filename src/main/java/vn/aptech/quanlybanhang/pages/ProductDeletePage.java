/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.service.SupplierServiceImpl;

public class ProductDeletePage extends Page {

    @Override
    public void displayContent() {
        try {
            ProductService productService = new ProductServiceImpl();
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhập ID Sản phẩm muốn xóa : ");
            int productId = sc.nextInt();
            if (productService.deleteById(productId)) {
                System.out.println("Xóa Sản phẩm thành công!");
            }else{
                System.out.println("Đã xảy ra lỗi");
            }
        } catch (Exception e) {
              Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public String getTitle() {
        return "Xóa Sản phẩm";
    }

}
