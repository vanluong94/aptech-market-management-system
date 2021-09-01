/*
 * Do an Java tai HaNoi Aptech
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

/**
 *
 * @author vanluong
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public class ProductSearchPage extends Page {

    private final ProductService productService;

    public ProductSearchPage() {
        this.productService = new ProductServiceImpl();
    }

    @Override
    public void displayContent() {

        String choice = null;
        try {
            int page = 1;
            do {
                String search = AppScanner.scanStringWithMessage("Tìm kiếm sản phẩm cần tìm theo tên: ");
                PaginatedResults<Product> products = productService.findByName(page, search);

                if (products.getResults().isEmpty()) {
                    System.out.println("<Không có Sản phẩm nào>");
                    return;
                }
                System.out.println(String.format("Các Sản phẩm được tìm thấy theo tên: \"%s\"", search));

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
                choice = AppScanner.scanStringWithMessage("Bạn có muốn tìm Sản phẩm khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            } while ("y".equalsIgnoreCase(choice) && page > 0);

        } catch (SQLException ex) {
            Logger.getLogger(ProductSearchPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductSearchPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Tìm kiếm Sản phẩm";
    }

}
