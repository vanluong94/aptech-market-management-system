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
import vn.aptech.quanlybanhang.pages.OrderCreateWithCustomerPage;

/**
 *
 * @author vanluong
 */
public class CashierMenu extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();

        menuItems.put(1, new OrderCreatePage());
        menuItems.put(2, new OrderCreateWithCustomerPage());
        menuItems.put(3, new CashierMenuOrder());
        menuItems.put(4, new CashierMenuProduct());
        menuItems.put(5, new CashierMenuCategory());
        menuItems.put(6, new CashierStatisticPage());
        menuItems.put(7, new CashierMenuCustomer());
        menuItems.put(8, new ChangePasswordPage());

        menuItems.put(-1, new SignoutMenuItem());
        menuItems.put(0, new ExitMenuItem());

        return menuItems;
    }

    @Override
    protected final String registerMenuTitle() {
        return "Phần m�?m Quản lý Siêu Thị";
    }

    @Override
    public String getBreadcrumbPathName() {
        return "Menu";
    }

}
