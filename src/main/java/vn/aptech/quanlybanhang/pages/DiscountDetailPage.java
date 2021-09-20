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
import vn.aptech.quanlybanhang.utilities.I18n;

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
                int discountId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("discount", "entity.scan.id.detail"));
                Discount discount = discountService.findById(discountId);
                if (discount == null) {
                    I18n.printEntityMessage("discount", "entity.error.idNotFound");
                } else {
                    this.displayDiscountDetail(discount);
                    if (!AppScanner.confirm(I18n.getMessage("entity.confirm.searchAnOther"))) {
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
        return I18n.getEntityMessage("discount", "entity.title.detail");
    }

    private void displayDiscountDetail(Discount discount) {
        
        System.out.printf("\n\n[ID: %d] %s\n", discount.getId(), discount.getName());

        List<ProductDiscount> dProducts = this.discountService.getDiscountProducts(discount);
        
        I18n.print("discount.msg.productList");
        if (dProducts.size() < 1) {
            I18n.print("discount.msg.productNotFound");
        } else {
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
            String[] headers = {
                "ID", 
                I18n.getMessage("product.label.singular"), 
                I18n.getMessage("product.price"), 
                I18n.getMessage("product.price.sale"), 
                I18n.getMessage("product.discount.percentage"), 
                I18n.getMessage("discount.datetime.start"), 
                I18n.getMessage("discount.datetime.end")
            };
            TableUI tableUI = new TableUI(headers, rows);
            tableUI.display();
        }

    }

}
