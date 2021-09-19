/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.StringCommon;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.service.OrderService;
import vn.aptech.quanlybanhang.service.OrderServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public class OrderSearchByCustomerPage extends Page {

    private final OrderService orderService;
    
    public OrderSearchByCustomerPage(){
        this.orderService = new OrderServiceImpl();
    }
    
    @Override
    public void displayContent() {
        String choice = null;
        try {
            int page = 1;
            do {                
                String search = AppScanner.scanStringWithMessage("Nhập số điện thoại khách hàng : ");
                PaginatedResults<Order> orders = orderService.findByCustomerPhone(page, search);
                
                if (orders.getResults().isEmpty()) {
                    System.out.println("Không có đơn hàng nào từ khách hàng này");
                    return;
                }
                System.out.println(String.format("Các đơn hàng được tìm thấy theo số điện thoại : \"%s\" ", search));
                
                List<Object[]> rows = new ArrayList<Object[]>();
                for (Order order : orders.getResults()) {
                    Object[] row = {
                        order.getId(),
                        StringCommon.safeNullObject(order.getEmployee().getName()),
                        StringCommon.safeNullObject(order.getCustomer().getName()),
                        StringCommon.safeNullObject(order.getCustomer().getPhone()),
                        order.getDatetimeString(),
                        order.getAmountString()
                    };
                    rows.add(row);
                }
                
                String[] headers = {"ID", "Nhân viên", "Khách","SĐT", "Ngày tạo hóa đơn", "Tổng tiền hóa đơn"};

                TableUI theTable = new TableUI(headers, rows);
                theTable.display();
                
                if(orders.needsPagination()) {
                    orders.displayPagination();
                    orders.displayPaginationMenu();

                    page = orders.scanGoPage();

                    System.out.println("\n\n");
                } else {
                    page = 0; 
                }
                choice = AppScanner.scanStringWithMessage("Bạn có muốn tìm khách hàng khác không? [Y/N]");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            } while ("y".equalsIgnoreCase(choice) && page > 0);
 
        } catch (SQLException ex) {
            Logger.getLogger(OrderSearchByCustomerPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(OrderSearchByCustomerPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getMessage("order.title.searchByCustomer");
    }
    
    @Override
    public String getBreadcrumbPathName(){
        return I18n.getEntityMessage("order", "entity.title.search", true);
    }
    
}
