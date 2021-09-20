/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.service.SupplierService;
import vn.aptech.quanlybanhang.service.SupplierServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;


public class SupplierDetailPage extends Page {

    @Override
    public void displayContent() {
        SupplierService supplierService = new SupplierServiceImpl();
        String choice = null;
        do {           
            try {
                int supplierId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("supplier", "entity.scan.id.detail"));
                Supplier supplier = supplierService.findById(supplierId);
                if (supplier == null) {
                    I18n.printEntityMessage("supplier", "entity.error.idNotFound");
                } else {
                    List<Object[]> rows = new ArrayList<>();
                    Object[] row = {
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getAddress()
                    };
                    rows.add(row);
                    
                    String[] headers = {"ID", I18n.getMessage("supplier.name"), I18n.getMessage("supplier.addr")};
                    TableUI theTable = new TableUI(headers, rows);
                    theTable.display();
                    choice = AppScanner.scanStringWithMessage(I18n.getEntityMessage("supplier", "entity.confirm.findMore"));
                    if (!"y".equalsIgnoreCase(choice)) {
                        break;
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(SupplierDetailPage.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } while ("y".equalsIgnoreCase(choice));
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("supplier", "entity.title.detail");
    }
    
}
