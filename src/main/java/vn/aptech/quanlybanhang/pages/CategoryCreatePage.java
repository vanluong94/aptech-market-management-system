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
import vn.aptech.quanlybanhang.utilities.I18n;

public class CategoryCreatePage extends Page {

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("category", "entity.title.create");
    }

    @Override
    public void displayContent() {

        CategoryService categoryService = new CategoryServiceImpl();

        String categoryName = AppScanner.scanStringWithMessage(I18n.getEntityMessage("category", "category.scan.name"));

        try {
            Category category = new Category(categoryName);
            if (categoryService.create(category)) {
                I18n.printEntityMessage("category", "entity.msg.created");
            } else {
                I18n.printEntityMessage("category", "entity.error.createFailed");
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryCreatePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
