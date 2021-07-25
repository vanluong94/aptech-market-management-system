/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.menu;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.service.DiscountService;
import vn.aptech.quanlybanhang.service.DiscountServiceImpl;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public class DiscountMenu implements BaseMenu{
    
    private final DiscountService discountService;

    public DiscountMenu() {
        this.discountService = new DiscountServiceImpl();
    }

    @Override
    public void displayMenu() {
        
        System.out.println("=====================");
        System.out.println("= Quan ly danh sach Nha Cung Cap =");
        System.out.println("=====================");
        System.out.println("1. Danh sach Chuong trinh giam gia");
        System.out.println("2. Them mot Chuong trinh giam gia moi");
        System.out.println("3. Xem chi tiet mot Chuong trinh giam gia");
        System.out.println("4. Xoa mot Chuong trinh giam gia");
        System.out.println("0. Thoat");
    }

    @Override
    public void start(Scanner scanner) {
        try {
            int choice = -1;
            this.displayMenu();
            System.out.println("Vui long nhap lua chon [0-4]");
            choice = scanner.nextInt();
            scanner.nextLine();
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
                    String discountName = scanner.nextLine();
                    
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
                    int discountId = scanner.nextInt();
                    Discount discount = discountService.findById(discountId);
                    if (discount == null) {
                        System.out.println("Khong tim thay ID Chuong trinh giam gia phu hop!");
                    }else{
                        System.out.println(discount.toString());
                    }
                    break;
                case 4:
                    System.out.println("Nhap ID chuong trinh giam gia muon xoa :");
                    discountId = scanner.nextInt();
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
