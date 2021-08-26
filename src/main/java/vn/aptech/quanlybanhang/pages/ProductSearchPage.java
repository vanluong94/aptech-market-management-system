/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vn.aptech.quanlybanhang.common.MessageCommon;
import vn.aptech.quanlybanhang.common.MessageContent;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author vanluong
 */
public class ProductSearchPage extends Page {

    @Override
    public void displayContent() {
        
        ProductService productService = new ProductServiceImpl();
        
        String choice = null;
        do {
            try {
                String search = AppScanner.scanStringWithMessage("Tìm kiếm sản phẩm cần tìm theo tên: ");
                List<Product> products = productService.findByName(search);
                if (products.isEmpty()) {
                    System.out.println(MessageCommon.getMessage(MessageContent.OBJECT_NOT_FOUND, search));
                } else {
                    System.out.println(String.format("Các Sản phẩm được tìm thấy theo tên: \"%s\"", search));
                    List<Object[]> rows = new ArrayList<Object[]>();
                    for (Product product : products) {
                        Object[] row = {
                            product.getId(),
                            product.getName(),
                            product.getPriceString(),
                            product.getQuantityInStock()
                        };
                        rows.add(row);
                    }
                    String[] headers = {"ID", "Tên SP", "Giá", "Tồn kho"};
                    TableUI tableUI = new TableUI(headers, rows);
                    tableUI.display();
                }
                choice = AppScanner.scanStringWithMessage("Bạn có muốn tìm Sản phẩm khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            } catch (SQLException e) {
                System.out.println("Exception when ProductMenu.handleSearch: " + e.getMessage());
            }
        } while ("y".equalsIgnoreCase(choice));
    }

    @Override
    public String getTitle() {
        return "Tìm kiếm Sản phẩm";
    }
    
}
