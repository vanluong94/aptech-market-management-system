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


public class CashierOrderDetailPage extends Page {

    @Override
    public void displayContent() {
        OrderService orderService = new OrderServiceImpl();
        String choice = null;
        do{
            try {
                int orderId = AppScanner.scanIntWithMessage("Nhập ID hóa đơn muốn kiểm tra : ");
                Order order = orderService.findByCashier(orderId);
                if (order == null ) {
                    System.out.println("ID hóa đơn không phù hợp");
                } else {
                    List<Object[]> rows = new ArrayList<Object[]>();
                    Object[] row = {
                      order.getId(),
                      order.getEmployee().getEmployeeId(),
                      order.getCustomer().getId(),
                      order.getOrderDate(),
                      order.getAmount()
                    };
                    rows.add(row);
                String[] headers = {"ID","Nhân viên","Khách","Ngày tạo hóa đơn","Tổng tiền hóa đơn"};
                TableUI tableUI = new TableUI(headers,rows);
                tableUI.display();
                }
                choice = AppScanner.scanStringWithMessage("Bạn có muốn tìm hóa đơn khác không? [Y/N] : ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            } catch (SQLException e) {
                System.out.println("Exception when ProductMenu.handleSearch: "+e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(CashierOrderDetailPage.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }while("y".equalsIgnoreCase(choice));
    }
    @Override
    public String getTitle() {
        return "Tìm Hóa đơn theo ID";
    }
    
    @Override
    public String getBreadcrumbPathName(){
        return "Tìm kiếm ";
    }
}
