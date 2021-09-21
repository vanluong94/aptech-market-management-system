/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.CashierOrderDetailPage;
import vn.aptech.quanlybanhang.pages.CashierTodayOrdersPage;
import vn.aptech.quanlybanhang.utilities.I18n;


public class CashierMenuOrder extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();

        menuItems.put(1, new CashierTodayOrdersPage());
        menuItems.put(2, new CashierOrderDetailPage());

        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());

        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return I18n.getEntityMessage("order", "entity.title.manage", true);
    }
    
}
