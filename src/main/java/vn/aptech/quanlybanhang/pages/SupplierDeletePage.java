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
import vn.aptech.quanlybanhang.utilities.I18n;


public class SupplierDeletePage extends Page {

    @Override
    public void displayContent() {
        try {
            SupplierService supplierService = new SupplierServiceImpl();
            
            int supplierId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("supplier", "entity.scan.id.delete"));
            if (supplierService.deleteById(supplierId)) {
                I18n.printEntityMessage("supplier", "entity.msg.deleted");
            } else {
                I18n.printEntityMessage("supplier", "entity.error.deleteFailed");
            }
        } catch (Exception ex) {
            Logger.getLogger(SupplierDeletePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("supplier", "entity.title.delete");
    }
    
}
