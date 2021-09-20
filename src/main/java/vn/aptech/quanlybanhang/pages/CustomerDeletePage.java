/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Customer;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.service.CustomerService;
import vn.aptech.quanlybanhang.service.CustomerServiceImpl;
import vn.aptech.quanlybanhang.service.OrderService;
import vn.aptech.quanlybanhang.service.OrderServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public class CustomerDeletePage extends Page {

    @Override
    public void displayContent() {
        try {
            CustomerService customerService = new CustomerServiceImpl();
            OrderService orderService = new OrderServiceImpl();
            Customer customer = null;
            
            int check = AppScanner.scanIntWithMessage(I18n.getEntityMessage("customer", "entity.scan.id.delete"));
            
            while ((customer = customerService.findById(check)) == null) {
                I18n.printEntityMessage("customer", "entity.error.idNotFound");
                check = AppScanner.scanIntWithMessage(I18n.getEntityMessage("customer", "entity.scan.id.delete"));
            }
            
            I18n.print(
                    "entity.msg.foundName", 
                    I18n.getMessage("customer.label.singular"), 
                    String.format("%s (%s: %s)", customer.getName(), I18n.getMessage("customer.phone"), customer.getPhone())
            );
            
            if(AppScanner.confirm(I18n.getEntityMessage("customer", "entity.confirm.delete"))) {
                Order order = orderService.findByCustomerId(check);
                if (order == null) {
                    if (customerService.deleteById(check)) {
                        I18n.printEntityMessage("customer", "entity.msg.deleted");
                    } else {
                        I18n.printEntityMessage("customer", "entity.error.deleteFailed");
                    }
                } else {
                    I18n.print("customer.error.cannotDelete");
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(CustomerDeletePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("customer", "entity.title.delete");
    }

}
