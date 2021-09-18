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
import vn.aptech.quanlybanhang.utilities.I18n;


public class SupplierCreatePage extends Page {

    @Override
    public void displayContent() {
        SupplierService supplierService = new SupplierServiceImpl();   
        
        String supplierName = AppScanner.scanStringWithi18Message("supplier.scan.name");
        String supplierAddress = AppScanner.scanStringWithi18Message("supplier.scan.addr");

        if (supplierName.length() > 0 && supplierAddress.length() > 0) {
            try {
                Supplier supplier = new Supplier(supplierName, supplierAddress);
                if (supplierService.create(supplier)) {
                    I18n.printEntityMessage("supplier", "entity.msg.created");
                } else {
                    I18n.printEntityMessage("supplier", "entity.msg.createFailed");
                }
            } catch (Exception ex) {
                Logger.getLogger(SupplierCreatePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("supplier", "entity.title.create");
    }
    
}
