/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.service.CategoryService;
import vn.aptech.quanlybanhang.service.CategoryServiceImpl;

/**
 *
 * @author vanluong
 */
public class CategoryListingPage extends Page {

    @Override
    public String getTitle() {
        return "Danh Sach Danh Muc";
    }
    
    @Override
    public void displayContent() {
        try {
            CategoryService categoryService = new CategoryServiceImpl();
            List<Category> categories = categoryService.findAll();
            if (categories.isEmpty()) {
                System.out.println("Danh mục trống!");
            } else {
                for (Category category : categories) {
                    System.out.println(category.toString());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryListingPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
