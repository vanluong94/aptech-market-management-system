/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.service.CategoryService;
import vn.aptech.quanlybanhang.service.CategoryServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;

public class CategoryCreatePage extends Page {

    @Override
    public String getTitle() {
        return "Them Danh Muc";
    }

    @Override
    public void displayContent() {

        CategoryService categoryService = new CategoryServiceImpl();

        System.out.println("Nhập tên danh mục: ");
        String categoryName = AppScanner.getScanner().nextLine();
        if (categoryName.length() > 0) {
            try {
                Category category = new Category(categoryName);
                if (categoryService.create(category)) {
                    System.out.println("Thêm danh mục mới thành công!");
                } else {
                    System.out.println("Đã xảy ra lỗi!");
                }
            } catch (Exception ex) {
                Logger.getLogger(CategoryCreatePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Tên danh mục không được bỏ trống!");
        }
    }

}
