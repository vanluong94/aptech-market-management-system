/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
public class CustomerSearchByIDPage extends Page {

    @Override
    public void displayContent() {
        try {
            CustomerService customerService = new CustomerServiceImpl();
            Customer customer = new Customer();
            int check = AppScanner.scanIntWithMessage("Nhap ID khach hang muon tim kiem : ", false);
            while (customerService.findById(check) == null) {
                System.out.println("ID khong ton tai !");
                check = AppScanner.scanIntWithMessage("Nhap ID khach hang muon tim kiem: ", false);
            }
            List<Customer> customers = new ArrayList<>();
            customers.add(customerService.findById(check));
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
            Logger.getLogger(CustomerSearchByIDPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getTitle() {
        return "Tim khach hang bang ID";
    }

}
