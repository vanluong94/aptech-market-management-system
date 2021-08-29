/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.entities.OrderItem;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public interface OrderDAO extends BaseDAO<Order> {

    Order findByCashierId(int id) throws SQLException;

    List<Order> todayOrder() throws SQLException;

    List<OrderItem> getOrderItems(Order order);
    
    PaginatedResults<Order> findByDateRange(Date fromDate, Date toDate, int page) throws SQLException;
}
