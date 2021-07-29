/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.ui;

import vn.aptech.quanlybanhang.menu.Breadcrumb;

/**
 *
 * @author vanluong
 */
public class HeaderUI {
    
    public static void display(String title){
        int lineLength = title.length() + 20; //means padding 20 for both sides
        HelperUI.displayMargin();
        displayBorder(lineLength);
        displayHeader(title, lineLength);
        displayBorder(lineLength);
    }
    
    public static void displayWithBreadcrumb(String title){
        
        String breadcrumb = Breadcrumb.getBreadcrumb();
        int lineLength = title.length() > breadcrumb.length() ? title.length() : breadcrumb.length();
        lineLength += 20;  //means padding 20 for both sides
        
        HelperUI.displayMargin();
        displayBorder(lineLength);
        displayHeader(breadcrumb, lineLength);
        displayBorder(lineLength);
        displayHeader("", lineLength);
        displayHeader(title, lineLength);
        displayHeader("", lineLength);
        displayBorder(lineLength);
    }
    
    protected static void displayHeader(String title, int length) {

        int spaceLength = length - 2 - title.length();
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
    
    protected static void displayBorder(int length) {
        int dotCount = length - 2;
        String borderFormat = "+%" + dotCount + "s+";
        System.out.println(String.format(borderFormat, " ").replace(" ", "-"));
    }

}
