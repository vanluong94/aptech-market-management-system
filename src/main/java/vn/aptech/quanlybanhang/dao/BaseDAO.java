/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author anhnbt
 * @param <T>
 */
public interface BaseDAO<T> {
    boolean create(T object) throws SQLException;
    boolean update(T object) throws SQLException;
    boolean deleteById(int id) throws SQLException;
    T findById(int id) throws SQLException;
    List<T> findAll() throws SQLException;
    PaginatedResults<T> select(int page) throws SQLException;
}
