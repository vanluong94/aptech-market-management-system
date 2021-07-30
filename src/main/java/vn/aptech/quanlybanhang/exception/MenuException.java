/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.exception;

/**
 *
 * @author vanluong
 */
public class MenuException extends Exception {
    public MenuException(String message, Throwable cause){
        super(message, cause);
    }

    public MenuException(String message) {
        super(message);
    }
}
