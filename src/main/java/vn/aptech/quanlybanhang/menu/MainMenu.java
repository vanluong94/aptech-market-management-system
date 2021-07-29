/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.SignoutMenuItem;
import vn.aptech.quanlybanhang.pages.AuthPage;
import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;

/**
 *
 * @author anhnbt
 */
public class MainMenu extends Menu {
    
    @Override
    protected final String registerMenuTitle() {
        return "Phần Mềm Quản Lý Siêu Thị";
    }

    @Override
    protected final LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();
        
//        menuItems.put(1, new ProductMenu());
        menuItems.put(2, new CategoryMenu());
        menuItems.put(3, new DiscountMenu());
        menuItems.put(4, new SupplierMenu());
        menuItems.put(5, new BrandMenu());
        menuItems.put(6, new SignoutMenuItem());
        menuItems.put(0, new ExitMenuItem());
        
        return menuItems;
    }
    
    @Override
    public String getBreadcrumbPathName(){
        return "Menu";
    }
    
}
