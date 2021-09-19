/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import vn.aptech.quanlybanhang.entities.Employee;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public interface AuthDAO {

    Employee login(String username, String password);

    Employee findById(int id);
}
