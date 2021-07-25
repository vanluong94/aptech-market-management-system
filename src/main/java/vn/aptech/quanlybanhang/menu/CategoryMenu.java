/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.service.CategoryService;
import vn.aptech.quanlybanhang.service.CategoryServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author anhnbt
 */
public class CategoryMenu extends Menu {
    
    private final String TITLE = "Quản lý Danh mục";
    private final int[] CHOICES = {1,2,3,0}; // for validation purpose
    private final String[] MENU_ITEMS = {
        "1. Danh sách danh mục",
        "2. Thêm danh mục",
        "3. Xem chi tiết Danh mục",
        "0. Thoát",
    };
    
    private final CategoryService categoryService;

    public CategoryMenu() {
        
        this.setMenuItems(this.MENU_ITEMS);
        this.setTitle(this.TITLE);
        this.setChoices(this.CHOICES);
        
        this.categoryService = new CategoryServiceImpl();
    }

    @Override
    public void handle(int choice) {
        try {
            switch(choice) {
                case 1:
                    System.out.println("Danh sách danh mục");
                    List<Category> categories = categoryService.findAll();
                    if (categories.isEmpty()) {
                        System.out.println("Danh mục trống!");
                    } else {
                        for (Category category : categories) {
                            System.out.println(category.toString());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Nhập tên danh mục: ");
                    String categoryName = AppScanner.getScanner().nextLine();
                    if (categoryName.length() > 0) {
                        Category category = new Category(categoryName);
                        if (categoryService.create(category)) {
                            System.out.println("Thêm danh mục mới thành công!");
                        } else {
                            System.out.println("Đã xảy ra lỗi!");
                        }
                    } else {
                        System.out.println("Tên danh mục không được bỏ trống!");
                    }
                    break;
                case 3:
                    System.out.println("Nhập ID danh mục muốn xem");
                    int categoryId = AppScanner.getScanner().nextInt();
                    Category category = categoryService.findById(categoryId);
                    if (category == null) {
                        System.out.println("Không tìm thấy Danh mục này!");
                    } else {
                        System.out.println(category.toString());
                    }
                    break;
                case 0:
                    System.exit(0);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
