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

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public class CustomerCreatePage extends Page {

    @Override
    public void displayContent() {
        try {
            CustomerService customerService = new CustomerServiceImpl();
            String name = AppScanner.scanStringWithMessage("Nhap ten khach hang: ", false);
            String phone = AppScanner.scanStringWithMessage("Nhap so dien thoai khach hang : ", false);
            String add = AppScanner.scanStringWithMessage("Nhap dia chi khach hang : ", false);
            Employee employee = AuthServiceImpl.getCurrentEmployee();
            Customer customer = new Customer(name, phone, add, employee);
            if (customerService.create(customer)) {
                System.out.println("Them khach hang thanh cong !");
            } else {
                System.out.println("Da xay ra loi !");
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerCreatePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Them khach hang";
    }

}
