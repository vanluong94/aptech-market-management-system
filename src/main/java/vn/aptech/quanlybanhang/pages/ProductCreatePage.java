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
            brand.setBrandId(AppScanner.scanIntWithMessage("Nhập Brand ID: "));

            Category category = new Category();
            category.setCategoryId(AppScanner.scanIntWithMessage("Nhập Category ID: "));

            String name = AppScanner.scanStringWithMessage("Nhập tên Sản phẩm: ");
            double price = AppScanner.scanDoubleWithMessage("Giá tiền: ");
            int qty = AppScanner.scanIntWithMessage("Số lượng :");

            Product product = new Product();
            product.setBrand(brand);
            product.setCategory(category);
            product.setEmployee(AuthServiceImpl.getCurrentEmployee());
            product.setName(name);
            product.setPrice(price);
            product.setQuantityInStock(qty);
            if (productService.create(product)) {
                System.out.println("Thêm Sản phẩm mới thành công!");
            } else {
                System.out.println("Đã xảy ra lỗi");
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductCreatePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Tạo Sản phẩm";
    }

}
