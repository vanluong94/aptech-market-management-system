/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.OrderDetailPage;
import vn.aptech.quanlybanhang.pages.OrderListingPage;
import vn.aptech.quanlybanhang.pages.OrderSearchByCustomerPage;
import vn.aptech.quanlybanhang.pages.OrderSearchByDatePage;


public class InventoryMenuOrder extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();

        menuItems.put(1, new OrderDetailPage());
        menuItems.put(2, new OrderListingPage());
        menuItems.put(3, new OrderSearchByCustomerPage());
        menuItems.put(4, new OrderSearchByDatePage());

        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());

        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return "Tra cứu Hóa đơn";
    }
    
}
