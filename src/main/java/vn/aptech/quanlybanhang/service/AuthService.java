/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.service;

import vn.aptech.quanlybanhang.entities.Employee;

/**
 *
 * @author anhnbt
 */
public interface AuthService {
    Employee login(Employee emp);
}
