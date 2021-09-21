/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.service.OrderService;
import vn.aptech.quanlybanhang.service.OrderServiceImpl;
import vn.aptech.quanlybanhang.ui.OrderUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class OrderDetailPage extends Page {

    private final OrderService service;

    public OrderDetailPage() {
        this.service = new OrderServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            while(true) {
                int orderId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("order", "entity.scan.id.detail"));
                Order order = service.findById(orderId);
                if (order == null) {
                    I18n.printEntityMessage("order", "entity.error.idNotFound");
                    continue;
                } else {
                    order.setOrderItems(service.getOrderItems(order));
                    OrderUI orderUI = new OrderUI(order);
                    orderUI.display();
                }
                if (!AppScanner.confirm("\n" + I18n.getEntityMessage("order", "entity.confirm.findMore"))) {
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderDetailPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("order", "entity.title.detail");
    }

}
