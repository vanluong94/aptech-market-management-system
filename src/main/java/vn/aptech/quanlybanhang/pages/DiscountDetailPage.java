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
import vn.aptech.quanlybanhang.common.DateCommon;
import vn.aptech.quanlybanhang.constant.Constant;
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.entities.ProductDiscount;
import vn.aptech.quanlybanhang.service.DiscountService;
import vn.aptech.quanlybanhang.service.DiscountServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class DiscountDetailPage extends Page {

    DiscountService discountService;

    public DiscountDetailPage() {
        this.discountService = new DiscountServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            while (true) {
                System.out.println("\n");
                int discountId = AppScanner.scanIntWithMessage("Nhập ID chương trình giảm giá muốn kiểm tra: ");
                Discount discount = discountService.findById(discountId);
                if (discount == null) {
                    System.out.println("Không tìm thấy ID Chương trình giảm giá phù hợp!");
                } else {
                    this.displayDiscountDetail(discount);
                    if (!AppScanner.confirm("Bạn có muốn tìm chương trình giảm giá khác? [Y/N]: ")) {
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DiscountDetailPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Xem chi tiết Chương trình giảm giá";
    }

    private void displayDiscountDetail(Discount discount) {
        
        System.out.printf("\n\n[ID: %d] %s\n", discount.getId(), discount.getName());

        List<ProductDiscount> dProducts = this.discountService.getDiscountProducts(discount);
        if (dProducts.size() < 1) {
            System.out.println("[Products] Không tìm thấy sản phẩm nào");
        } else {
            System.out.println("[Products]");
            List<Object[]> rows = new ArrayList<>();

            for (ProductDiscount dProduct : dProducts) {
                Object[] row = {
                    dProduct.getProduct().getId(),
                    dProduct.getProduct().getName(),
                    dProduct.getProduct().getPriceString(),
                    dProduct.getProduct().getDiscountPriceString(),
                    dProduct.getDiscountPercentage() + "%",
                    DateCommon.convertDateToString(dProduct.getStartDate(), Constant.DATE_TIME_SIMPLE_FORMAT),
                    DateCommon.convertDateToString(dProduct.getEndDate(), Constant.DATE_TIME_SIMPLE_FORMAT)
                };
                rows.add(row);
                System.out.println(dProduct.getStartDate());
            }
            String[] headers = {"ID", "Tên Sản Phẩm", "Giá gốc", "Giá bán", "Giảm", "Ngày bắt đầu", "Ngày kết thúc"};
            TableUI tableUI = new TableUI(headers, rows);
            tableUI.display();
        }

    }

}
