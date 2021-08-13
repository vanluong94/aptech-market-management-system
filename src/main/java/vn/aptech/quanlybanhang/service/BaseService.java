/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author anhnbt
 * @param <T>
 */
public interface BaseService<T> {
    boolean create(T object) throws SQLException, Exception;
    boolean update(T object) throws SQLException, Exception;
    boolean deleteById(int id) throws SQLException, Exception;
    T findById(int id) throws SQLException, Exception;
    List<T> findAll() throws SQLException;
    PaginatedResults<T> select(int page) throws SQLException, Exception;
}
