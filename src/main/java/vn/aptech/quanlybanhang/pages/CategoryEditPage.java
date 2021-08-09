/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.service.CategoryService;
import vn.aptech.quanlybanhang.service.CategoryServiceImpl;
import vn.aptech.quanlybanhang.ui.HeaderUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author vanluong
 */
public class CategoryEditPage extends Page {

    @Override
    public void displayContent() {

        try {

            Category theCat;

            do {
                int id = AppScanner.scanIntWithMessage("Nhap ID cho Danh Muc can sua: ");

                CategoryService categoryService = new CategoryServiceImpl();
                theCat = categoryService.findById(id);

                if (theCat != null) {

                    HeaderUI.display("Thong tin Danh Muc");
                    System.out.println(theCat.toString());

                    HeaderUI.display("Nhap thong tin moi cho Danh Muc");
                    String newName = AppScanner.scanStringWithMessage("Ten moi cho Danh Muc: ");
                    theCat.setCategoryName(newName);

                    System.out.println(""); //margin line
                    if (categoryService.update(theCat)) {
                        System.out.println("Cap nhat thanh cong ten Danh Muc!");
                    } else {
                        System.out.println("Da xay ra loi, khong the cap nhat ten Danh Muc.");
                    }
                } else {
                    System.out.println("ID Danh Muc khong ton tai.");
                }

            } while (theCat == null);

        } catch (Exception ex) {
            Logger.getLogger(CategoryEditPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getTitle() {
        return "Sua Danh muc";
    }

}
