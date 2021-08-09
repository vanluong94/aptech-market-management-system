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

/**
 *
 * @author vanluong
 */
public class CategorySearchPage extends Page {

    @Override
    public void displayContent() {

        String keyword = AppScanner.scanStringWithMessage("Nhap tu khoa cho ten Danh Muc can tim: ");

        CategoryService categoryService = new CategoryServiceImpl();

        try {
            List<Category> categories = categoryService.searchByName(keyword);
            List<Object[]> rows = new ArrayList<>();

            System.out.println("\n\n");

            if (categories.isEmpty()) {
                System.out.println("<Khong tim thay Danh muc nao>");
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

                String[] headers = {"ID", "Ten Danh Muc", "So San Pham"};

                TableUI theTable = new TableUI(headers, rows);
                theTable.display();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorySearchPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Tim kiem Danh Muc";
    }

}
