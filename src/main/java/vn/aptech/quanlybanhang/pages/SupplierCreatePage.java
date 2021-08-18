/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.service.SupplierService;
import vn.aptech.quanlybanhang.service.SupplierServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;


public class SupplierCreatePage extends Page {

    @Override
    public void displayContent() {
        SupplierService supplierService = new SupplierServiceImpl();   
        
        System.out.println("Nhập tên Nhà cung cấp :");
        String supplierName = AppScanner.getScanner().nextLine();
        System.out.println("Nhập Địa chỉ Nhà cung cấp :");
        String supplierAddress = AppScanner.getScanner().nextLine();

        if (supplierName.length() > 0 && supplierAddress.length() > 0) {
            try {
                Supplier supplier = new Supplier(supplierName, supplierAddress);
                if (supplierService.create(supplier)) {
                    System.out.println("Thêm Nhà cung cấp thành công!");
                } else {
                    System.out.println("Đã xảy ra lỗi!");
                }
            } catch (Exception ex) {
                Logger.getLogger(SupplierCreatePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getTitle() {
        return "Thêm Nha Cung Cap";
    }
    
}
