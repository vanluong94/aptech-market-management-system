/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Customer;
import vn.aptech.quanlybanhang.service.CustomerService;
import vn.aptech.quanlybanhang.service.CustomerServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public class CustomerEditPage extends Page {

    @Override
    public void displayContent() {
        try {
            
            CustomerService customerService = new CustomerServiceImpl();
            
            int check = AppScanner.scanIntWithMessage(I18n.getEntityMessage("customer", "entity.scan.id.edit"));
            while (customerService.findById(check) == null) {
                I18n.printEntityMessage("customer", "entity.error.idNotFound");
                check = AppScanner.scanIntWithMessage(I18n.getEntityMessage("customer", "entity.scan.id.edit"));
            }
            
            String name = AppScanner.scanStringWithMessage(I18n.getMessage("customer.scan.newName"));
            String phone = AppScanner.scanStringWithMessage(I18n.getMessage("customer.scan.newPhone"));
            String add = AppScanner.scanStringWithMessage(I18n.getMessage("customer.scan.newAddr"));
            Customer customer = new Customer(check, name, phone, add);
            if (customerService.update(customer)) {
                I18n.printEntityMessage("customer", "entity.msg.updated");
            } else {
                I18n.printEntityMessage("customer", "entity.error.updateFailed");
            }

        } catch (Exception ex) {
            Logger.getLogger(CustomerEditPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("customer", "entity.title.edit");
    }
}
