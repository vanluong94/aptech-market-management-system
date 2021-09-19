/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.entities.ImportProduct;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.entities.Supplier;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.service.BrandService;
import vn.aptech.quanlybanhang.service.BrandServiceImpl;
import vn.aptech.quanlybanhang.service.CategoryService;
import vn.aptech.quanlybanhang.service.CategoryServiceImpl;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.service.SupplierService;
import vn.aptech.quanlybanhang.service.SupplierServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class ImportProductPage extends Page {

    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    public ImportProductPage() {
        this.productService = new ProductServiceImpl();
        this.brandService = new BrandServiceImpl();
        this.categoryService = new CategoryServiceImpl();
        this.supplierService = new SupplierServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            System.out.println("Bạn có muốn xem danh sách thương hiệu? [y/N]");
            List<Brand> brands = brandService.findAll();
            if (brands.isEmpty()) {
                System.out.println("Chưa có thương hiệu nào.");
                System.out.println("Bạn có muốn thêm thương hiệu mới không? [y/N]");
            } else {
                List<Object[]> rows = new ArrayList<>();
                
                // transfer data to table row
                for (Brand brand : brands) {
                    Object[] row = {
                        brand.getId(),
                        brand.getName()
                    };

                    rows.add(row);
                }

                String[] headers = {"ID", "Tên thương hiệu"};

                TableUI theTable = new TableUI(headers, rows);
                theTable.display();
            }

            int brandId = AppScanner.scanIntWithMessage("Nhập mã thương hiệu: ");
            Brand brand = brandService.findById(brandId);
            if (brand == null) {
                System.out.println("Không tồn tại mã thương hiệu này");
            }
            
            System.out.println("Bạn có muốn xem danh sách danh mục? [y/N]");
            List<Category> categories = categoryService.findAll();
            if (categories.isEmpty()) {
                System.out.println("Chưa có danh mục nào.");
                System.out.println("Bạn có muốn thêm danh mục mới không? [y/N]");
            } else {
                List<Object[]> rows = new ArrayList<>();
                
                // transfer data to table row
                for (Category category : categories) {
                    Object[] row = {
                        category.getId(),
                        category.getName()
                    };

                    rows.add(row);
                }

                String[] headers = {"ID", "Tên danh mục"};

                TableUI theTable = new TableUI(headers, rows);
                theTable.display();
            }
            
            int categoryId = AppScanner.scanIntWithMessage("Nhập mã danh mục: ");
            Category category = categoryService.findById(categoryId);
            if (category == null) {
                System.out.println("Không tồn tại mã danh mục này");
            }
            
            System.out.println("Bạn có muốn xem danh sách nhà cung cấp? [y/N]");
            List<Supplier> suppliers = supplierService.findAll();
            if (suppliers.isEmpty()) {
                System.out.println("Chưa có nhà cung cấp nào.");
                System.out.println("Bạn có muốn thêm nhà cung cấp mới không? [y/N]");
            } else {
                List<Object[]> rows = new ArrayList<>();
                
                // transfer data to table row
                for (Supplier supplier : suppliers) {
                    Object[] row = {
                        supplier.getId(),
                        supplier.getName()
                    };

                    rows.add(row);
                }

                String[] headers = {"ID", "Tên nhà cung cấp"};

                TableUI theTable = new TableUI(headers, rows);
                theTable.display();
            }

            int supplierId = AppScanner.scanIntWithMessage("Nhập mã nhà cung cấp: ");
            Supplier supplier = supplierService.findById(supplierId);
            if (supplier == null) {
                System.out.println("Không tồn tại mã nhà cung cấp này");
            }
            
            String name = AppScanner.scanStringWithMessage("Nhập tên sản phẩm: ");
            double price = AppScanner.scanDoubleWithMessage("Nhập giá tiền: ");
            int quantity = AppScanner.scanIntWithMessage("Nhập số lượng: ");
            Product product = new Product();
            product.setBrand(brand);
            product.setCategory(category);
            product.setEmployee(AuthServiceImpl.getCurrentEmployee());
            product.setName(name);
            product.setPrice(price);
            product.setQuantityInStock(quantity);
            product.setSupplier(supplier);
            if (productService.create(product)) {
                System.out.println("Thêm sản phẩm mới thành công!");
            } else {
                System.out.println("Đã xảy ra lỗi");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportProductPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ImportProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Nhập hàng";
    }

}
