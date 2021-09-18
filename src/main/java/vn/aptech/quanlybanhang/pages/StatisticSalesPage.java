/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.DateCommon;
import vn.aptech.quanlybanhang.common.StringCommon;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class StatisticSalesPage extends Page {

    private final ProductService productService;

    public StatisticSalesPage() {
        this.productService = new ProductServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            Date today = new Date();
            Date beginMonth = DateCommon.getBeginMonth(today);
            Date endMonth = DateCommon.getEndMonth(today);
            Date lastMonth = DateCommon.addMonth(today, -1);
            double todayAmount = productService.getStatisticAmount(today, today);
            System.out.println(I18n.getMessage("order.statistic.revenue.today") + ": " + StringCommon.convertDoubleToVND(todayAmount));
            double yesterday = productService.getStatisticAmount(DateCommon.addDay(today, -1), DateCommon.addDay(today, -1));
            System.out.println(I18n.getMessage("order.statistic.revenue.yesterday") + ": " + StringCommon.convertDoubleToVND(yesterday));
            double last7days = productService.getStatisticAmount(DateCommon.addDay(today, -7), today);
            System.out.println(I18n.getMessage("order.statistic.revenue.last7Days") + ": " + StringCommon.convertDoubleToVND(last7days));
            double last30days = productService.getStatisticAmount(DateCommon.addDay(today, -30), today);
            System.out.println(I18n.getMessage("order.statistic.revenue.last30Days") + ": " + StringCommon.convertDoubleToVND(last30days));
            double thisMonth = productService.getStatisticAmount(beginMonth, endMonth);
            System.out.println(I18n.getMessage("order.statistic.revenue.thisMonth") + ": " + StringCommon.convertDoubleToVND(thisMonth));
            double lastMonthAmount = productService.getStatisticAmount(DateCommon.getBeginMonth(lastMonth), DateCommon.getEndMonth(lastMonth));
            System.out.println(I18n.getMessage("order.statistic.revenue.lastMonth") + ": " + StringCommon.convertDoubleToVND(lastMonthAmount));
            String choice = AppScanner.scanStringWithMessage(I18n.getMessage("order.confirm.reportRevenueByTimerange"));
            if (choice.equalsIgnoreCase("y")) {
                String format = "dd/MM/yyyy";
                String fromDate = AppScanner.scanStringWithMessage(I18n.getMessage("order.scan.date.from", format), true);
                String toDate = AppScanner.scanStringWithMessage(I18n.getMessage("order.scan.date.to", format), true);
                double customAmount = productService.getStatisticAmount(DateCommon.convertStringToDateByPattern(fromDate, format), DateCommon.convertStringToDateByPattern(toDate, format));
                System.out.println(
                        I18n.getMessage("order.statistic.revenue.timeRange", fromDate, toDate)
                        + ": " + StringCommon.convertDoubleToVND(customAmount)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatisticSalesPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(StatisticSalesPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getMessage("order.title.revenue");
    }

}
