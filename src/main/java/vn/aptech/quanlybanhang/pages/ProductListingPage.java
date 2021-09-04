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
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;

public class ProductListingPage extends Page {

    @Override
    public void displayContent() {
        ProductService productService = new ProductServiceImpl();
        do {        
            try {
                List<Product> products = productService.findAll();
                if (products.isEmpty()) {
                    System.out.println("Danh sách trống");
                } else {
                    List<Object[]> rows = new ArrayList<Object[]>();
                    for (Product product : products) {
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
                    }
                    String[] headers = {"ID","Nhãn hàng","Loại sản phẩm","Nhà cung cấp","Nhân viên","Tên Sản phẩm","Giá","Số lượng trong kho"};
                    TableUI tableUI = new TableUI(headers, rows);
                    tableUI.display();
                    break;
                }
            } catch (SQLException e) {
                System.out.println("Exception when ProductMenu.handleSearch: " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(ProductListingPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    @Override
    public String getTitle() {
        return "Danh sách Sản phẩm";
    }

}
