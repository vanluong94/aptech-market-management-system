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
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.service.SupplierService;
import vn.aptech.quanlybanhang.service.SupplierServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;


public class SupplierListingPage extends Page {

    @Override
    public void displayContent() {
        SupplierService supplierService = new SupplierServiceImpl();
        do {            
            try {
                List<Supplier> suppliers = supplierService.findAll();
                
                if (suppliers.isEmpty()) {
                    System.out.println("Không tìm thấy Nhà cung cấp nào trong dữ liệu.");
                    
                } else {
                    System.out.println("Các Nhà cung cấp được tìm thấy dựa theo tên : ");
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
                    break;
                }
            } catch (SQLException e) {
                System.out.println("Exception when ProductMenu.handleSearch: " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(SupplierListingPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    @Override
    public String getTitle() {
        return "Danh Sach Nha Cung Cap";
    }
    
}
