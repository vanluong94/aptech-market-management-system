/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.sql.SQLException;
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
        System.out.println("1. Thêm danh mục");
        System.out.println("0. Thoát");
    }

    @Override
    public void start(Scanner scanner) {
        try {
            int choice = -1;
            this.displayMenu();
            System.out.println("Vui lòng nhập lựa chọn [0-1]: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice) {
                case 1:
                    System.out.println("Nhập tên danh mục: ");
                    String categoryName = scanner.nextLine();
                    if (categoryName.length() > 0) {
                        Category category = new Category(categoryName);
                        if (categoryService.saveOrUpdate(category)) {
                            System.out.println("Thêm danh mục mới thành công!");
                        } else {
                            System.out.println("Đã xảy ra lỗi!");
                        }

                    } else {
                        System.out.println("Tên danh mục không được bỏ trống!");
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
