/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.ProductCreatePage;
import vn.aptech.quanlybanhang.pages.ProductDeletePage;
import vn.aptech.quanlybanhang.pages.ProductDetailPage;
import vn.aptech.quanlybanhang.pages.ProductEditPage;
import vn.aptech.quanlybanhang.pages.ProductListingPage;
import vn.aptech.quanlybanhang.pages.ProductSearchPage;


public class InventoryMenuProduct extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();

        menuItems.put(1, new ProductListingPage());
        menuItems.put(2, new ProductSearchPage());
        menuItems.put(3, new ProductDetailPage());
        menuItems.put(4, new ProductCreatePage());
        menuItems.put(5, new ProductEditPage());
        menuItems.put(6, new ProductDeletePage());

        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());

        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return "Quan ly San Pham";
    }
    
}