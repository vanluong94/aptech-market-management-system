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
            Customer customer = new Customer();
            int check = AppScanner.scanIntWithMessage("Nhap ID khach hang muon sua thong tin : ", false);
            System.out.print("Nhap ID khach hang muon xoa: ");
            while (customerService.findById(check) == null) {
                System.out.println("ID khong ton tai !");
                check = AppScanner.scanIntWithMessage("Nhap ID khach hang muon sua thong tin : ", false);
            }
            Order order = orderService.findByCustomerId(check);
            if (order == null) {
                if (customerService.deleteById(check)) {
                    System.out.println("Xoa thanh cong !");
                } else {
                    System.out.println("Da xay ra loi !");
                }
            } else {
                System.out.println("Khong the xoa ! Khach hang da co hoa don!");
            }

        } catch (Exception ex) {
            Logger.getLogger(CustomerDeletePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Xoa khach hang";
    }

}
