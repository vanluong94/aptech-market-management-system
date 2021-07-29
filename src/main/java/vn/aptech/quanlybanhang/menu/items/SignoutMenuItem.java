/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu.items;

import vn.aptech.quanlybanhang.Main;
import vn.aptech.quanlybanhang.menu.MenuItem;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.ui.HelperUI;

/**
 *
 * @author vanluong
 */
public class SignoutMenuItem extends MenuItem {

    @Override
    public String getTitle() {
        return "Dang xuat";
    }

    @Override
    public void display() {
        AuthServiceImpl.logout();
        
        HelperUI.displayMargin();
        System.out.println("Dang xuat thanh cong!");
        
        Main.start();
    }
    
}
