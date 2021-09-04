/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.service.DiscountService;
import vn.aptech.quanlybanhang.service.DiscountServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author vanluong
 */
public class DiscountDetailPage extends Page {

    @Override
    public void displayContent() {
        DiscountService discountService = new DiscountServiceImpl();
        String choice = null;
        do {            
            try {
                int discountId = AppScanner.scanIntWithMessage("Nhập ID chương trình giảm giá muốn kiểm tra :");
                Discount discount = discountService.findById(discountId);
                if (discount == null) {
                    System.out.println("Không tìm thấy ID Chương trình giảm giá phù hợp!");
                } else {
                    List<Object[]> rows = new ArrayList<Object[]>();
                    Object[] row = {
                        discount.getId(),
                        discount.getName()
                    };
                    rows.add(row);
                    String[] headers = {"ID","Tên Danh mục"};
                    TableUI tableUI = new TableUI(headers,rows);
                    tableUI.display();
                    choice = AppScanner.scanStringWithMessage("Bạn có muốn tìm Danh mục khác không? [Y/N] : ");
                    if (!"y".equalsIgnoreCase(choice)) {
                        break;
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(DiscountDetailPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while ("y".equalsIgnoreCase(choice));
    }

    @Override
    public String getTitle() {
        return "Xem chi tiết Chương trình giảm giá";
    }
    
}
