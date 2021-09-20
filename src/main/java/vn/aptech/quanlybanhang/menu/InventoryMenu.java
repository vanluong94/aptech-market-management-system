/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.SignoutMenuItem;
import vn.aptech.quanlybanhang.pages.ChangePasswordPage;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author vanluong
 */
public class InventoryMenu extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();
        
        menuItems.put(1, new InventoryMenuImport());
        menuItems.put(2, new InventoryMenuProduct());
        menuItems.put(3, new InventoryMenuCategory());
        menuItems.put(4, new InventoryMenuSupplier());
        menuItems.put(5, new InventoryMenuBrand());
        menuItems.put(6, new InventoryMenuOrder());
        menuItems.put(7, new InventoryMenuStatistic());
        menuItems.put(8, new ChangePasswordPage());
        
        menuItems.put(-1, new SignoutMenuItem());
        menuItems.put(0, new ExitMenuItem());
        
        return menuItems;
    }

    @Override
    protected final String registerMenuTitle() {
        return I18n.getMessage("app.heading");
    }
    
    @Override
    public String getBreadcrumbPathName(){
        return "Menu";
    }
    
}
