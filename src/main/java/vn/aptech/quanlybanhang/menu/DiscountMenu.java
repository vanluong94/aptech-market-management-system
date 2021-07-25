/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.menu;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.service.DiscountService;
import vn.aptech.quanlybanhang.service.DiscountServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public class DiscountMenu extends Menu{
    
    private final String TITLE = "Quan ly danh sach Giam gia";
    private final int[] CHOICES = {1,2,5,0}; // for validation purpose
    private final String[] MENU_ITEMS = {
        "1. Danh sach Chuong trinh giam gia",
        "2. Them mot Chuong trinh giam gia moi",
        "3. Xem chi tiet mot Chuong trinh giam gia",
        "4. Xoa mot Chuong trinh giam gia",
        "0. Thoat",
    };
    
    private final DiscountService discountService;

    public DiscountMenu() {
        this.discountService = new DiscountServiceImpl();
    }

    @Override
    public void handle(int choice) {
        try {
            switch(choice){
                case 1:
                    System.out.println("Danh sach Chuong Trinh Giam Gia");
                    List<Discount> discounts = discountService.findAll();
                    if (discounts.isEmpty()) {
                        System.out.println("Danh sach trong");
                    }else{
                        for (Discount discount : discounts) {
                            System.out.println(discount.toString());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Nhap ten chuong trinh giam gia :");
                    String discountName = AppScanner.getScanner().nextLine();
                    
                    if (discountName.length() > 0) {
                        Discount discount = new Discount(discountName);
                        if (discountService.create(discount)) {
                            System.out.println("Them chuong trinh giam gia thanh cong!");
                        }else{
                            System.out.println("Da xay ra loi!");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Nhap ID chuong trinh giam gia muon kiem tra :");
                    int discountId = AppScanner.getScanner().nextInt();
                    Discount discount = discountService.findById(discountId);
                    if (discount == null) {
                        System.out.println("Khong tim thay ID Chuong trinh giam gia phu hop!");
                    }else{
                        System.out.println(discount.toString());
                    }
                    break;
                case 4:
                    System.out.println("Nhap ID chuong trinh giam gia muon xoa :");
                    discountId = AppScanner.getScanner().nextInt();
                    if (discountService.deleteById(discountId)) {
                        System.out.println("Xoa thanh cong Chuong trinh giam gia ");
                    }else{
                        System.out.println("Da xay ra loi!");
                    }
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
