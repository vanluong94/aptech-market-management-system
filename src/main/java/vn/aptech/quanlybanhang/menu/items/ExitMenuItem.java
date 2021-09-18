/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu.items;

import vn.aptech.quanlybanhang.menu.MenuItem;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author vanluong
 */
public class ExitMenuItem extends MenuItem {

    @Override
    public void display() {
        System.exit(0);
    }

    @Override
    public String getTitle() {
        return I18n.getMessage("app.exit");
    }
    
}
