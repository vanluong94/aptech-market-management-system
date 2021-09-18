/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import jasper.JasperPrintService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.io.FileUtils;
import vn.aptech.quanlybanhang.common.CommonException;
import vn.aptech.quanlybanhang.common.Response;
import vn.aptech.quanlybanhang.constant.Constant;
import vn.aptech.quanlybanhang.dao.OrderDAO;
import vn.aptech.quanlybanhang.dao.OrderDAOImpl;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.exception.InputInvalidException;
import vn.aptech.quanlybanhang.entities.OrderItem;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    private final JasperPrintService jasperPrintService;

    public OrderServiceImpl() {
        this.orderDAO = new OrderDAOImpl();
        this.jasperPrintService = new JasperPrintService();
    }

    @Override
    public boolean create(Order object) throws SQLException, Exception {
        if (object == null) {
            throw new Exception("Doi tuong khong duoc de trong");
        }
        return orderDAO.create(object);
    }

    @Override
    public boolean update(Order object) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteById(int id) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order findById(int id) throws SQLException, Exception {
        return this.orderDAO.findById(id);
    }

    @Override
    public List<Order> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PaginatedResults<Order> select(int page) throws SQLException, Exception {
        return this.orderDAO.select(page);
    }

    @Override
    public List<OrderItem> getOrderItems(Order order) {
        return this.orderDAO.getOrderItems(order);
    }

    @Override
    public Order findByCashierId(int id) throws SQLException, Exception {
        if (id < 1) {
            throw new InputInvalidException("ID không hợp lệ!");
        }
        return this.orderDAO.findByCashierId(id);
    }

    @Override
    public PaginatedResults<Order> CashierStatistics(int page, String fromDate, String toDate) throws SQLException {
        return orderDAO.CashierStatistics(page, fromDate, toDate);
    }

    @Override
    public PaginatedResults<Order> todayOrder(int page) throws SQLException {
        return this.orderDAO.todayOrder(page);
    }

    @Override
    public Order findByCustomerId(int id) throws SQLException {
        return this.orderDAO.findByCustomerId(id);
    }

    @Override
    public PaginatedResults<Order> findByDateRange(Date fromDate, Date toDate, int page) throws SQLException {
        return this.orderDAO.findByDateRange(fromDate, toDate, page);
    }

    @Override
    public String requestReportXlsx(Order order) throws Exception {
        String file = null;
        JasperPrint jp;
        try {
            vn.aptech.quanlybanhang.utilities.FileUtils fileUtils = new vn.aptech.quanlybanhang.utilities.FileUtils();
            File invoice = fileUtils.getFileFromResource(Constant.INVOICE_TEMPLATE_FILE);
            File bill = fileUtils.getFileFromResource(Constant.INVOICE_EXPORT_FILE);
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(order.getOrderItems());
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("ItemDataSource", itemsJRBean);
            parameters.put("cashier", AuthServiceImpl.getCurrentEmployee().getName());
            parameters.put("customerName", order.getCustomer().getName());
            parameters.put("customerAddress", order.getCustomer().getAddress());
            parameters.put("customerPhone", order.getCustomer().getPhone());
            parameters.put("invoiceNumber", order.getId());
            
            InputStream input = new FileInputStream(invoice);
            JasperDesign jasperDesign = JRXmlLoader.load(input);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jp = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            FileUtils.writeByteArrayToFile(bill, jasperPrintService.exportReportXlsx(jp));
            file = bill.getAbsolutePath();
        } catch (FileNotFoundException e) {
            throw new CommonException(Response.SYSTEM_ERROR, "FileNotFoundException khi requestReportXlsx thực hiện tạo JasperPrint. Chi tiết: " + e.getMessage());
        }
        return file;
    }
}
