/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.ProductCategoryPage;
import vn.aptech.quanlybanhang.pages.ProductDetailPage;
import vn.aptech.quanlybanhang.pages.ProductListingPage;
import vn.aptech.quanlybanhang.pages.ProductSearchPage;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author vanluong
 */
public class AdminMenuProduct extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();

        menuItems.put(1, new ProductListingPage());
        menuItems.put(2, new ProductSearchPage());
        menuItems.put(3, new ProductDetailPage());
        menuItems.put(4, new ProductCategoryPage());

        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());

        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return I18n.getEntityMessage("product", "entity.title.manage", true);
    }
    
}
