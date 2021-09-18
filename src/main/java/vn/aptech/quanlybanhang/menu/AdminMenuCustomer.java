/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.CustomerCreatePage;
import vn.aptech.quanlybanhang.pages.CustomerDeletePage;

import vn.aptech.quanlybanhang.pages.CustomerEditPage;
import vn.aptech.quanlybanhang.pages.CustomerListingPage;
import vn.aptech.quanlybanhang.pages.CustomerSearchByIDPage;
import vn.aptech.quanlybanhang.pages.CustomerSearchByPhonePage;
import vn.aptech.quanlybanhang.utilities.I18n;


/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public class AdminMenuCustomer extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();


        menuItems.put(1, new CustomerCreatePage());
        menuItems.put(2, new CustomerEditPage());
        menuItems.put(3, new CustomerSearchByIDPage());
        menuItems.put(4, new CustomerSearchByPhonePage());
        menuItems.put(5, new CustomerListingPage());
        menuItems.put(6, new CustomerDeletePage());

        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());
        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return I18n.getEntityMessage("customer", "entity.title.manage", true);
    }

}
