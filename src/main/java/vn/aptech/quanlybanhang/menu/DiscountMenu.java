/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.menu;

import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.pages.DiscountCreatePage;
import vn.aptech.quanlybanhang.pages.DiscountDetailPage;
import vn.aptech.quanlybanhang.pages.DiscountListingPage;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public class DiscountMenu extends Menu{
    
    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();
        
        menuItems.put(1, new DiscountListingPage());
        menuItems.put(2, new DiscountCreatePage());
        menuItems.put(3, new DiscountDetailPage());
        
        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());
        
        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return "Quan ly danh sach Giam gia";
    }

}
