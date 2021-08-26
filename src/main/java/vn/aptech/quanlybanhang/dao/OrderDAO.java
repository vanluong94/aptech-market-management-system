/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.entities.OrderItem;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public interface OrderDAO extends BaseDAO<Order> {
    Order findByCashier(int id) throws SQLException;
    List<Order> todayOrder () throws SQLException;
    List<OrderItem> getOrderItems(Order order);
}
