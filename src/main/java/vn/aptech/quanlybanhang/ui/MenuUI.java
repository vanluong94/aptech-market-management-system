/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.ui;

import java.util.List;
import java.util.Map;
import vn.aptech.quanlybanhang.menu.Breadcrumb;
import vn.aptech.quanlybanhang.menu.MenuItem;

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

        int spaceLength = this.getLineLength() - 2 - title.length();
        int paddingLeft, paddingRight;

        if (spaceLength % 2 == 1) {
            paddingLeft = spaceLength / 2;
            paddingRight = paddingLeft + 1;
        } else {
            paddingLeft = paddingRight = spaceLength / 2;
        }

        String paddingLeftFormat = "%1$" + paddingLeft + "s";
        String paddingRightFormat = "%1$" + paddingRight + "s";

        String lineFormat = "|" + paddingLeftFormat + title + paddingRightFormat + "|";

        System.out.println(String.format(lineFormat, " "));
    }

    protected void displayBorder() {
        int dotCount = this.getLineLength() - 2;
        String borderFormat = "+%" + dotCount + "s+";
        System.out.println(String.format(borderFormat, " ").replace(" ", "-"));
    }

    private int getLineLength() {
        return lineLength;
    }

    private void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }
    
}
