/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.exception.InputInvalidException;
import vn.aptech.quanlybanhang.service.SupplierService;
import vn.aptech.quanlybanhang.service.SupplierServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

public class SupplierEditPage extends Page {

    @Override
    public void displayContent() {
        SupplierService supplierService = new SupplierServiceImpl();
        boolean retry;
        do {
            retry = false;
            try {
                int id = AppScanner.scanIntWithMessage("Nhập ID cho Nhà cung cấp muốn sửa Thông tin : ");
                Supplier supplier = supplierService.findById(id);
                if (supplier == null) {
                    I18n.getEntityMessage("supplier", "entity.error.idNotFound");
                    retry = true;
                } else {
                    System.out.println("\n\nNhập Thông tin mới cho Nhà cung cấp, bỏ trống nếu giữ nguyên.");
                    String newName = AppScanner.scanStringWithMessage("Nhập tên mới cho Nhà cung cấp : ");
                    String newAdd = AppScanner.scanStringWithMessage("Nhập Địa chỉ mới cho Nhà cung cấp : ");

                    if (newName.length() > 0) {
                        supplier.setName(newName);
                    }
                    if (newAdd.length() > 0) {
                        supplier.setAddress(newAdd);
                    }
                    supplierService.update(supplier);
                    System.out.println("Cập nhật thành công!");
                }
            } catch (InputInvalidException e) {
                System.out.println(e.getMessage());
                retry = true;
            }catch (Exception ex){
                Logger.getLogger(SupplierEditPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (retry);

    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("supplier", "entry.title.edit");
    }

}
