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
                int categoryId = AppScanner.scanIntWithMessage("Nhập ID danh mục muốn xem : ");
                Category category = categoryService.findById(categoryId);
                if (category == null) {
                    System.out.println("Không tìm thấy ID Danh mục phù hợp");
                } else {
                    List<Object[]> rows = new ArrayList<Object[]>();
                    Object[] row = {
                        category.getCategoryId(),
                        category.getCategoryName(),
                        category.getProductsCount()
                    };
                    rows.add(row);
                    String[] headers = {"ID", "Tên Danh mục", "Số Sản phẩm"};
                    TableUI theTable = new TableUI(headers, rows);
                    theTable.display();
                    choice = AppScanner.scanStringWithMessage("Bạn có muốn tìm Danh mục khác không ? [Y/N] : ");
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
        return "Chi tiết Danh mục";
    }
}
