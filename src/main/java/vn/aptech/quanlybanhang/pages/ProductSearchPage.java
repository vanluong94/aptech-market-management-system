/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
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

        
        try {
            
            while(true) {
                
                int page = 1;
                
                String search = AppScanner.scanStringWithMessage(I18n.getMessage("product.scan.searchName"));
                
                do {
                    
                    PaginatedResults<Product> products = productService.findByName(page, search);

                    if (products.getResults().isEmpty()) {
                        I18n.printEntityMessage("product", "entity.msg.emptyResults");
                        break;
                    }

                    I18n.print("entity.msg.foundBaseOn", I18n.getMessage("product.label.plural"), search);

                    Product.toTable(products.getResults()).display();

                    if (products.needsPagination()) {
                        products.displayPagination();
                        products.displayPaginationMenu();
                        page = products.scanGoPage();
                        System.out.println("");
                    } else {
                        page = 0;
                    }
                    
                } while (page > 0);
                
                if (!AppScanner.confirm(I18n.getMessage("entity.confirm.searchAnOther"))) {
                    break;
                }
            }
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
