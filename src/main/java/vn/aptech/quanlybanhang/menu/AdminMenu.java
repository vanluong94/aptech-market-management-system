/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.SignoutMenuItem;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Admin
 */
public class AdminMenu extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();

        menuItems.put(1, new AdminMenuEmployee());
        menuItems.put(2, new AdminMenuProduct());
        menuItems.put(3, new AdminMenuSupplier());
        menuItems.put(4, new AdminMenuCategory());
        menuItems.put(5, new AdminMenuOrder());
        menuItems.put(6, new AdminMenuDiscount());
        menuItems.put(7, new AdminMenuCustomer());
        menuItems.put(8, new AdminMenuStatistic());
        menuItems.put(9, new AdminMenuProfile());

        menuItems.put(-1, new SignoutMenuItem());
        menuItems.put(0, new ExitMenuItem());

        return menuItems;
    }

    @Override
    protected final String registerMenuTitle() {
        return I18n.getMessage("app.heading");
    }

    @Override
    public String getBreadcrumbPathName() {
        return "Menu";
    }
}
