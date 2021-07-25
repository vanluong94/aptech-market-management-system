/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.service.CategoryService;
import vn.aptech.quanlybanhang.service.CategoryServiceImpl;

/**
 *
 * @author anhnbt
 */
public class CategoryMenu implements BaseMenu {
    
    private final CategoryService categoryService;

    public CategoryMenu() {
        this.categoryService = new CategoryServiceImpl();
    }
    
    @Override
    public void displayMenu() {
        System.out.println("=====================");
        System.out.println("= Quản lý Danh mục =");
        System.out.println("=====================");
        System.out.println("1. Danh sách danh mục");
        System.out.println("2. Thêm danh mục");
        System.out.println("3. Xem chi ti?t Danh m?c");
        System.out.println("0. Thoát");
    }

    @Override
    public void start(Scanner scanner) {
        try {
            int choice = -1;
            this.displayMenu();
            System.out.println("Vui lòng nhập lựa ch�?n [0-1]: ");
            choice = scanner.nextInt();
            scanner.nextLine();
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
                    String categoryName = scanner.nextLine();
                    if (!categoryName.trim().equals("")) {
                        Category category = new Category(categoryName.trim());
                        if (categoryService.create(category)) {
                            System.out.println("Thêm danh mục mới thành công!");
                        } else {
                            System.out.println("�?ã xảy ra lỗi!");
                        }
                    } else {
                        System.out.println("T�n danh m?c b?t bu?c nh?p!");
                    }
                    break;
                case 3:
                    System.out.println("Nh?p ID danh m?c mu?n xem");
                    int categoryId = scanner.nextInt();
                    Category category = categoryService.findById(categoryId);
                    if (category == null) {
                        System.out.println("Kh�ng t�m th?y Danh m?c n�y!");
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
