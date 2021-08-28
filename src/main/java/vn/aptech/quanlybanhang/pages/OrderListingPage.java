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
import vn.aptech.quanlybanhang.utilities.PaginatedResults;


/**
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class OrderListingPage extends Page {

    @Override
    public void displayContent() {
        try{
            int page = 1;

            do {

                OrderService orderService = new OrderServiceImpl();
                PaginatedResults<Order> results = orderService.select(page);
                
                if(results.getResults().isEmpty()) {
                    System.out.println("<Không tìm thấy Đơn hàng nào>");
                    return;
                }
                
                // transfer data to table row
                List<Object[]> rows = new ArrayList<>();
                for (Order order : results.getResults()) {
                    Object[] row = {
                        order.getId(),
                        order.getEmployee().getName(),
                        order.getCustomer().getName(),
                        order.getDatetimeString(),
                        order.getAmountString()
                    };

                    rows.add(row);
                }

                String[] headers = {"ID", "Nhân viên", "Khách", "Ngày tạo hóa đơn", "Tổng tiền hóa đơn"};

                TableUI theTable = new TableUI(headers, rows);
                theTable.display(); //table
                
                if(results.needsPagination()) {
                     results.displayPagination(); //pagination
                    results.displayPaginationMenu(); //pagination menu

                    page = results.scanGoPage();

                    System.out.println("\n\n");
                } else {
                    page = 0; 
               }
               
                
            } while (page > 0);
        } catch (SQLException ex) {
            Logger.getLogger(OrderListingPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OrderListingPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Danh sách Hóa đơn";
    }
    
}
