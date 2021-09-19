/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Customer;
import vn.aptech.quanlybanhang.service.CustomerService;
import vn.aptech.quanlybanhang.service.CustomerServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public class CustomerSearchByPhonePage extends Page {

    @Override
    public void displayContent() {
        try {
            CustomerService customerService = new CustomerServiceImpl();
            String check = AppScanner.scanStringWithMessage(I18n.getMessage("customer.scan.searchPhone"));
            while (customerService.findByPhone(check) == null) {
                I18n.printEntityMessage("customer", "entity.msg.emptyResults");
                check = AppScanner.scanStringWithMessage(I18n.getMessage("customer.scan.searchPhone"));
            }
            List<Customer> customers = new ArrayList<>();
            customers.add(customerService.findByPhone(check));
            if (customers.isEmpty()) {
                I18n.printEntityMessage("customer", "entity.msg.emptyResults");
            } else {
                List<Object[]> rows = new ArrayList<>();
                for (Customer c : customers) {
                    Object[] row = {
                        c.getId(),
                        c.getName(),
                        c.getAddress(),
                        c.getPhone(),
                        String.format("%s (ID:%d)", c.getEmployee().getName(), c.getEmployee().getId())
                    };
                    rows.add(row);
                }
                
                String[] headers = {
                    "ID", 
                    I18n.getMessage("customer.name"), 
                    I18n.getMessage("customer.addr"), 
                    I18n.getMessage("customer.phone"),
                    I18n.getMessage("customer.emp")
                };
                TableUI theTable = new TableUI(headers, rows);
                theTable.display();
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerSearchByPhonePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getTitle() {
        return I18n.getMessage("customer.title.searchByPhone");
    }

}
