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
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author vanluong
 */
public class BrandSearchPage extends Page {

    @Override
    public void displayContent() {
        
        BrandService brandService = new BrandServiceImpl();
        boolean retry = false;
        
        do{
            try {
                System.out.println("\n\n");
                String search = AppScanner.scanStringWithMessage("Tìm nhãn hàng cần sửa theo tên: ");
                System.out.println("\n\n");
                
                List<Brand> brands = brandService.searchByName(search);
                
                
                if (!brands.isEmpty()) {
                    
                    System.out.println( String.format( "Các nhãn hàng được tìm thấy dựa theo tên \"%s\"", search ) );
                    
                    List<Object[]> rows = new ArrayList<>();
                    
                    // transfer data to table row
                    for (Brand brand : brands) {
                        Object[] row = {
                            brand.getBrandId(),
                            brand.getBrandName(),
                            brand.getBrandAdd()
                        };
                        
                        rows.add(row);
                    }
                    
                    String[] headers = {"ID", "Tên Nhãn hàng", "Địa chỉ"};
                    
                    TableUI theTable = new TableUI(headers, rows);
                    theTable.display();
                    
                } else {
                    System.out.println("Không tìm thấy nhãn hàng nào.");
                    retry = true;
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(BrandSearchPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(retry);
    }

    @Override
    public String getTitle() {
        return "Tìm Nhãn hàng";
    }
    
}
