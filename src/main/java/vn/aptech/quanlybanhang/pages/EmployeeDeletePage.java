/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;


public class EmployeeDeletePage extends Page {

    @Override
    public void displayContent() {
        try {
            EmployeeService employeeService = new EmployeeServiceImpl();
            
            int employeeId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("employee", "entity.scan.id.delete"));
            if (employeeService.deleteById(employeeId)) {
                I18n.printEntityMessage("employee", "entity.msg.deleted");
            } else {
                I18n.printEntityMessage("employee", "entity.error.deleteFailed");
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeDeletePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("employee", "entity.title.delete");
    }
    
}
