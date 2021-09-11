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

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public class CustomerSearchByPhonePage extends Page {

    @Override
    public void displayContent() {
        try {
            CustomerService customerService = new CustomerServiceImpl();
            Customer customer = new Customer();
            String check = AppScanner.scanStringWithMessage("Nhap so dien thoai khach hang : ", false);
            while (customerService.findByPhone(check) == null) {
                System.out.println("SDT khong ton tai !");
                check = AppScanner.scanStringWithMessage("Nhap so dien thoai khach hang : ", false);
            }
            List<Customer> customers = new ArrayList<>();
            customers.add(customerService.findByPhone(check));
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
        } catch (Exception ex) {
            Logger.getLogger(CustomerSearchByPhonePage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getTitle() {
        return "Tim khach hang bang SDT";
    }

}
