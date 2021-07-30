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

/**
 *
 * @author vanluong
 */
public class BrandListingPage extends Page {

    @Override
    public void displayContent() {
        try {
            BrandService brandService = new BrandServiceImpl();
            
            List<Brand> brands = brandService.findAll();
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
            
            String[] headers = {"ID", "Name", "Address"};
            
            TableUI theTable = new TableUI(headers, rows);
            theTable.display();
        } catch (SQLException ex) {
            Logger.getLogger(BrandListingPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Danh Sach Nhan Hang";
    }
    
}
