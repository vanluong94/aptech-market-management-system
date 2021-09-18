/*
 *  Do an Java tai HaNoi Aptech
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

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ProductBestSalePage extends Page {

    private final ProductService productService;

    public ProductBestSalePage() {
        this.productService = new ProductServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            int page = 1;
            do {
                
                String format = "dd/MM/yyyy";
                String fromDate = AppScanner.scanStringWithMessage(I18n.getMessage("product.scan.date.from", format), true);
                String toDate = AppScanner.scanStringWithMessage(I18n.getMessage("product.scan.date.to", format), true);
                
                PaginatedResults<Product> results = productService.findAllByOrderByUnitsOnOrderDesc(page, fromDate, toDate);
                if (results.getResults().isEmpty()) {
                    I18n.printEntityMessage("product", "entity.msg.emptyResults");
                    return;
                }
                
                I18n.print("entity.msg.found", results.getTotalItems(), I18n.getMessage("product.label.singular"));

                List<Object[]> rows = new ArrayList<>();

                // transfer data to table row
                for (Product product : results.getResults()) {
                    Object[] row = {
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getQuantityInStock(),
                        product.getUnitsOnOrder(),
                        product.getCategory().getCategoryName(),
                        product.getBrand().getBrandName(),
                        product.getSupplier().getName()
                    };

                    rows.add(row);
                }

                String[] headers = {
                    "ID", 
                    I18n.getMessage("product.label.singular"),
                    I18n.getMessage("product.price"),
                    I18n.getMessage("product.qty"),
                    I18n.getMessage("product.soldQty"),
                    I18n.getMessage("category.label.singular"),
                    I18n.getMessage("category.label.singular"),
                    I18n.getMessage("brand.label.singular"),
                    I18n.getMessage("supplier.label.singular")
                };

                TableUI theTable = new TableUI(headers, rows);
                theTable.display();

                if (results.needsPagination()) {
                    results.displayPagination(); //pagination
                    results.displayPaginationMenu(); //pagination menu

                    page = results.scanGoPage();

                    System.out.println("\n\n");
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
        return I18n.getMessage("title.bestSaleProducts");
    }
}
