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
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author vanluong
 */
public class CategoryListingPage extends Page {

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("category", "entity.title.all", true);
    }

    @Override
    public void displayContent() {
        try {
            CategoryService categoryService = new CategoryServiceImpl();
            List<Category> categories = categoryService.findAll();

            if (categories.isEmpty()) {
                I18n.printEntityMessage("category", "entity.msg.emptyResults");
            } else {

                List<Object[]> rows = new ArrayList<>();

                // transfer data to table row
                for (Category cat : categories) {
                    Object[] row = {
                        cat.getId(),
                        cat.getName(),
                        cat.getProductCount()
                    };
                    rows.add(row);
                }
                String[] headers = {"ID", I18n.getMessage("category.name"), I18n.getMessage("category.productsCount")};
                TableUI theTable = new TableUI(headers, rows);
                theTable.display();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryListingPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CategoryListingPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
