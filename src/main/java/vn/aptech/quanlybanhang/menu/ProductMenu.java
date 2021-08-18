/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.ProductCategoryPage;
import vn.aptech.quanlybanhang.pages.ProductCreatePage;
import vn.aptech.quanlybanhang.pages.ProductDeletePage;
import vn.aptech.quanlybanhang.pages.ProductDetailPage;
import vn.aptech.quanlybanhang.pages.ProductEditPage;
import vn.aptech.quanlybanhang.pages.ProductListingPage;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ProductMenu extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();

        menuItems.put(1, new ProductListingPage());
        menuItems.put(2, new ProductCreatePage());
        menuItems.put(3, new ProductDetailPage());
        menuItems.put(4, new ProductCategoryPage());
        menuItems.put(5, new ProductDeletePage());
        menuItems.put(6, new ProductEditPage());

        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());

        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return "Quản lý danh sach Sản phẩm";
    }

}
