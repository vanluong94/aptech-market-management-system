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

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public class CustomerListingPage extends Page {

    @Override
    public void displayContent() {
        try {
            CustomerService customerService = new CustomerServiceImpl();
            List<Customer> customers = customerService.findAll();
            if (customers.isEmpty()) {
                System.out.println("Trong !");
            } else {
                List<Object[]> rows = new ArrayList<>();
                for (Customer c : customers) {
                    Object[] row = {
                        c.getId(),
                        c.getName(),
                        c.getAddress(),
                        c.getPhone(),
                        c.getEmployee().getEmployeeId(),
                        c.getEmployee().getName(),
                        c.getDiscount(),
                        c.getSalePoint()
                    };
                    rows.add(row);
                }
                String[] headers = {"ID", "Name", "Address", "Phone", "Employee ID", "Employee Name", "Discount", "Sale Point"};
                TableUI theTable = new TableUI(headers, rows);
                theTable.display();
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerListingPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Danh sach khach hang";
    }

}
