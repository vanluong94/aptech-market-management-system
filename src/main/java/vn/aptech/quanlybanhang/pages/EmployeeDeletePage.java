/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Employee;
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
        
        while(true){
            
            try{
                
                int employeeId = AppScanner.scanIntWithMessage(I18n.getEntityMessage("employee", "entity.scan.id.delete"));
                Employee employee = employeeService.findById(employeeId);

                if (employee == null) {
                    I18n.printEntityMessage("employee", "entity.error.idNotFound");
                    continue;
                } 
                
                System.out.println("");
                I18n.print("entity.msg.foundName", I18n.getMessage("employee.label.singular"), employee.getName());
                System.out.println("");
                
                if (AppScanner.confirm(I18n.getEntityMessage("employee", "entity.confirm.delete"))) {
                    if (employeeService.empHasData(employee)){
                        I18n.print("employee.error.preventDelete");
                    } else if (employeeService.deleteById(employeeId)) {
                        I18n.printEntityMessage("employee", "entity.msg.deleted");
                    }else{
                        I18n.printEntityMessage("employee", "entity.error.deleteFailed");
                    }
                }
                
                System.out.println("");
                if (!AppScanner.confirm(I18n.getEntityMessage("employee", "entity.confirm.deleteAnother"))) {
                    System.out.println("");
                    break;
                }
            } catch (Exception ex) {
                Logger.getLogger(EmployeeDeletePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("employee", "entity.title.delete");
    }

}
