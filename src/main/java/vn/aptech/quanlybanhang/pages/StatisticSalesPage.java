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
            System.out.println("Doanh số Hôm nay: " + StringCommon.convertDoubleToVND(todayAmount));
            double yesterday = productService.getStatisticAmount(DateCommon.addDay(today, -1), DateCommon.addDay(today, -1));
            System.out.println("Doanh số Hôm qua: " + StringCommon.convertDoubleToVND(yesterday));
            double last7days = productService.getStatisticAmount(DateCommon.addDay(today, -7), today);
            System.out.println("Doanh số 7 ngày gần đây: " + StringCommon.convertDoubleToVND(last7days));
            double last30days = productService.getStatisticAmount(DateCommon.addDay(today, -30), today);
            System.out.println("Doanh số 30 ngày gần đây: " + StringCommon.convertDoubleToVND(last30days));
            double thisMonth = productService.getStatisticAmount(beginMonth, endMonth);
            System.out.println("Doanh số Tháng này: " + StringCommon.convertDoubleToVND(thisMonth));
            double lastMonthAmount = productService.getStatisticAmount(DateCommon.getBeginMonth(lastMonth), DateCommon.getEndMonth(lastMonth));
            System.out.println("Doanh số Tháng trước: " + StringCommon.convertDoubleToVND(lastMonthAmount));
            String choice = AppScanner.scanStringWithMessage("Bạn có muốn xem báo cáo tùy chỉnh không? [y/N]: ");
            if (choice.equalsIgnoreCase("y")) {
                String fromDate = AppScanner.scanStringWithMessage("Nhập thời gian từ ngày muốn xem [dd/MM/yyyy]: ", true);
                String toDate = AppScanner.scanStringWithMessage("Đến ngày [dd/MM/yyyy]: ", true);
                double customAmount = productService.getStatisticAmount(DateCommon.convertStringToDateByPattern(fromDate, "dd/MM/yyyy"), DateCommon.convertStringToDateByPattern(toDate, "dd/MM/yyyy"));
                System.out.println("Doanh số từ ngày " + fromDate + " đến ngày " + toDate + ": " + StringCommon.convertDoubleToVND(customAmount));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatisticSalesPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(StatisticSalesPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Doanh số";
    }

}
