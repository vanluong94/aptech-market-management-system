/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import vn.aptech.quanlybanhang.service.EmployeeService;
import vn.aptech.quanlybanhang.service.EmployeeServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

public class EmployeeDeletePage extends Page {

    private final EmployeeService employeeService;

    public EmployeeDeletePage() {
        this.employeeService = new EmployeeServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            int employeeId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("employee", "entity.scan.id.delete"));
            if (AppScanner.confirm(I18n.getMessage("entity.confirm.delete", I18n.getMessage("employee.label.singular")))) {
                if (employeeService.deleteById(employeeId)) {
                    I18n.printEntityMessage("employee", "entity.msg.deleted");
                } else {
                    I18n.printEntityMessage("employee", "entity.error.deleteFailed");
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("employee", "entity.title.delete");
    }

}
