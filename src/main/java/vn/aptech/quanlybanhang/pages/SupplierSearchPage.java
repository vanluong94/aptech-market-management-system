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
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.service.SupplierService;
import vn.aptech.quanlybanhang.service.SupplierServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;


public class SupplierSearchPage extends Page {

    @Override
    public void displayContent() {
        SupplierService supplierService = new SupplierServiceImpl();
            try {
                String search = AppScanner.scanStringWithMessage("Tên Nhà cung cấp cần tìm : ");
                
                List<Supplier> suppliers = supplierService.searchByName(search);
                
                if (suppliers.isEmpty()) {
                    System.out.println("Không tìm thấy Nhà cung cấp nao cung tên");
                    
                } else {
                    System.out.println("Các Nhà cung cấp được tìm thấy dựa theo tên : ");
//                    for(Supplier supplier : suppliers){
//                        System.out.println(supplier.toString());
//                    }
                    List<Object[]> rows = new ArrayList<>();
                    for (Supplier supplier : suppliers) {
                        Object[] row = {
                            supplier.getId(),
                            supplier.getName(),
                            supplier.getAddress()
                        };
                        
                        rows.add(row);
                    }
                    
                    String[] headers = {"ID", "Tên NCC", "Địa chỉ"};
                    
                    TableUI theTable = new TableUI(headers, rows);
                    theTable.display();
                }
            } catch (SQLException e) {
                Logger.getLogger(SupplierSearchPage.class.getName()).log(Level.SEVERE, null, e);
            }catch (ClassNotFoundException e){
                Logger.getLogger(SupplierSearchPage.class.getName()).log(Level.SEVERE, null, e);
            }
    }

    @Override
    public String getTitle() {
        return "Tìm kiếm Nhà cung cấp";
    }
    
}
