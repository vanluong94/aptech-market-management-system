/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.CategoryCreatePage;
import vn.aptech.quanlybanhang.pages.CategoryDeletePage;
import vn.aptech.quanlybanhang.pages.CategoryDetailPage;
import vn.aptech.quanlybanhang.pages.CategoryEditPage;
import vn.aptech.quanlybanhang.pages.CategoryListingPage;
import vn.aptech.quanlybanhang.pages.CategorySearchPage;


public class InventoryMenuCategory extends Menu {

     @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();

        menuItems.put(1, new CategoryCreatePage());
        menuItems.put(2, new CategoryEditPage());
        menuItems.put(3, new CategoryDeletePage());
        menuItems.put(4, new CategoryListingPage());
        menuItems.put(5, new CategorySearchPage());
        menuItems.put(6, new CategoryDetailPage());

        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());

        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return "Quản lý Danh mục";
    }
    
}
