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

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 */
public class CustomerEditPage extends Page {

    @Override
    public void displayContent() {
        try {
            CustomerService customerService = new CustomerServiceImpl();
            int check = AppScanner.scanIntWithMessage("Nhap ID khach hang muon sua thong tin : ", false);
            while (customerService.findById(check) == null) {
                System.out.println("ID khong ton tai !");
                check = AppScanner.scanIntWithMessage("Nhap ID khach hang muon sua thong tin : ", false);
            }
            String name = AppScanner.scanStringWithMessage("Sua ten khach hang thanh : ", false);
            String phone = AppScanner.scanStringWithMessage("Sua so dien thoai khach hang thanh: ", false);
            String add = AppScanner.scanStringWithMessage("Sua dia chi khach hang thanh : ", false);
            Customer customer = new Customer(check, name, phone, add);
            if (customerService.update(customer)) {
                System.out.println("Sua thanh cong !");
            } else {
                System.out.println("Da xay ra loi");
            }

        } catch (Exception ex) {
            Logger.getLogger(CustomerEditPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Sua khach hang";
    }
}
