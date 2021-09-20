/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.ValidateCommon;
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.entities.Category;
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
import vn.aptech.quanlybanhang.utilities.I18n;

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
            Brand brand = null;
            Supplier supplier = null;
            Category category = null;
            Product product = new Product();
            if (AppScanner.confirm(I18n.getMessage("entity.confirm.showList", I18n.getMessage("brand.label.plural")))) {
                List<Brand> brands = brandService.findAll();
                if (brands.isEmpty()) {
                    I18n.printEntityMessage("brand", "entity.msg.emptyResults");
                } else {
                    List<Object[]> rows = new ArrayList<>();
                    // transfer data to table row
                    for (Brand b : brands) {
                        Object[] row = {
                            b.getId(),
                            b.getName()
                        };

                        rows.add(row);
                    }
                    String[] headers = {"ID", I18n.getMessage("brand.name")};
                    TableUI theTable = new TableUI(headers, rows);
                    theTable.display();
                }
            }

            while (brand == null) {
                int brandId = AppScanner.scanIntWithi18Message("product.scan.brand");
                I18n.print("product.msg.checkingBrandId");
                brand = brandService.findById(brandId);
                if (brand == null) {
                    I18n.printEntityMessage("brand", "entity.error.idNotFound");
                } else {
                    I18n.print("entity.msg.foundName", I18n.getMessage("brand.label.singular"), brand.getName());
                    product.setBrand(brand);
                }
            }

            if (AppScanner.confirm(I18n.getMessage("entity.confirm.showList", I18n.getMessage("category.label.plural")))) {
                List<Category> categories = categoryService.findAll();
                if (categories.isEmpty()) {
                    I18n.printEntityMessage("category", "entity.msg.emptyResults");
                } else {
                    List<Object[]> rows = new ArrayList<>();
                    // transfer data to table row
                    for (Category c : categories) {
                        Object[] row = {
                            c.getId(),
                            c.getName()
                        };
                        rows.add(row);
                    }
                    String[] headers = {"ID", I18n.getMessage("category.name")};
                    TableUI theTable = new TableUI(headers, rows);
                    theTable.display();
                }
            }

            while (category == null) {
                int categoryId = AppScanner.scanIntWithi18Message("product.scan.category");
                I18n.print("product.msg.checkingCategoryId");
                category = categoryService.findById(categoryId);
                if (category == null) {
                    I18n.printEntityMessage("category", "entity.error.idNotFound");
                } else {
                    I18n.print("entity.msg.foundName", I18n.getMessage("category.label.singular"), category.getName());
                    product.setCategory(category);
                }
            }

            if (AppScanner.confirm(I18n.getMessage("entity.confirm.showList", I18n.getMessage("supplier.label.plural")))) {
                List<Supplier> suppliers = supplierService.findAll();
                if (suppliers.isEmpty()) {
                    I18n.printEntityMessage("supplier", "entity.msg.emptyResults");
                } else {
                    List<Object[]> rows = new ArrayList<>();
                    // transfer data to table row
                    for (Supplier s : suppliers) {
                        Object[] row = {
                            s.getId(),
                            s.getName()
                        };
                        rows.add(row);
                    }
                    String[] headers = {"ID", I18n.getMessage("supplier.name")};
                    TableUI theTable = new TableUI(headers, rows);
                    theTable.display();
                }
            }

            while (supplier == null) {
                int supplierId = AppScanner.scanIntWithi18Message("product.scan.supplier");
                I18n.print("product.msg.checkingSupplierId");
                supplier = supplierService.findById(supplierId);
                if (supplier == null) {
                    I18n.printEntityMessage("supplier", "entity.error.idNotFound");
                } else {
                    I18n.print("entity.msg.foundName", I18n.getMessage("supplier.label.singular"), supplier.getName());
                    product.setSupplier(supplier);
                }
            }
            String name = "";

            while (ValidateCommon.isValidStringLength(name, 3, 100)) {
                name = AppScanner.scanStringWithi18Message("product.scan.name");

                if (ValidateCommon.isValidStringLength(name, 3, 100)) {
                    I18n.print("product.error.invalidNameLength", new Object[]{3, 100});
                }
            }
            double price = 0;
            while (price <= 100) {
                price = AppScanner.scanDoubleWithMessage(I18n.getMessage("product.scan.price"));

                if (price <= 100) {
                    I18n.print("product.error.invalidPrice");
                }
            }

            int qty = 0;
            while (qty <= 0) {
                qty = AppScanner.scanIntWithi18Message("product.scan.qty");

                if (qty <= 0) {
                    I18n.print("product.error.invalidQty");
                }
            }
            product.setEmployee(AuthServiceImpl.getCurrentEmployee());
            product.setName(name);
            product.setPrice(price);
            product.setQuantityInStock(qty);
            if (productService.create(product)) {
                I18n.printEntityMessage("product", "entity.msg.created");
            } else {
                I18n.printEntityMessage("product", "entity.msg.createFailed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportProductPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ImportProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getMessage("product.import.label");
    }

}
