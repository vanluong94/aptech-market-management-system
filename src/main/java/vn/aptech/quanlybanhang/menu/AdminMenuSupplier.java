/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.SupplierCreatePage;
import vn.aptech.quanlybanhang.pages.SupplierDeletePage;
import vn.aptech.quanlybanhang.pages.SupplierDetailPage;
import vn.aptech.quanlybanhang.pages.SupplierEditPage;
import vn.aptech.quanlybanhang.pages.SupplierListingPage;
import vn.aptech.quanlybanhang.pages.SupplierSearchPage;

/**
 *
 * @author vanluong
 */
public class AdminMenuSupplier extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();

        menuItems.put(1, new SupplierCreatePage());
        menuItems.put(2, new SupplierEditPage());
        menuItems.put(3, new SupplierDeletePage());
        menuItems.put(4, new SupplierSearchPage());
        menuItems.put(5, new SupplierDetailPage());
        menuItems.put(6, new SupplierListingPage());

        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());

        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return "Quan ly Nha cung cap";
    }
    
}
