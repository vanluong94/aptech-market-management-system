/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.EmployeeCreatePage;
import vn.aptech.quanlybanhang.pages.EmployeeDeletePage;
import vn.aptech.quanlybanhang.pages.EmployeeEditPage;
import vn.aptech.quanlybanhang.pages.EmployeeListingPage;
import vn.aptech.quanlybanhang.pages.EmployeeSearchPage;

/**
 *
 * @author vanluong
 */
public class AdminMenuEmployee extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();

        menuItems.put(1, new EmployeeCreatePage());
        menuItems.put(2, new EmployeeEditPage());
        menuItems.put(3, new EmployeeDeletePage());
        menuItems.put(4, new EmployeeSearchPage());
        menuItems.put(5, new EmployeeListingPage());

        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());

        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return "Quản lý Nhân viên";
    }

}
