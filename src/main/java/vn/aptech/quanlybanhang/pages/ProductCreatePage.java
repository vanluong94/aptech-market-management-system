/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ProductCreatePage extends Page {

    private final ProductService productService;

    public ProductCreatePage() {
        this.productService = new ProductServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            Brand brand = new Brand();
            brand.setBrandId(AppScanner.scanIntWithMessage("Nhap Brand ID : "));

            Category category = new Category();
            category.setCategoryId(AppScanner.scanIntWithMessage("Nhap Category ID :"));

            String name = AppScanner.scanStringWithMessage("Nhap ten San Pham :");
            double price = AppScanner.scanDoubleWithMessage("Gia tien :");
            int qty = AppScanner.scanIntWithMessage("So luong :");

            Product product = new Product();
            product.setBrand(brand);
            product.setCategory(category);
            product.setEmployee(AuthServiceImpl.getCurrentEmployee());
            product.setName(name);
            product.setPrice(price);
            product.setQuantityInStock(qty);
            if (productService.create(product)) {
                System.out.println("Them San Pham moi thanh cong!");
            } else {
                System.out.println("Da xay ra loi");
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductCreatePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Tao San Pham";
    }

}
