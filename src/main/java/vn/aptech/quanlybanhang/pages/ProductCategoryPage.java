/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

public class ProductCategoryPage extends Page {

    private final ProductService productService;

    public ProductCategoryPage() {
        this.productService = new ProductServiceImpl();
    }

    @Override
    public void displayContent() {

        String choice = null;

        try {
            int page = 1;
            do {
                int categoryId = AppScanner.scanIntWithMessage("Nhập ID Danh mục Sản phẩm bạn muốn xem : ");
                PaginatedResults<Product> products = productService.findByCategoryId(page, categoryId);
                if (products.getResults().isEmpty()) {
                    System.out.println("<Không có Sản phẩm nào>");
                    return;
                }
                List<Object[]> rows = new ArrayList<Object[]>();
                for (Product product : products.getResults()) {
                    Object[] row = {
                        product.getId(),
                        product.getBrand().getBrandName(),
                        product.getCategory().getCategoryName(),
                        product.getSupplier().getName(),
                        product.getEmployee().getName(),
                        product.getName(),
                        product.getPriceString(),
                        product.getQuantityInStock(),
                        product.getCreatedAt(),
                        product.getUpdatedAt()
                    };
                    rows.add(row);
                }
                String[] headers = {"ID", "Nhãn hàng", "Loại sản phẩm", "Nhà cung cấp", "Nhân viên", "Tên Sản phẩm", "Giá", "Số lượng trong kho", "Ngày khởi tạo", "Ngày cập nhật"};
                TableUI tableUI = new TableUI(headers, rows);
                tableUI.display();

                if (products.needsPagination()) {
                    products.displayPagination();
                    products.displayPaginationMenu();
                    page = products.scanGoPage();
                    System.out.println("\n\n");
                } else {
                    page = 0;
                }

                choice = AppScanner.scanStringWithMessage("Bạn có muốn tìm loại sản phẩm khác không? [Y/N] : ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }

            } while ("y".equalsIgnoreCase(choice));
        } catch (SQLException ex) {
            Logger.getLogger(ProductCategoryPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductCategoryPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getTitle() {
        return "Xem danh sach Sản phẩm theo Danh mục";
    }

    @Override
    public String getBreadcrumbPathName() {
        return "Danh sách SP theo Danh mục";
    }

}
