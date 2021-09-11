/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import vn.aptech.quanlybanhang.dao.OrderDAO;
import vn.aptech.quanlybanhang.dao.OrderDAOImpl;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.exception.InputInvalidException;
import vn.aptech.quanlybanhang.entities.OrderItem;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    public OrderServiceImpl() {
        this.orderDAO = new OrderDAOImpl();
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

}
