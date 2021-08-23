/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.util.List;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.entities.OrderItem;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public interface OrderService extends BaseService<Order> {
    List<OrderItem> getOrderItems(Order order);
}
