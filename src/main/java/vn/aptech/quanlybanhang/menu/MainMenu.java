/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.menu.AuthMenu;
import vn.aptech.quanlybanhang.menu.BaseMenu;
import vn.aptech.quanlybanhang.menu.CategoryMenu;
import vn.aptech.quanlybanhang.service.CategoryService;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.DBConnection;

/**
 *
 * @author anhnbt
 */
public class MainMenu extends Menu {
    
    private final String TITLE = "Phần Mềm Quản Lý Siêu Thị";
    private final int[] CHOICES = {1,2,5,0}; // for validation purpose
    private final String[] MENU_ITEMS = {
        "1. Quản lý danh mục",
        "2. Đăng Nhập",
        "5. Quản lý Nhãn hàng",
        "0. Thoát",
    };
    
    public MainMenu(){
        this.setMenuItems(this.MENU_ITEMS);
        this.setTitle(this.TITLE);
        this.setChoices(this.CHOICES);
    }

    @Override
    public void handle(int choice) {
        switch (choice) {
            case 1:
                CategoryMenu categoryMenu = new CategoryMenu();
                categoryMenu.start();
                break;
            case 2:
                AuthMenu authMenu = new AuthMenu();
                authMenu.start();
                break;
            case 5:
                BrandMenu menu = new BrandMenu();
                menu.start();
                break;
            case 0:
                System.exit(0);
        }
    }
    
}
