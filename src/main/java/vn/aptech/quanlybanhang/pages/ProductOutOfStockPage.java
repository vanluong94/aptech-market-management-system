/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.I18n;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 * 
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class ProductOutOfStockPage extends Page {

    @Override
    public void displayContent() {
        try {

            int page = 1;
            ProductService productService = new ProductServiceImpl();

            do {
                PaginatedResults<Product> results = productService.selectOutOfStock(page);
                
                if(results.getResults().isEmpty()) {
                    I18n.printEntityMessage("product", "entity.msg.emptyResults");
                    return;
                }
                
                TableUI theTable = Product.toTable(results.getResults());
                theTable.display(); //table
                
                if(results.needsPagination()) {
                     results.displayPagination(); //pagination
                    results.displayPaginationMenu(); //pagination menu

                    page = results.scanGoPage();

                    System.out.println("");
                } else {
                    page = 0;
                }
            } while (page > 0);

        } catch (SQLException ex) {
            Logger.getLogger(BrandListingPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BrandListingPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getMessage("title.productsOutOfStock");
    }
    
}
