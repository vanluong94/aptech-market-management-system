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
import vn.aptech.quanlybanhang.utilities.I18n;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

public class ProductCategoryPage extends Page {

    private final ProductService productService;

    public ProductCategoryPage() {
        this.productService = new ProductServiceImpl();
    }

    @Override
    public void displayContent() {


        try {
            while (true) {
                int page = 1;
                int categoryId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("category", "entity.scan.id.detail"));
                
                do {
                    
                    PaginatedResults<Product> products = productService.findByCategoryId(page, categoryId);

                    if (products.getResults().isEmpty()) {
                        I18n.printEntityMessage("product", "entity.msg.emptyResults");
                        break;
                    }

                    TableUI tableUI = Product.toTable(products.getResults());
                    tableUI.display();

                    if (products.needsPagination()) {
                        products.displayPagination();
                        products.displayPaginationMenu();
                        page = products.scanGoPage();
                        System.out.println("");
                    } else {
                        page = 0;
                    }

                }while(page > 0);

                if (!AppScanner.confirm(I18n.getMessage("entity.confirm.searchAnOther"))) {
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductCategoryPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getTitle() {
        return I18n.getMessage("title.productsByCategory");
    }

}
