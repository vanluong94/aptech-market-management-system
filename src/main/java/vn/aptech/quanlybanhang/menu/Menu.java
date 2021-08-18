/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import vn.aptech.quanlybanhang.exception.MenuException;
import vn.aptech.quanlybanhang.ui.MenuUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;

public abstract class Menu extends MenuUI {
    
    private LinkedHashMap<Integer, MenuItem> menuItems;
    
    protected abstract LinkedHashMap<Integer, MenuItem> registerMenuItems();
    protected abstract String registerMenuTitle();
            
    protected void handle(int choice) throws MenuException{
        if( isChoiceValid(choice) ){
            this.getMenuItems().get(choice).start();
        } else {
            throw new MenuException("Lựa chọn không khả dụng!");
        }
    }

    @Override
    public void display() {
        
        this.displayMenu();
        
        boolean retry = false;
        
        do{
            
            retry = false;
            
            try {
                this.handle(AppScanner.scanIntWithMessage("Vui lòng nhập lựa chọn: "));
            } catch (MenuException ex) {
                System.out.println(ex.getMessage());
                retry = true;
            }
            
        }while( retry );

    }
    
    public boolean isChoiceValid(int choice){
        return this.getChoices().contains(choice);
    }
    
    @Override
    protected List<String> getMenuItemLines(){
        
        List<String> menuItemLines = new ArrayList<>();
        for (Map.Entry<Integer, MenuItem> entry : this.getMenuItems().entrySet()) {
            menuItemLines.add(String.format("%d. %s", entry.getKey(), entry.getValue().getTitle()));
        }
        
        return menuItemLines;
        
    }
    
    protected Set<Integer> getChoices() {
        return this.getMenuItems().keySet();
    }
    
    protected LinkedHashMap<Integer, MenuItem> getMenuItems() {
        if(menuItems == null){
            menuItems = this.registerMenuItems();
        }
        return menuItems;
    }

    protected void setMenuItems(LinkedHashMap<Integer, MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
    
    @Override
    public String getTitle() {
        return this.registerMenuTitle();
    }

}
