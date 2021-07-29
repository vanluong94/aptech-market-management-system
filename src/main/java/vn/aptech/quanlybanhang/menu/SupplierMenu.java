/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.menu;


import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.SupplierCreatePage;
import vn.aptech.quanlybanhang.pages.SupplierDeletePage;
import vn.aptech.quanlybanhang.pages.SupplierDetailPage;
import vn.aptech.quanlybanhang.pages.SupplierListingPage;

/**
 *
 * @author VuxxLong
 */
public class SupplierMenu extends Menu{

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();
        
        menuItems.put(1, new SupplierListingPage());
        menuItems.put(2, new SupplierCreatePage());
        menuItems.put(3, new SupplierDetailPage());
        menuItems.put(4, new SupplierDeletePage());
        
        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());
        
        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return "Quan Ly Nha Cung Cap";
    }
    
}
