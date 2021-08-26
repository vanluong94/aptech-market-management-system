/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.entities.OrderItem;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public interface OrderService extends BaseService<Order> {
    Order findByCashier(int id) throws SQLException, Exception;
    public List<Order> todayOrder() throws SQLException;
    List<OrderItem> getOrderItems(Order order);
}
