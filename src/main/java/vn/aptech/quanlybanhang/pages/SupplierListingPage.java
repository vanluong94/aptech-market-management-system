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
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.service.SupplierService;
import vn.aptech.quanlybanhang.service.SupplierServiceImpl;


public class SupplierListingPage extends Page {

    @Override
    public void displayContent() {
        try {
            SupplierService supplierService = new SupplierServiceImpl();
            
            List<Supplier> suppliers = supplierService.findAll();
            if (suppliers.isEmpty()) {
                System.out.println("Danh sách trống");
            } else {
                for (Supplier supplier : suppliers) {
                    System.out.println(supplier.toString());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierListingPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Danh Sach Nha Cung Cap";
    }
    
}
