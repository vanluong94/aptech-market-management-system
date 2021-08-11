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
                System.out.println("<Khong tim thay Danh muc nao>");
            } else {
                
                List<Object[]> rows = new ArrayList<>();
                
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
            Logger.getLogger(CategoryListingPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}