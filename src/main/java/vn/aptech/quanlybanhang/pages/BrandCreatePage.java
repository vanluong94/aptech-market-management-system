/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.service.BrandService;
import vn.aptech.quanlybanhang.service.BrandServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author vanluong
 */
public class BrandCreatePage extends Page {

    @Override
    public void displayContent() {
        
        try {
            BrandService brandService = new BrandServiceImpl();
            
            Brand brand = new Brand();
            
            brand.setBrandName(AppScanner.scanStringWithMessage("[Tên Nhãn hàng]: "));
            brand.setBrandAdd(AppScanner.scanStringWithMessage("[Địa chỉ Nhãn hàng]: "));
            
            brandService.create(brand);
            
            System.out.println("\nThêm Nhãn hàng thành công");
        } catch (Exception ex) {
            Logger.getLogger(BrandCreatePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Them Nhan Hang";
    }
    
}
