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

        String choice = null;

        try {
            int page = 1;
            do {
                int categoryId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("category", "entity.scan.id.detail"));
                PaginatedResults<Product> products = productService.findByCategoryId(page, categoryId);

                if (products.getResults().isEmpty()) {
                    I18n.printEntityMessage("product", "entity.msg.emptyResults");
                    return;
                }

                List<Object[]> rows = new ArrayList<>();
                for (Product product : products.getResults()) {
                    Object[] row = {
                        product.getId(),
                        product.getBrand().getName(),
                        product.getCategory().getName(),
                        product.getSupplier().getName(),
                        product.getEmployee().getName(),
                        product.getName(),
                        product.getPriceString(),
                        product.getQuantityInStock(),
                        product.getCreatedAtString(),
                        product.getUpdatedAtString()
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

                if (products.needsPagination()) {
                    products.displayPagination();
                    products.displayPaginationMenu();
                    page = products.scanGoPage();
                    System.out.println("\n\n");
                } else {
                    page = 0;
                }

                choice = AppScanner.scanStringWithMessage(I18n.getMessage("entity.confirm.searchAnOther"));
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }

            } while ("y".equalsIgnoreCase(choice));
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
