/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.service.CategoryService;
import vn.aptech.quanlybanhang.service.CategoryServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 * @author vanluong
 */
public class CategoryDetailPage extends Page {

    @Override
    public void displayContent() {
        CategoryService categoryService = new CategoryServiceImpl();
        String choice = null;
        do {
            try {
                int categoryId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("category", "entity.scan.id.detail"));
                Category category = categoryService.findById(categoryId);
                if (category == null) {
                    I18n.printEntityMessage("category", "entity.msg.emptyResults");
                } else {
                    List<Object[]> rows = new ArrayList<>();
                    Object[] row = {
                        category.getId(),
                        category.getName(),
                        category.getProductCount()
                    };
                    rows.add(row);
                    String[] headers = {"ID", I18n.getMessage("category.name"), I18n.getMessage("category.productsCount")};
                    TableUI theTable = new TableUI(headers, rows);
                    theTable.display();
                    choice = AppScanner.scanStringWithMessage(I18n.getMessage("entity.confirm.searchAnOther"));
                    if (!"y".equalsIgnoreCase(choice)) {
                        break;
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(CategoryDetailPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while ("y".equalsIgnoreCase(choice));
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("category", "entity.title.detail");
    }
}
