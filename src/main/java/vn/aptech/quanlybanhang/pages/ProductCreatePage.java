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
import vn.aptech.quanlybanhang.utilities.I18n;

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
            brand.setBrandId(AppScanner.scanIntWithi18Message("product.scan.brand"));

            Category category = new Category();
            category.setCategoryId(AppScanner.scanIntWithi18Message("product.scan.category"));

            String name = AppScanner.scanStringWithi18Message("product.scan.name");
            double price = AppScanner.scanDoubleWithMessage(I18n.getMessage("product.scan.price"));
            int qty = AppScanner.scanIntWithi18Message("product.scan.qty");

            Product product = new Product();
            product.setBrand(brand);
            product.setCategory(category);
            product.setEmployee(AuthServiceImpl.getCurrentEmployee());
            product.setName(name);
            product.setPrice(price);
            product.setQuantityInStock(qty);
            if (productService.create(product)) {
                I18n.printEntityMessage("product", "entity.msg.created");
            } else {
                I18n.printEntityMessage("product", "entity.msg.createFailed");
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductCreatePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("product", "entity.title.create");
    }

}
