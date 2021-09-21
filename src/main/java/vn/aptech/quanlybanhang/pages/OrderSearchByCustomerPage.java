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

    public OrderSearchByCustomerPage() {
        this.orderService = new OrderServiceImpl();
    }

    @Override
    public void displayContent() {
        String choice = null;
        try {
            
            while (true) {
                
                int page = 1;
                String search = AppScanner.scanStringWithMessage(I18n.getMessage("customer.scan.searchPhone"));

                do {

                    PaginatedResults<Order> orders = orderService.findByCustomerPhone(page, search);

                    if (orders.getResults().isEmpty()) {
                        I18n.printEntityMessage("order", "entity.msg.emptyResults");
                        continue;
                    }

                    I18n.print("entity.msg.foundBaseOn", I18n.getMessage("order.label.plural"), search);
                    List<Object[]> rows = new ArrayList<>();
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

                    String[] headers = {"ID",
                        I18n.getMessage("order.emp"),
                        I18n.getMessage("order.customer"),
                        I18n.getMessage("order.phone"),
                        I18n.getMessage("entity.createdAt"),
                        I18n.getMessage("order.total")};

                    TableUI theTable = new TableUI(headers, rows);
                    theTable.display();

                    if (orders.needsPagination()) {
                        orders.displayPagination();
                        orders.displayPaginationMenu();

                        page = orders.scanGoPage();

                        System.out.println("");
                    } else {
                        page = 0;
                    }

                } while (page > 0);

                if (!AppScanner.confirm(I18n.getMessage("entity.confirm.searchAnOther"))) {
                    break;
                }
            }

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
    public String getBreadcrumbPathName() {
        return I18n.getEntityMessage("order", "entity.title.search", true);
    }

}
