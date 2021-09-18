/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import vn.aptech.quanlybanhang.utilities.I18n;


public class OrderSearchByCustomerPage extends Page {

    @Override
    public void displayContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTitle() {
        return I18n.getMessage("order.title.searchByCustomer");
    }
    
    @Override
    public String getBreadcrumbPathName(){
        return I18n.getEntityMessage("order", "entity.title.search", true);
    }
    
}
