/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Customer;

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public interface CustomerService extends BaseService<Customer> {

    Customer findByPhone(String phone) throws SQLException;
}
