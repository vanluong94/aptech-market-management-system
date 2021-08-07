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

public class SupplierEditPage extends Page {

    @Override
    public void displayContent() {
        SupplierService supplierService = new SupplierServiceImpl();
        boolean retry;
        do {
            retry = false;
            try {
                int id = AppScanner.scanIntWithMessage("Nhap ID cho Nha cung cap muon sua thong tin : ");
                Supplier supplier = supplierService.findById(id);
                if (supplier == null) {
                    System.out.println("ID khong hop le!");
                    retry = true;
                } else {
                    System.out.println("\n\nNhap thong tin moi cho Nha cung cap,bo trong neu giu nguyen.");
                    String newName = AppScanner.scanStringWithMessage("Nhap ten moi cho Nha cung cap : ");
                    String newAdd = AppScanner.scanStringWithMessage("Nhap dia chi moi cho Nha cung cap : ");

                    if (newName.length() > 0) {
                        supplier.setName(newName);
                    }
                    if (newAdd.length() > 0) {
                        supplier.setAddress(newAdd);
                    }
                    supplierService.update(supplier);
                    System.out.println("Cap nhat thanh cong!");
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
        return "Sua Nha cung cap";
    }

}
