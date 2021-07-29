/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu.items;

import vn.aptech.quanlybanhang.menu.Breadcrumb;
import vn.aptech.quanlybanhang.menu.MenuItem;

/**
 *
 * @author vanluong
 */
public class GoBackMenuItem extends MenuItem{

    @Override
    public String getTitle() {
        return "Quay lai";
    }
    
    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void start(){
        Breadcrumb.goBack();
    }
    
}
