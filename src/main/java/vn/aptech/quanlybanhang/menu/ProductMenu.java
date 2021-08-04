/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.menu;

import java.util.LinkedHashMap;
import vn.aptech.quanlybanhang.menu.items.ExitMenuItem;
import vn.aptech.quanlybanhang.menu.items.GoBackMenuItem;
import vn.aptech.quanlybanhang.pages.ProductCategoryPage;
import vn.aptech.quanlybanhang.pages.ProductCreatePage;
import vn.aptech.quanlybanhang.pages.ProductDeletePage;
import vn.aptech.quanlybanhang.pages.ProductDetailPage;
import vn.aptech.quanlybanhang.pages.ProductEditPage;
import vn.aptech.quanlybanhang.pages.ProductListingPage;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ProductMenu extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();
        
        menuItems.put(1, new ProductListingPage());
        menuItems.put(2, new ProductCreatePage());
        menuItems.put(3, new ProductDetailPage());
        menuItems.put(4, new ProductCategoryPage());
        menuItems.put(5, new ProductDeletePage());
        menuItems.put(6, new ProductEditPage());
        
        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());
        
        return menuItems;
    }
    
//    public void handle(int choice) {
//        try {
//            Product product = null;
//            switch (choice) {
//                case 2:
//                    Brand brandId = new Brand();
//                    brandId.setBrandId(AppScanner.scanIntWithMessage("Nhap Brand ID : "));
//
//                    Category categoryId = new Category();
//                    categoryId.setCategoryId(AppScanner.scanIntWithMessage("Nhap Category ID :"));
//
//                    Employee employeeId = new Employee();
//                    employeeId.getEmployeeId();
////                    employeeId.setEmployeeId(AppScanner.scanIntWithMessage("Nhap Employee ID :"));
//
//                    String productName = AppScanner.scanStringWithMessage("Nhap ten San Pham :");
//                    double productPrice = AppScanner.scanDoubleWithMessage("Gia tien :");
//                    int productStock = AppScanner.scanIntWithMessage("So luong :");
//
//                    Product productNew = new Product(brandId, categoryId, employeeId, productName, productPrice, productStock);
//                    if (productService.create(productNew)) {
//                        System.out.println("Them San Pham moi thanh cong!");
//                    } else {
//                        System.out.println("Da xay ra loi");
//                    }
//                    break;
//                case 3:
//                    int productId = AppScanner.scanIntWithMessage("Nhap ID san phan ban muon xem : ");
//                    product = productService.findById(productId);
//                    if (product == null) {
//                        System.out.println("Khong tim thay ID San pham phu hop!");
//                    } else {
//                        System.out.println(product.toString());
//                    }
//                    break;
//                case 4:
//                    Category categoryId2 = new Category();  //x�a s? 2 sau khi test th�nh c�ng
//                    categoryId2.setCategoryId(AppScanner.scanIntWithMessage("Nhap ID Loai san pham ban muon xem : "));
//                    List<Product> products = productService.findByCategoryId(categoryId2.getCategoryId());
//                    if (products.isEmpty()) {
//                        System.out.println("Danh sach trong");
//                    } else {
//                        System.out.println(product.toString());
//                    }
//                    break;
//                case 6:
//                    HeaderUI.display("Chỉnh sửa thông tin sản phẩm");
//                    this.handleEdit();
//                    break;
//                case 0:
//                    System.exit(0);
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    void handleEdit() {
//        this.handleSearch();
//        String choice = null;
//        do {
//            try {
//                int id = AppScanner.scanIntWithMessage("Nhập ID sản phẩm cần sửa: ");
//                Product product = productService.findById(id);
//                if (product == null) {
//                    System.out.println("ID không tồn tại!");
//                } else {
//                    System.out.println("Các trư�?ng đánh dấu * bắt buộc nhập. Nhấn <enter> để giữ lại thông tin cũ.");
//                    String name = AppScanner.scanStringWithMessage("Tên sản phẩm: ");
//                    double price = AppScanner.scanDoubleWithMessage("Giá: ");
//                    int qty = AppScanner.scanIntWithMessage("Số lượng: ");
//                    if (name.length() > 0) {
//                        product.setName(name);
//                    }
//                    if (price > 0) {
//                        product.setPrice(price);
//                    }
//                    if (qty > 0) {
//                        product.setQuantityInStock(qty);
//                    }
//                    if (productService.update(product)) {
//                        System.out.println("Cập nhật thông tin sản phẩm thành công!");
//                    } else {
//                        System.out.println("Không cập nhật được. Vui lòng thử lại.");
//                    }
//                }
//                choice = AppScanner.scanStringWithMessage("Bạn có muốn nhập lại không? [y/N]: ");
//                if (!"y".equalsIgnoreCase(choice)) {
//                    break;
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(ProductMenu.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } while ("y".equalsIgnoreCase(choice));
//    }

    @Override
    protected String registerMenuTitle() {
        return "Quan ly danh sach San Pham";
    }

}
