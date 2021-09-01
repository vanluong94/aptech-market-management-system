/*
 * Do an Java tai HaNoi Aptech
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
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public class CashierStatisticPage extends Page {

    private final OrderService orderService;

    public CashierStatisticPage() {
        this.orderService = new OrderServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            int page = 1;
            do {
                String fromDate = AppScanner.scanStringWithMessage("Nhập thời gian từ ngày muốn xem [dd/MM/yyyy]: ", true);
                String toDate = AppScanner.scanStringWithMessage("Đến ngày [dd/MM/yyyy]: ", true);

                PaginatedResults<Order> results = orderService.CashierStatistics(page, fromDate, toDate);
                if (results.getResults().isEmpty()) {
                    System.out.println("<Không có Đơn hàng nào>");
                    return;
                }

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
                TableUI tableUI = new TableUI(headers, rows);
                tableUI.display();

                if (results.needsPagination()) {
                    results.displayPagination();
                    results.displayPaginationMenu();
                    page = results.scanGoPage();
                    System.out.println("\n\n");
                } else {
                    page = 0;
                }

            } while (page > 0);
        } catch (SQLException ex) {
            Logger.getLogger(CashierStatisticPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CashierStatisticPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Thống kê";
    }

}
