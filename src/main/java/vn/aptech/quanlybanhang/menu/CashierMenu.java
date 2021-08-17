/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.SignoutMenuItem;
import vn.aptech.quanlybanhang.pages.CashierStatisticPage;
import vn.aptech.quanlybanhang.pages.ChangePasswordPage;
import vn.aptech.quanlybanhang.pages.OrderCreatePage;

/**
 *
 * @author vanluong
 */
public class CashierMenu extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();
        
        menuItems.put(1, new OrderCreatePage());
        menuItems.put(2, new CashierMenuOrder());
        menuItems.put(3, new CashierMenuProduct());
        menuItems.put(4, new CashierMenuCategory());
        menuItems.put(5, new CashierStatisticPage());
        menuItems.put(6, new ChangePasswordPage());
        
        menuItems.put(7, new SignoutMenuItem());
        menuItems.put(0, new ExitMenuItem());
        
        return menuItems;
    }

    @Override
    protected final String registerMenuTitle() {
        return "Phần mềm Quản lý Siêu Thị";
    }
    
    @Override
    public String getBreadcrumbPathName(){
        return "Menu";
    }
    
}
