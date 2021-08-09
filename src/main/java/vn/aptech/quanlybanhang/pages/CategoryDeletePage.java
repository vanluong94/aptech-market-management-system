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
 * @author vanluong
 */
public class CategoryDeletePage extends Page {

    @Override
    public void displayContent() {
        try {

            Category theCat;

            do {
                int id = AppScanner.scanIntWithMessage("\nNhap ID cho Danh Muc can xoa: ");

                CategoryService categoryService = new CategoryServiceImpl();
                theCat = categoryService.findById(id);

                if (theCat != null) {

                    HeaderUI.display("Thong tin Danh Muc");
                    System.out.println(theCat.toString());

                    String confirm = AppScanner.scanStringWithMessage("Ban co chac chan muon xoa Danh Muc nay? (Y/N): ");

                    if (confirm.toLowerCase().equals("y")) {
                        System.out.println(""); //margin line
                        if (categoryService.deleteById(theCat.getCategoryId())) {
                            System.out.println("Xoa thanh cong ten danh muc!");
                        } else {
                            System.out.println("Da xay ra loi, khong the xoa Danh Muc.");
                        }
                    } else {
                        theCat = null; // let user select category again
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
        return "Xoa Danh muc";
    }

}
