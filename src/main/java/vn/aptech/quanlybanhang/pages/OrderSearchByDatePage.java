/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.DateCommon;
import vn.aptech.quanlybanhang.common.StringCommon;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.service.OrderService;
import vn.aptech.quanlybanhang.service.OrderServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

public class OrderSearchByDatePage extends Page {

    @Override
    public void displayContent() {

        try {

            OrderService orderService = new OrderServiceImpl();
            int page = 1;

            String choice = null;

            /**
             * repeat the whole searching process
             */
            do {

                I18n.print("order.msg.enterTimerange");

                /**
                 * enter time range
                 */
                Date dateFrom = null, dateTo = null;
                String format = "dd/MM/yyyy";
                do {
                    dateFrom = DateCommon.convertStringToDateByPattern(
                            AppScanner.scanStringWithMessage(I18n.getMessage("order.scan.datetime.start", format)),
                            format
                    );

                    if (dateFrom == null) {
                        I18n.getMessage("order.error.date.invalid");
                    }
                } while (dateFrom == null);
                
                do {
                    dateTo = DateCommon.convertStringToDateByPattern(
                            AppScanner.scanStringWithMessage(I18n.getMessage("order.scan.datetime.end", format)),
                            format
                    );

                    if (dateTo == null) {
                        I18n.getMessage("order.error.date.invalid");
                    }
                } while (dateTo == null);

                /**
                 * loop for pagination results page
                 */
                do {

                    PaginatedResults<Order> results = orderService.findByDateRange(dateFrom, dateTo, page);

                    if (results.getResults().isEmpty()) {
                        I18n.printEntityMessage("order", "entity.msg.emptyResults");
                        return;
                    }

                    // transfer data to table row
                    List<Object[]> rows = new ArrayList<>();
                    for (Order order : results.getResults()) {
                        Object[] row = {
                            order.getId(),
                            order.getEmployee().getName(),
                            StringCommon.safeNullObject(order.getCustomer().getName()), // order might not have customer
                            order.getDatetimeString(),
                            order.getAmountString()
                        };

                        rows.add(row);
                    }

                    String[] headers = {
                        "ID", 
                        I18n.getMessage("order.emp"), 
                        I18n.getMessage("order.customer"),
                        I18n.getMessage("entity.createdAt"),
                        I18n.getMessage("order.total")
                    };

                    TableUI theTable = new TableUI(headers, rows);
                    theTable.display(); //table

                    if (results.needsPagination()) {
                        results.displayPagination(); //pagination
                        results.displayPaginationMenu(); //pagination menu

                        page = results.scanGoPage();

                        System.out.println("\n\n");
                    } else {
                        page = 0;
                    }

                } while (page > 0);

                /**
                 * when we're out of loop, asking if user want to do another
                 * search
                 */
                choice = AppScanner.scanStringWithMessage(I18n.getMessage("order.confirm.searchAnotherTimerange"));
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }

            } while ("y".equalsIgnoreCase(choice));

        } catch (SQLException e) {
            System.out.println("Exception when OrderMenu.handleSearch: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(OrderSearchByDatePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getTitle() {
        return I18n.getMessage("order.title.searchByDate");
    }

    @Override
    public String getBreadcrumbPathName() {
        return I18n.getEntityMessage("order", "entity.title.search", true);
    }

}
