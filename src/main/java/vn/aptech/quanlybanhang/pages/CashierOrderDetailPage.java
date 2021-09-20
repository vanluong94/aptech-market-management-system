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
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.service.OrderService;
import vn.aptech.quanlybanhang.service.OrderServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

public class CashierOrderDetailPage extends Page {

    @Override
    public void displayContent() {
        OrderService orderService = new OrderServiceImpl();
        String choice = null;
        do {
            try {
                int orderId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("order", "entity.scan.id.detail"));
                Order order = orderService.findByCashierId(orderId);
                if (order == null) {
                    I18n.printEntityMessage("order", "entity.error.idNotFound");
                } else {
                    List<Object[]> rows = new ArrayList<>();
                    Object[] row = {
                        order.getId(),
                        order.getEmployee().getName(),
                        order.getCustomer().getName(),
                        order.getDatetimeString(),
                        order.getAmountString()
                    };
                    rows.add(row);
                    String[] headers = {
                        "ID", 
                        I18n.getMessage("order.emp"), 
                        I18n.getMessage("order.customer"), 
                        I18n.getMessage("entity.createdAt"),
                        I18n.getMessage("order.total")
                    };
                    TableUI tableUI = new TableUI(headers, rows);
                    tableUI.display();
                }

                choice = AppScanner.scanStringWithMessage(I18n.getMessage("entity.confirm.searchAnOther"));
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            } catch (SQLException e) {
                System.out.println("Exception when ProductMenu.handleSearch: " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(CashierOrderDetailPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while ("y".equalsIgnoreCase(choice));
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("order", "entity.title.findById");
    }

}
