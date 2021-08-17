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
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.service.BrandService;
import vn.aptech.quanlybanhang.service.BrandServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author vanluong
 */
public class BrandListingPage extends Page {

    @Override
    public void displayContent() {
        try {

            int page = 1;

            do {

                BrandService brandService = new BrandServiceImpl();
                PaginatedResults<Brand> results = brandService.select(page);
                
                if(results.getResults().isEmpty()) {
                    System.out.println("<Không tìm thấy Nhãn hàng nào>");
                    return;
                }
                
                // transfer data to table row
                List<Object[]> rows = new ArrayList<>();
                for (Brand brand : results.getResults()) {
                    Object[] row = {
                        brand.getBrandId(),
                        brand.getBrandName(),
                        brand.getBrandAdd()
                    };

                    rows.add(row);
                }

                String[] headers = {"ID", "Tên Nhãn hàng", "Địa chỉ"};

                TableUI theTable = new TableUI(headers, rows);
                theTable.display(); //table
                
                if(results.needsPagination()) {
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
        return "Danh Sach Nhan Hang";
    }

}
