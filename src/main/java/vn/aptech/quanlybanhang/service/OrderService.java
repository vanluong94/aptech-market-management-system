/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.entities.OrderItem;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public interface OrderService extends BaseService<Order> {

    Order findByCashierId(int id) throws SQLException, Exception;

    public List<Order> todayOrder() throws SQLException;

    List<OrderItem> getOrderItems(Order order);
    
    PaginatedResults<Order> findByDateRange(Date fromDate, Date toDate, int page) throws SQLException;
}
