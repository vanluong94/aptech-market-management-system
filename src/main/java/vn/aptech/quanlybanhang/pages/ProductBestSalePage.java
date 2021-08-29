/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.DateCommon;
import vn.aptech.quanlybanhang.constant.Constant;
import vn.aptech.quanlybanhang.entities.ImportProduct;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.PaginatedResults;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ProductBestSalePage extends Page {

    private final ProductService productService;

    public ProductBestSalePage() {
        this.productService = new ProductServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            int page = 1;
            do {
                String fromDate = AppScanner.scanStringWithMessage("Nhập thời gian từ ngày muốn xem [dd/MM/yyyy]: ", true);
                String toDate = AppScanner.scanStringWithMessage("Đến ngày [dd/MM/yyyy]: ", true);
                PaginatedResults<Product> results = productService.findAllByOrderByUnitsOnOrderDesc(page, fromDate, toDate);
                if (results.getResults().isEmpty()) {
                    System.out.println("<Không có Sản phẩm nào>");
                    return;
                }
                System.out.println("Tìm thấy " + results.getTotalItems() + " sản phẩm");
                List<Object[]> rows = new ArrayList<>();

                // transfer data to table row
                for (Product product : results.getResults()) {
                    Object[] row = {
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getQuantityInStock(),
                        product.getUnitsOnOrder(),
                        product.getCategory().getCategoryName(),
                        product.getBrand().getBrandName(),
                        product.getSupplier().getName(),};

                    rows.add(row);
                }

                String[] headers = {"ID", "Tên SP", "Giá", "SL", "SL đã bán", "Danh mục", "Nhãn hàng", "Nhà cung cấp"};

                TableUI theTable = new TableUI(headers, rows);
                theTable.display();

                if (results.needsPagination()) {
                    results.displayPagination(); //pagination
                    results.displayPaginationMenu(); //pagination menu

                    page = results.scanGoPage();

                    System.out.println("\n\n");
                } else {
                    page = 0;
                }

            } while (page > 0);

        } catch (SQLException ex) {
            Logger.getLogger(BrandListingPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BrandListingPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getTitle() {
        return "Danh sách Sản phẩm bán chạy";
    }

    @Override
    public String getBreadcrumbPathName() {
        return "Sản phẩm bán chạy";
    }

}
