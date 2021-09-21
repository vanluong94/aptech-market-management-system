/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.validator.GenericValidator;
import vn.aptech.quanlybanhang.common.DateCommon;
import vn.aptech.quanlybanhang.common.StringCommon;
import vn.aptech.quanlybanhang.constant.Constant;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.service.OrderService;
import vn.aptech.quanlybanhang.service.OrderServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;
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
                /**
                 * enter time range
                 */
                Date fromDate = null;
                Date toDate = null;
                Date toDay = DateCommon.getToday();
                do {
                    String fromDateStr = AppScanner.scanStringWithMessage(I18n.getMessage("order.scan.date.from", Constant.DATE_FORMAT), true);
                    fromDate = DateCommon.convertStringToDateByPattern(fromDateStr, Constant.DATE_FORMAT);
                    if (!GenericValidator.isDate(fromDateStr, Constant.DATE_FORMAT, true)) {
                        fromDate = null;
                        I18n.print("order.error.date.invalid");
                    } else if (DateCommon.getBeginDay(toDay).before(DateCommon.getBeginDay(fromDate))) {
                        fromDate = null;
                        I18n.print("entity.error.date.compareCurrentDate", I18n.getMessage("startDate"));
                    }
                } while (fromDate == null);
                
                do {
                    String toDateStr = AppScanner.scanStringWithMessage(I18n.getMessage("order.scan.datetime.end", Constant.DATE_FORMAT));
                    toDate = DateCommon.convertStringToDateByPattern(toDateStr, Constant.DATE_FORMAT);
                    if (!GenericValidator.isDate(toDateStr, Constant.DATE_FORMAT, true)) {
                        toDate = null;
                        I18n.print("order.error.date.invalid");
                    } else if (DateCommon.getBeginDay(toDay).before(DateCommon.getBeginDay(toDate))) {
                        toDate = null;
                        I18n.print("entity.error.date.compareCurrentDate", I18n.getMessage("endDate"));
                    } else if (DateCommon.getBeginDay(toDate).before(DateCommon.getBeginDay(fromDate))) {
                        toDate = null;
                        I18n.print("entity.error.date.compareStartDate");
                    }
                } while (toDate == null);
                
                PaginatedResults<Order> results = orderService.CashierStatistics(page, fromDate, toDate);
                if (results.getResults().isEmpty()) {
                    I18n.printEntityMessage("order", "entity.msg.emptyResults");
                    return;
                }
                
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
                TableUI tableUI = new TableUI(headers, rows);
                tableUI.display();
                
                if (results.needsPagination()) {
                    results.displayPagination();
                    results.displayPaginationMenu();
                    page = results.scanGoPage();
                    System.out.println("");
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
        return I18n.getMessage("title.statistic");
    }
    
}
