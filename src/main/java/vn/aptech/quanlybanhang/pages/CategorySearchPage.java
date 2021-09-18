/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
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
 *
 * @author vanluong
 */
public class CategorySearchPage extends Page {

    @Override
    public void displayContent() {

        String keyword = AppScanner.scanStringWithMessage(I18n.getEntityMessage("category", "entity.scan.searchKeyword"));

        CategoryService categoryService = new CategoryServiceImpl();

        try {
            List<Category> categories = categoryService.searchByName(keyword);
            List<Object[]> rows = new ArrayList<>();

            System.out.println("\n\n");

            if (categories.isEmpty()) {
                I18n.printEntityMessage("category", "entity.msg.emptyResults");
            } else {
                // transfer data to table row
                for (Category cat : categories) {
                    Object[] row = {
                        cat.getCategoryId(),
                        cat.getCategoryName(),
                        cat.getProductsCount()
                    };

                    rows.add(row);
                }

                String[] headers = {"ID", I18n.getMessage("category.name"), I18n.getMessage("category.productsCount")};

                TableUI theTable = new TableUI(headers, rows);
                theTable.display();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorySearchPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("category", "entity.title.search", true);
    }

}
