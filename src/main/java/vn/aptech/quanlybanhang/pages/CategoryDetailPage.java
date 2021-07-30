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

/**
 *
 * @author vanluong
 */
public class CategoryDetailPage extends Page {

    @Override
    public String getTitle() {
        return "Chi Tiet Danh Muc";
    }

    @Override
    public void displayContent() {
        try {
            CategoryService categoryService = new CategoryServiceImpl();
            System.out.println("Nhập ID danh mục muốn xem");
            int categoryId = AppScanner.getScanner().nextInt();
            Category category = categoryService.findById(categoryId);
            if (category == null) {
                System.out.println("Không tìm thấy Danh mục này!");
            } else {
                System.out.println(category.toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDetailPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
