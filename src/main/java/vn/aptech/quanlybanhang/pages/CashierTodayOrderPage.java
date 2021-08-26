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


public class CashierTodayOrderPage extends Page {

    @Override
    public void displayContent() {
        OrderService orderService = new OrderServiceImpl();
        String choice = null;
        do {            
            try {
                List<Order> orders = orderService.todayOrder();
                
                if (orders == null) {
                    System.out.println("Ngày hôm nay chưa có đơn hàng nào.");
                } else {
                    List<Object[]> rows = new ArrayList<Object[]>();
                    for (Order order : orders) {
                        Object[] row = {
                            order.getId(),
                            order.getEmployee().getEmployeeId(),
                            order.getCustomer().getId(),
                            order.getOrderDate(),
                            order.getAmount()
                        };
                        rows.add(row);
                    }
                    String[] headers = {"ID","Nhân viên","Khách","Ngày tạo hóa đơn","Tổng tiền hóa đơn"};
                    TableUI tableUI = new TableUI(headers,rows);
                    tableUI.display();
                    choice = AppScanner.scanStringWithMessage("Bạn có muốn tìm hóa đơn khác không? [Y/N] : ");
                    if (!"y".equalsIgnoreCase(choice)) {
                        break;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Exception when ProductMenu.handleSearch: "+e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(CashierTodayOrderPage.class.getName()).log(Level.SEVERE, null, ex);
            }   
        } while (true);
    }

    @Override
    public String getTitle() {
        return "Hóa đơn hôm nay";
    }
    
}
