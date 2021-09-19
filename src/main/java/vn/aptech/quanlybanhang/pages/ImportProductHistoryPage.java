/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.constant.Constant;
import vn.aptech.quanlybanhang.entities.ImportProduct;
import vn.aptech.quanlybanhang.service.ImportProductService;
import vn.aptech.quanlybanhang.service.ImportProductServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ImportProductHistoryPage extends Page {

    private final ImportProductService importProductService;

    public ImportProductHistoryPage() {
        this.importProductService = new ImportProductServiceImpl();
    }
    
    @Override
    public void displayContent() {
        try {
            
            I18n.getMessage("product.import.msg.recently");
            
            List<ImportProduct> importProducts = importProductService.findAll();
            if (importProducts.isEmpty()) {
                I18n.printEntityMessage("product", "entity.msg.emptyResults");
            } else {
                DateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
                List<Object[]> rows = new ArrayList<>();
                
                // transfer data to table row
                for (ImportProduct importProduct : importProducts) {
                    Object[] row = {
                        importProduct.getId(),
                        importProduct.getPrice(),
                        importProduct.getQuantity(),
                        importProduct.getSupplier().getName(),
                        dateFormat.format(importProduct.getCreatedAt()),
                    };

                    rows.add(row);
                }

                String[] headers = {
                    "ID", 
                    I18n.getMessage("product.price"), 
                    I18n.getMessage("product.qty"), 
                    I18n.getMessage("supplier.label.singular"), 
                    I18n.getMessage("product.import.datetime")
                };

                TableUI theTable = new TableUI(headers, rows);
                theTable.display();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportProductHistoryPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ImportProductHistoryPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String getTitle() {
        return I18n.getMessage("product.import.title.history");
    }
    
}
