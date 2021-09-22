/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.ValidateCommon;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.service.SupplierService;
import vn.aptech.quanlybanhang.service.SupplierServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

public class SupplierCreatePage extends Page {

    private final SupplierService supplierService;

    public SupplierCreatePage() {
        this.supplierService = new SupplierServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            String name = AppScanner.scanStringWithi18Message("supplier.scan.name");
            String address = AppScanner.scanStringWithi18Message("supplier.scan.addr");

            while (ValidateCommon.isInvalidStringLength(name, 3, 100)) {
                name = AppScanner.scanStringWithi18Message("supplier.scan.name");

                if (ValidateCommon.isInvalidStringLength(name, 3, 100)) {
                    I18n.print("entity.error.invalidNameLength", new Object[]{3, 100});
                }
            }

            while (ValidateCommon.isInvalidStringLength(address, 3, 255)) {
                address = AppScanner.scanStringWithi18Message("supplier.scan.addr");

                if (ValidateCommon.isInvalidStringLength(address, 3, 255)) {
                    I18n.print("entity.error.invalidNameLength", new Object[]{3, 255});
                }
            }

            if (name.length() > 0 && address.length() > 0) {
                Supplier supplier = new Supplier(name, address);
                if (supplierService.create(supplier)) {
                    I18n.printEntityMessage("supplier", "entity.msg.created");
                } else {
                    I18n.printEntityMessage("supplier", "entity.msg.createFailed");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(SupplierCreatePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("supplier", "entity.title.create");
    }

}
