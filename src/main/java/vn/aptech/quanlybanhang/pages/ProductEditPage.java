/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.menu.ProductMenu;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;


public class ProductEditPage extends Page {

    @Override
    public void displayContent() {
        
        ProductService productService = new ProductServiceImpl();
        
        ProductSearchPage searchPage = new ProductSearchPage();
        searchPage.displayContent();
        
        String choice = null;
        do {
            try {
                int id = AppScanner.scanIntWithMessage("Nhập ID sản phẩm cần sửa: ");
                Product product = productService.findById(id);
                if (product == null) {
                    System.out.println("ID không tồn tại!");
                } else {
                    System.out.println("Các trư�?ng đánh dấu * bắt buộc nhập. Nhấn <enter> để giữ lại thông tin cũ.");
                    String name = AppScanner.scanStringWithMessage("Tên sản phẩm: ");
                    double price = AppScanner.scanDoubleWithMessage("Giá: ");
                    int qty = AppScanner.scanIntWithMessage("Số lượng: ");
                    if (name.length() > 0) {
                        product.setName(name);
                    }
                    if (price > 0) {
                        product.setPrice(price);
                    }
                    if (qty > 0) {
                        product.setQuantityInStock(qty);
                    }
                    if (productService.update(product)) {
                        System.out.println("Cập nhật thông tin sản phẩm thành công!");
                    } else {
                        System.out.println("Không cập nhật được. Vui lòng thử lại.");
                    }
                }
                choice = AppScanner.scanStringWithMessage("Bạn có muốn nhập lại không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            } catch (Exception ex) {
                Logger.getLogger(ProductMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while ("y".equalsIgnoreCase(choice));
    }

    @Override
    public String getTitle() {
        return "Sửa Sản phẩm";
    }
    
}
