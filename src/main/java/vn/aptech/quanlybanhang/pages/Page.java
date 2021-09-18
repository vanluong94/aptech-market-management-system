/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import vn.aptech.quanlybanhang.menu.Breadcrumb;
import vn.aptech.quanlybanhang.menu.MenuItem;
import vn.aptech.quanlybanhang.ui.HeaderUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author vanluong
 */
public abstract class Page extends MenuItem{
    
    public abstract void displayContent();
    
    @Override
    public void start(){
        super.start();
        AppScanner.scanStringWithMessage(I18n.getMessage("app.enterToReturn"), true);
        Breadcrumb.goBack();
    }
    
    @Override
    public void display(){
        this.displayHeader();
        this.displayContent();
    }
    
    public void displayHeader(){
        HeaderUI.displayWithBreadcrumb(this.getTitle());
    }
}
