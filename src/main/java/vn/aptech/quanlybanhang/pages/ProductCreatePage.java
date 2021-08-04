/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.AuthService;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;

public class ProductCreatePage extends Page {

    @Override
    public void displayContent() {
        ProductService productService = new ProductServiceImpl();
        Brand brandId = new Brand();
        brandId.setBrandId(AppScanner.scanIntWithMessage("Nhap Brand ID : "));

        Category categoryId = new Category();
        categoryId.setCategoryId(AppScanner.scanIntWithMessage("Nhap Category ID :"));

//        Employee employeeId = new Employee();
//        employeeId.getEmployeeId();

//        AuthService employeeId2 = new AuthServiceImpl();
//        employeeId2.getCurrentEmployee();
        
        
        String productName = AppScanner.scanStringWithMessage("Nhap ten San Pham :");
        double productPrice = AppScanner.scanDoubleWithMessage("Gia tien :");
        int productStock = AppScanner.scanIntWithMessage("So luong :");

        Product productNew = new Product(brandId, categoryId, AuthServiceImpl.getCurrentEmployee(), productName, productPrice, productStock);

        try {
            if (productService.create(productNew)) {
                System.out.println("Them San Pham moi thanh cong!");
            } else {
                System.out.println("Da xay ra loi");
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductCreatePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Tao San Pham";
    }


}
