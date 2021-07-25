/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.menu;


import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.service.SupplierService;
import vn.aptech.quanlybanhang.service.SupplierServiceImpl;

/**
 *
 * @author VuxxLong
 */
public class SupplierMenu implements BaseMenu{

    private final SupplierService supplierService;

    public SupplierMenu() {
        this.supplierService = new SupplierServiceImpl();
    }
    
    
    @Override
    public void displayMenu() {

        System.out.println("=====================");
        System.out.println("= Quan ly danh sach Nha Cung Cap =");
        System.out.println("=====================");
        System.out.println("1. Danh sach Nha cung cap");
        System.out.println("2. Them Nha cung cap");
        System.out.println("3. Xem chi tiet Nha cung cap");
        System.out.println("4. Xoa 1 Nha cung cap");
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
                    System.out.println("Danh sach Nha Cung Cap");
                    List<Supplier> suppliers = supplierService.findAll();
                    if (suppliers.isEmpty()) {
                        System.out.println("Danh sach trong");
                    }else{
                        for (Supplier supplier : suppliers) {
                            System.out.println(supplier.toString());
                        }
                    }
                    break;
                case 2:
                    System.out.println("Nhap ten nha cung cap :");
                    String supplierName = scanner.nextLine();
                    System.out.println("Nhap dia chi nha cung cap :");
                    String supplierAddress = scanner.nextLine();
                    
                    if (supplierName.length() > 0 && supplierAddress.length() > 0 ) {
                        Supplier supplier = new Supplier(supplierName,supplierAddress);
                        if (supplierService.create(supplier)) {
                            System.out.println("Them nha cung cap thanh cong!");
                        }else{
                            System.out.println("Da xay ra loi!");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Nhap ID nha cung cap muon kiem tra : ");
                    int supplierId = scanner.nextInt();
                    Supplier supplier = supplierService.findById(supplierId);
                    if (supplier == null) {
                        System.out.println("Khong tim thay ID nha cung cap phu hop");
                    }else{
                        System.out.println(supplier.toString());
                    }
                    break;
                case 4:
                    System.out.println("Nhap ID nha cung cap muon xoa : ");
                    supplierId = scanner.nextInt();
                    if (supplierService.deleteById(supplierId)) {
                        System.out.println("Xoa thanh cong Nha cung cap!");
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
