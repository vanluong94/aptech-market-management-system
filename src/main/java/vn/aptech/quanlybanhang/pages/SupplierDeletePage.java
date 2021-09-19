/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.service.SupplierService;
import vn.aptech.quanlybanhang.service.SupplierServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;


public class SupplierDeletePage extends Page {

    @Override
    public void displayContent() {
        try {

            SupplierService supplierService = new SupplierServiceImpl();
            ProductService productService = new ProductServiceImpl();
            
            while (true) {

                int id = AppScanner.scanIntWithMessage(I18n.getEntityMessage("supplier", "entity.scan.id.delete"));

                Supplier theCat = supplierService.findById(id);

                if (theCat == null) {
                    I18n.getEntityMessage("supplier", "entity.error.idNotFound");
                    continue;
                }

                System.out.println("");
                I18n.print("entity.msg.foundName", I18n.getMessage("supplier.label.singular"), theCat.getName());
                System.out.println("");

                if (AppScanner.confirm(I18n.getEntityMessage("supplier", "entity.confirm.delete"))) {
                    if (productService.findFirstProductBySupplier(theCat) != null) {
                        I18n.print("supplier.error.delete");
                    } else if (supplierService.deleteById(theCat.getId())) {
                        I18n.printEntityMessage("supplier", "entity.msg.deleted");
                    } else {
                        I18n.printEntityMessage("supplier", "entity.error.deleteFailed");
                    }
                }
                
                System.out.println("");
                if (!AppScanner.confirm(I18n.getEntityMessage("supplier", "entity.confirm.deleteAnother"))) {
                    System.out.println("");
                    break;
                }

            }

        } catch (Exception ex) {
            Logger.getLogger(SupplierEditPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("supplier", "entity.title.delete");
    }
    
}
