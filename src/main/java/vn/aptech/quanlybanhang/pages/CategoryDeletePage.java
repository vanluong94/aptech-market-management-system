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
                int id = AppScanner.scanIntWithMessage("\nNhập ID cho danh mục cần xóa: ");

                CategoryService categoryService = new CategoryServiceImpl();
                theCat = categoryService.findById(id);

                if (theCat != null) {

                    HeaderUI.display("Thông tin Danh mục");
                    System.out.println(theCat.toString());

                    String confirm = AppScanner.scanStringWithMessage("Bạn có chắc muốn xóa Danh mục này? (Y/N): ");

                    if (confirm.toLowerCase().equals("y")) {
                        System.out.println(""); //margin line
                        if (categoryService.deleteById(theCat.getCategoryId())) {
                            System.out.println("Xóa thành công tên Danh mục!");
                        } else {
                            System.out.println("Đã xảy ra lỗi, không thể xóa Danh mục.");
                        }
                    } else {
                        theCat = null; // let user select category again
                    }
                } else {
                    System.out.println("ID Danh mục không tồn tại");
                }

            } while (theCat == null);

        } catch (Exception ex) {
            Logger.getLogger(CategoryEditPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Xóa Danh mục";
    }

}
