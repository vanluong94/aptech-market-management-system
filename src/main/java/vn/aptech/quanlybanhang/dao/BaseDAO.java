/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.util.List;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @param <T>
 */
public interface BaseDAO<T> {

    boolean create(T object) throws Exception;

    boolean update(T object) throws Exception;

    boolean deleteById(int id) throws Exception;

    T findById(int id) throws Exception;

    List<T> findAll() throws Exception;

    PaginatedResults<T> select(int page) throws Exception;
}
