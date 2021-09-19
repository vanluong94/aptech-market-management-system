/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Customer;
import vn.aptech.quanlybanhang.service.CustomerService;
import vn.aptech.quanlybanhang.service.CustomerServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public class CustomerListingPage extends Page {

    private final CustomerService customerService;

    public CustomerListingPage() {
        customerService = new CustomerServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            List<Customer> customers = customerService.findAll();
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

        } catch (SQLException ex) {
            Logger.getLogger(CustomerListingPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CustomerListingPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("customer", "entity.title.all", true);
    }

}
