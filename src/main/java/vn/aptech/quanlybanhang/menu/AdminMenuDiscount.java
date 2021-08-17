/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.DiscountCreatePage;
import vn.aptech.quanlybanhang.pages.DiscountDeletePage;
import vn.aptech.quanlybanhang.pages.DiscountDetailPage;
import vn.aptech.quanlybanhang.pages.DiscountEditPage;
import vn.aptech.quanlybanhang.pages.DiscountListingPage;


public class AdminMenuDiscount extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();
        
        menuItems.put(1, new DiscountListingPage());
        menuItems.put(2, new DiscountDetailPage());
        menuItems.put(3, new DiscountCreatePage());
        menuItems.put(4, new DiscountEditPage());
        menuItems.put(5, new DiscountDeletePage());
        
        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());
        
        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return "Quản lý Chương trình giảm giá";
    }
    
}
