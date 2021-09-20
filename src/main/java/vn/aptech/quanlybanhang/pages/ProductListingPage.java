/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.I18n;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

public class ProductListingPage extends Page {

    @Override
    public void displayContent() {

        ProductService productService = new ProductServiceImpl();

        int page = 1;

        try {
            do {

                PaginatedResults<Product> results = productService.select(page);

                if (results.getResults().isEmpty()) {
                    I18n.print("product", "entity.msg.emptyResults");
                    return;
                }

                TableUI theTable = Product.toTable(results.getResults());
                theTable.display(); //table

                if (results.needsPagination()) {
                    results.displayPagination(); //pagination
                    results.displayPaginationMenu(); //pagination menu

                    page = results.scanGoPage();

                    System.out.println("\n\n");
                } else {
                    page = 0;
                }

            } while (page > 0);

        } catch (Exception ex) {
            Logger.getLogger(ProductListingPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("product", "entity.title.all", true);
    }

}
