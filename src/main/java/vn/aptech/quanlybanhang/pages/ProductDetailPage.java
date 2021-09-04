/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;

public class ProductDetailPage extends Page {

    @Override
    public void displayContent() {
        ProductService productService = new ProductServiceImpl();
        String choice = null;
        do {          
            try {
                int productId = AppScanner.scanIntWithMessage("Nhập ID Sản phẩm bạn muốn xem : ");
                Product product = productService.findById(productId);
                if (product == null) {
                    System.out.println("Không tìm thấy ID Sản phẩm phù hợp!");
                } else {
                    List<Object[]> rows = new ArrayList<Object[]>();
                    Object[] row = {
                        product.getId(),
                        product.getBrand().getBrandName(),
                        product.getCategory().getCategoryName(),
                        product.getSupplier().getName(),
                        product.getEmployee().getName(),
                        product.getName(),
                        product.getPriceString(),
                        product.getQuantityInStock()
                    };
                    rows.add(row);
                    String[] headers = {"ID","Nhãn hàng","Loại sản phẩm","Nhà cung cấp","Nhân viên","Tên Sản phẩm","Giá","Số lượng trong kho"};
                    TableUI tableUI = new TableUI(headers,rows);
                    tableUI.display();
            
                    choice = AppScanner.scanStringWithMessage("Bạn có muốn tra cứu sản phẩm khác không? [Y/N] : ");
                    if (!"y".equalsIgnoreCase(choice)) {
                        break;
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(ProductDetailPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while ("y".equalsIgnoreCase(choice));
    }

    @Override
    public String getTitle() {
        return "Tim Sản phẩm trong kho theo ID Sản phẩm";
    }
    
}
