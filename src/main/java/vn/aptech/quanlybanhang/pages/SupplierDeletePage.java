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
            
            System.out.println("Nhap ID nha cung cap muon xoa : ");
            int supplierId = AppScanner.getScanner().nextInt();
            if (supplierService.deleteById(supplierId)) {
                System.out.println("Xoa thanh cong Nha cung cap!");
            } else {
                System.out.println("Da xay ra loi!");
            }
        } catch (Exception ex) {
            Logger.getLogger(SupplierDeletePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Xoa Nha Cung Cap";
    }
    
}
