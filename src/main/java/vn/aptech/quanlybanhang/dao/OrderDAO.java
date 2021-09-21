/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

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

    Order findByCashierId(int id) throws Exception;

    PaginatedResults<Order> todayOrder(int page) throws Exception;

    List<OrderItem> getOrderItems(Order order) throws Exception;

    PaginatedResults<Order> CashierStatistics(int page, Date fromDate, Date toDate) throws Exception;

    PaginatedResults<Order> findByCustomerPhone(int page, String phone) throws Exception;

    Order findByCustomerId(int id) throws Exception;

    PaginatedResults<Order> findByDateRange(Date fromDate, Date toDate, int page) throws Exception;
}
