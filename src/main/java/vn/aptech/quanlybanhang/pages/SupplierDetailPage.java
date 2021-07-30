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


public class SupplierDetailPage extends Page {

    @Override
    public void displayContent() {
        try {
            SupplierService supplierService = new SupplierServiceImpl();
            System.out.println("Nhap ID nha cung cap muon kiem tra : ");
            int supplierId = AppScanner.getScanner().nextInt();
            Supplier supplier = supplierService.findById(supplierId);
            if (supplier == null) {
                System.out.println("Khong tim thay ID nha cung cap phu hop");
            } else {
                System.out.println(supplier.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(SupplierDetailPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Xem chi tiet Nha Cung Cap";
    }
    
}
