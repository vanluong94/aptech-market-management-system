/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vanluong
 */
public class Breadcrumb {
    
    private static List<MenuItem> menuItems = new ArrayList<>();
    
    public static void addMenuItem(MenuItem item){
        menuItems.add(item);
    }

    public static String getBreadcrumb() {
        List<String> paths = new ArrayList<>();
        for(MenuItem item : menuItems){
            paths.add(item.getBreadcrumbPathName());
        }
        return String.join(" > ", paths);
    }
    
    public static void goBack(){
        menuItems.remove(getLastItem());
        getLastItem().display(); // since .start() method will add another item to breadcrumb, so we can call directly to .display()
    }
    
    public static MenuItem getLastItem(){
        return menuItems.get(menuItems.size() - 1);
    }
    
    public static void reset(){
        menuItems.clear();
    }
}
