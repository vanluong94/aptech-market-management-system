/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.ui;

import java.util.List;
import java.util.Map;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.menu.Breadcrumb;
import vn.aptech.quanlybanhang.menu.MenuItem;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author vanluong
 */
public abstract class MenuUI extends MenuItem {
    
    Map<Integer, MenuItem> menuItems;
    List<String> menuItemLines;
    private int lineLength = 50;

    protected abstract List<String> getMenuItemLines();


    public void displayMenu() {
        
        HelperUI.displayMargin();
            
        // minimum 50 chars
        for (String menuItem : this.getMenuItemLines()) {
            if (menuItem.length() > lineLength) {
                this.setLineLength(menuItem.length());
            }
        }

        // check title length
        if (this.getTitle().length() > lineLength) {
            this.setLineLength(this.getTitle().length());
        }

        // check breadcrumb length
        String breadcrumb = Breadcrumb.getBreadcrumb();
        if(breadcrumb.length() > lineLength){
            this.setLineLength(breadcrumb.length());
        }
        
        // Print Header
        displayBorder();
        displayHeader(Breadcrumb.getBreadcrumb());
        displayBorder();
        displayHeader(""); 
        displayHeader(this.getTitle());
        displayHeader(""); 
        displayBorder();
        
        Employee emp = AuthServiceImpl.getCurrentEmployee();
        if(emp != null){
            displayHeader(I18n.getMessage("app.hello", emp.getName(), emp.getUserName()));
        }
        
        displayBorder();

        // Print Menu List
        for (String menuItem : this.getMenuItemLines()) {
            displayLine(menuItem);
        }
        
        // Print close border
        displayBorder();
    }

    protected void displayLine(String rowContent) {
        System.out.println(String.format("| %-" + (this.getLineLength() - 3) + "s|", rowContent));
    }

    protected void displayHeader(String title) {
        HelperUI.displayBorderLineCenter(title, this.getLineLength());
    }

    protected void displayBorder() {
        HelperUI.displayBorder(this.getLineLength());
    }

    private int getLineLength() {
        return lineLength;
    }

    private void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }
    
}
