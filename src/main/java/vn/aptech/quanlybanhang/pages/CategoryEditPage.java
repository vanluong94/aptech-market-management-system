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
                int id = AppScanner.scanIntWithMessage("Nhập ID cho danh mục cần sửa: ");

                CategoryService categoryService = new CategoryServiceImpl();
                theCat = categoryService.findById(id);

                if (theCat != null) {

                    HeaderUI.display("Thông tin Danh mục");
                    System.out.println(theCat.toString());

                    HeaderUI.display("Nhập thông tin mới cho Danh mục");
                    String newName = AppScanner.scanStringWithMessage("Tên mới cho Danh mục: ");
                    theCat.setCategoryName(newName);

                    System.out.println(""); //margin line
                    if (categoryService.update(theCat)) {
                        System.out.println("Cập nhật thành công tên Danh mục!");
                    } else {
                        System.out.println("Đã xảy ra lỗi, không thể cập nhật Danh mục.");
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
        return "Sửa Danh mục";
    }

}
