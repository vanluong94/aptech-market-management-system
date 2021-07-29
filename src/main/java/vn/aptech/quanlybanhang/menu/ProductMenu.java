/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.menu;

import java.sql.SQLException;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public class ProductMenu extends Menu {

    private final String TITLE = "Quan ly danh sach San Pham";
    private final int[] CHOICES = {1, 2, 3, 4, 5, 0};
    private final String[] MENU_ITEMS = {
        "1. Danh sach San Pham",
        "2. Them San Pham moi",
        "3. Xem chi tiet mot San Pham",
        "4. Xem danh sach San Pham theo Loai san pham",
        "5. Xoa mot San Pham",
        "6. Chinh sua thong tin cua mot San Pham",
        "0. Thoat"
    };

    private final ProductService productService;

    public ProductMenu() {
        this.setMenuItems(this.MENU_ITEMS);
        this.setTitle(this.TITLE);
        this.setChoices(this.CHOICES);
        this.productService = new ProductServiceImpl();
    }

    @Override
    public void handle(int choice) {
        try {
            Product product = null;
            switch (choice) {
                case 3:
                    int productId = AppScanner.scanIntWithMessage("Nhap ID san phan ban muon xem : ");
                    product = productService.findById(productId);
                    if (product == null) {
                        System.out.println("Khong tim thay ID San pham phu hop!");
                    } else {
                        System.out.println(product.toString());
                    }
                    break;
                case 4:
                    int categoryId = AppScanner.scanIntWithMessage("Nhap ID Loai san pham ban muon xem : ");
                    List<Product> products = productService.findByCategoryId(categoryId);
                    if (products.isEmpty()) {
                        System.out.println("Danh sach trong");
                    } else {
                        System.out.println(product.toString());
                    }
                    break;
                case 0:
                    System.exit(0);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
