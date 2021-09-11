/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.SQLException;
import vn.aptech.quanlybanhang.entities.Customer;

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public interface CustomerDAO extends BaseDAO<Customer> {

    Customer findByPhone(String phone) throws SQLException;
}
