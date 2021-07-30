/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

/**
 *
 * @author vanluong
 */
public abstract class MenuItem {
    
    public abstract String getTitle();
    public abstract void display();
    
    public String getBreadcrumbPathName(){
        return this.getTitle();
    }
    
    public void start(){
        Breadcrumb.addMenuItem(this);
        this.display();
    }
    
}
