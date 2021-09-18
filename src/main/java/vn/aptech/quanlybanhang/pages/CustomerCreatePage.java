/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Customer;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.service.CustomerService;
import vn.aptech.quanlybanhang.service.CustomerServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public class CustomerCreatePage extends Page {

    @Override
    public void displayContent() {
        try {
            CustomerService customerService = new CustomerServiceImpl();
            String name = AppScanner.scanStringWithMessage(I18n.getMessage("customer.scan.name"));
            String phone = AppScanner.scanStringWithMessage(I18n.getMessage("customer.scan.phone"));
            String add = AppScanner.scanStringWithMessage(I18n.getMessage("customer.scan.addr"));
            Employee employee = AuthServiceImpl.getCurrentEmployee();
            Customer customer = new Customer(name, phone, add, employee);
            if (customerService.create(customer)) {
                I18n.printEntityMessage("customer", "entity.msg.created");
            } else {
                I18n.printEntityMessage("customer", "entity.msg.createFailed");
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerCreatePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("customer", "entity.title.create");
    }

}
