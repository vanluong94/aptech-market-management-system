/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.ProductBestSalePage;
import vn.aptech.quanlybanhang.pages.ProductOutOfStockPage;
import vn.aptech.quanlybanhang.pages.StatisticSalesPage;

/**
 *
 * @author vanluong
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class AdminMenuStatistic extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();
        
        menuItems.put(1, new ProductOutOfStockPage());
        menuItems.put(2, new ProductBestSalePage());
        menuItems.put(3, new StatisticSalesPage());
        
        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());
        
        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return "Thống kê";
    }
    
}
