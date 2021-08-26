/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.common.ValidateCommon;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order findByCashier(int id) throws SQLException, Exception {
        if (id < 1) {
            throw new InputInvalidException("ID không hợp lệ!");
        }
        return this.orderDAO.findByCashier(id);
    }
    @Override
    public List<OrderItem> getOrderItems(Order order) {
        return this.orderDAO.getOrderItems(order);
    }

    @Override
    public List<Order> todayOrder() throws SQLException {
        return this.orderDAO.todayOrder();
    }

}
