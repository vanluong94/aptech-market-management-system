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
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.service.DiscountService;
import vn.aptech.quanlybanhang.service.DiscountServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;

/**
 *
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class DiscountListingPage extends Page {

    @Override
    public void displayContent() {
        DiscountService discountService = new DiscountServiceImpl();
        do {            
            try {
                List<Discount> discounts = discountService.findAll();
                if (discounts.isEmpty()) {
                    System.out.println("Danh sách trống");
                } else {
                    List<Object[]> rows = new ArrayList<Object[]>();
                    for (Discount discount : discounts) {
                        Object[] row = {
                            discount.getId(),
                            discount.getName()
                        };
                        rows.add(row);
                    }
                    String[] headers = {"ID","Tên Danh mục"};
                    TableUI tableUI = new TableUI(headers,rows);
                    tableUI.display();
                    break;
                }
            }catch (SQLException e) {
                System.out.println("Exception when ProductMenu.handleSearch: " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(DiscountListingPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
        
    }

    @Override
    public String getTitle() {
        return "Danh sách Chương trình giảm giá";
    }

}
