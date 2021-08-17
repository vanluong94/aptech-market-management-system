/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.BrandCreatePage;
import vn.aptech.quanlybanhang.pages.BrandDeletePage;
import vn.aptech.quanlybanhang.pages.BrandEditPage;
import vn.aptech.quanlybanhang.pages.BrandListingPage;
import vn.aptech.quanlybanhang.pages.BrandSearchPage;


public class InventoryMenuBrand extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();
        
        menuItems.put(1, new BrandListingPage());
        menuItems.put(2, new BrandCreatePage());
        menuItems.put(3, new BrandEditPage());
        menuItems.put(4, new BrandDeletePage());
        menuItems.put(5, new BrandSearchPage());
        
        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());
        
        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return "Quản lý Nhãn hàng";
    }
    
}
