/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.dao;

import java.sql.SQLException;

/**
 *
 * @author anhnbt
 */
public interface BaseDAO<T> {
    boolean saveOrUpdate(T object) throws SQLException;
    boolean deleteById(int id) throws SQLException;
    T findById(int id) throws SQLException;
}
