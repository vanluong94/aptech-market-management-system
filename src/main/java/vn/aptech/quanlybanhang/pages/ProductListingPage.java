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
import vn.aptech.quanlybanhang.utilities.I18n;

public class ProductListingPage extends Page {

    @Override
    public void displayContent() {
        ProductService productService = new ProductServiceImpl();
        do {        
            try {
                List<Product> products = productService.findAll();
                if (products.isEmpty()) {
                    I18n.print("product", "entity.msg.emptyResults");
                } else {
                    List<Object[]> rows = new ArrayList<>();
                    for (Product product : products) {
                        Object[] row = {
                            product.getId(),
                            product.getBrand().getName(),
                            product.getCategory().getName(),
                            product.getSupplier().getName(),
                            product.getEmployee().getName(),
                            product.getName(),
                            product.getPriceString(),
                            product.getQuantityInStock(),
                            product.getCreatedAt(),
                            product.getUpdatedAt()
                        };
                    
                        rows.add(row);
                    }
                    
                    String[] headers = {
                        "ID",
                        I18n.getMessage("brand.label.singular"),
                        I18n.getMessage("category.label.singular"),
                        I18n.getMessage("supplier.label.singular"),
                        I18n.getMessage("employee.label.singular"),
                        I18n.getMessage("product.label.singular"),
                        I18n.getMessage("product.price"),
                        I18n.getMessage("product.qty"),
                        I18n.getMessage("entity.createdAt"),
                        I18n.getMessage("entity.updatedAt")
                    };
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
        return I18n.getEntityMessage("product", "entity.title.all", true);
    }

}
