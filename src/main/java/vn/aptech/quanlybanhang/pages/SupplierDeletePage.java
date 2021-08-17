/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.service.SupplierService;
import vn.aptech.quanlybanhang.service.SupplierServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;


public class SupplierDeletePage extends Page {

    @Override
    public void displayContent() {
        try {
            SupplierService supplierService = new SupplierServiceImpl();
            
            System.out.println("Nhập ID Nhà cung cấp muốn xóa : ");
            int supplierId = AppScanner.getScanner().nextInt();
            if (supplierService.deleteById(supplierId)) {
                System.out.println("Xóa thành công Nhà cung cấp!");
            } else {
                System.out.println("Đã xảy ra lỗi!");
            }
        } catch (Exception ex) {
            Logger.getLogger(SupplierDeletePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Xóa Nha Cung Cap";
    }
    
}
