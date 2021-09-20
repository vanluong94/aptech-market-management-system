/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import vn.aptech.quanlybanhang.common.ValidateCommon;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.exception.InputInvalidException;
import vn.aptech.quanlybanhang.service.SupplierService;
import vn.aptech.quanlybanhang.service.SupplierServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

public class SupplierEditPage extends Page {

    private final SupplierService supplierService;

    public SupplierEditPage() {
        this.supplierService = new SupplierServiceImpl();
    }

    @Override
    public void displayContent() {
        boolean retry;
        do {
            retry = false;
            try {
                int id = AppScanner.scanIntWithMessage(I18n.getEntityMessage("supplier", "entity.scan.id.edit"));

                Supplier supplier = supplierService.findById(id);
                if (supplier == null) {
                    System.out.println(I18n.getEntityMessage("supplier", "entity.error.idNotFound"));
                    retry = true;
                } else {
                    I18n.print("supplier.msg.update");
                    String name = AppScanner.scanStringWithi18Message("supplier.scan.name.new");
                    String address = AppScanner.scanStringWithi18Message("supplier.scan.addr.new");

                    while (ValidateCommon.isValidStringLength(name, 3, 100)) {
                        name = AppScanner.scanStringWithi18Message("product.scan.name");

                        if (ValidateCommon.isValidStringLength(name, 3, 100)) {
                            I18n.print("entity.error.invalidNameLength", new Object[]{3, 100});
                        }
                    }

                    while (ValidateCommon.isValidStringLength(address, 3, 255)) {
                        address = AppScanner.scanStringWithi18Message("supplier.scan.addr.new");

                        if (ValidateCommon.isValidStringLength(address, 3, 255)) {
                            I18n.print("entity.error.invalidNameLength", new Object[]{3, 255});
                        }
                    }

                    if (name.length() > 0) {
                        supplier.setName(name);
                    }
                    if (address.length() > 0) {
                        supplier.setAddress(address);
                    }
                    if (supplierService.update(supplier)) {
                        I18n.printEntityMessage("supplier", "entity.msg.updated");
                    } else {
                        I18n.printEntityMessage("supplier", "entity.error.updateFailed");
                    }
                }
            } catch (InputInvalidException e) {
                System.out.println(e.getMessage());
                retry = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (retry);

    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("supplier", "entity.title.edit");
    }

}
