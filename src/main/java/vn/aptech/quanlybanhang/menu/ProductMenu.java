/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.menu;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.ui.HeaderUI;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ProductMenu extends Menu {

    private final String TITLE = "Quan ly danh sach San Pham";
    private final int[] CHOICES = {1, 2, 3, 4, 5, 6, 0};
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
                case 6:
                    HeaderUI.display("Chỉnh sửa thông tin sản phẩm");
                    this.handleEdit();
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

    void handleEdit() {
        this.handleSearch();
        String choice = null;
        do {
            try {
                int id = AppScanner.scanIntWithMessage("Nhập ID sản phẩm cần sửa: ");
                Product product = productService.findById(id);
                if (product == null) {
                    System.out.println("ID không tồn tại!");
                } else {
                    System.out.println("Các trường đánh dấu * bắt buộc nhập. Nhấn <enter> để giữ lại thông tin cũ.");
                    String name = AppScanner.scanStringWithMessage("Tên sản phẩm: ");
                    double price = AppScanner.scanDoubleWithMessage("Giá: ");
                    int qty = AppScanner.scanIntWithMessage("Số lượng: ");
                    if (name.length() > 0) {
                        product.setName(name);
                    }
                    if (price > 0) {
                        product.setPrice(price);
                    }
                    if (qty > 0) {
                        product.setQuantityInStock(qty);
                    }
                    if (productService.update(product)) {
                        System.out.println("Cập nhật thông tin sản phẩm thành công!");
                    } else {
                        System.out.println("Không cập nhật được. Vui lòng thử lại.");
                    }
                }
                choice = AppScanner.scanStringWithMessage("Bạn có muốn nhập lại không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            } catch (Exception ex) {
                Logger.getLogger(ProductMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while ("y".equalsIgnoreCase(choice));
    }

    void handleSearch() {
        String choice = null;
        do {
            try {
                String search = AppScanner.scanStringWithMessage("Tìm sản phẩm cần sửa theo tên: ");
                List<Product> products = this.productService.findByName(search);
                if (products.isEmpty()) {
                    System.out.println("Không tìm thấy sản phẩm nào!");
                } else {
                    System.out.println(String.format("Các sản phẩm được tìm thấy theo tên: \"%s\"", search));
                    List<Object[]> rows = new ArrayList<>();
                    DecimalFormat formatter = new DecimalFormat("###,###,###");
                    products.forEach((Product product) -> {
                        Object[] row = {
                            product.getId(),
                            product.getName(),
                            formatter.format(product.getPrice()) + " VND",
                            product.getQuantityInStock()
                        };
                        
                        rows.add(row);
                    });
                    String[] headers = {"ID", "Name", "Price", "Quantity"};
                    TableUI tableUI = new TableUI(headers, rows);
                    tableUI.display();
                }
                choice = AppScanner.scanStringWithMessage("Bạn có muốn tìm sản phẩm khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            } catch (SQLException e) {
                System.out.println("Exception when ProductMenu.handleSearch: " + e.getMessage());
            }
        } while ("y".equalsIgnoreCase(choice));
    }
}
