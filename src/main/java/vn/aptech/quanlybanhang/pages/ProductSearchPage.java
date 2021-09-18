/*
 * Do an Java tai HaNoi Aptech
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
 * @author vanluong
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public class ProductSearchPage extends Page {

    private final ProductService productService;

    public ProductSearchPage() {
        this.productService = new ProductServiceImpl();
    }

    @Override
    public void displayContent() {

        String choice = null;
        try {
            int page = 1;
            do {
                String search = AppScanner.scanStringWithMessage(I18n.getMessage("product.scan.searchName"));
                PaginatedResults<Product> products = productService.findByName(page, search);

                if (products.getResults().isEmpty()) {
                    I18n.print("product", "entity.msg.emptyResults");
                    return;
                }
                
                I18n.print("entity.msg.foundBaseOn", I18n.getMessage("product.label.singular"), search);
                
                List<Object[]> rows = new ArrayList<>();
                for (Product product : products.getResults()) {
                    Object[] row = {
                        product.getId(),
                        product.getBrand().getBrandName(),
                        product.getCategory().getCategoryName(),
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

                if (products.needsPagination()) {
                    products.displayPagination();
                    products.displayPaginationMenu();
                    page = products.scanGoPage();
                    System.out.println("\n\n");
                } else {
                    page = 0;
                }
                choice = AppScanner.scanStringWithMessage(I18n.getEntityMessage("product", "entity.confirm.searchAnOther"));
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            } while ("y".equalsIgnoreCase(choice) && page > 0);

        } catch (SQLException ex) {
            Logger.getLogger(ProductSearchPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductSearchPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("product", "entity.title.search", true);
    }

}
