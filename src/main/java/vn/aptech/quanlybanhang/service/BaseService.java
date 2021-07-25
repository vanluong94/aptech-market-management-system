/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.service;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author anhnbt
 * @param <T>
 */
public interface BaseService<T> {
    boolean saveOrUpdate(T object) throws SQLException, Exception;
    boolean deleteById(int id) throws SQLException;
    boolean insert(T object) throws SQLException;
    boolean update(T object) throws SQLException;
    T findById(int id) throws SQLException, Exception;
    List<T> findAll() throws SQLException;
}
