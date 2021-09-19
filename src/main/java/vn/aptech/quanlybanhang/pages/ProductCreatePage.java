/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
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
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class ProductCreatePage extends Page {

    private final ProductService productService;

    public ProductCreatePage() {
        this.productService = new ProductServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            
            Product product = new Product();
            
            BrandService brandService = new BrandServiceImpl();
            SupplierService supService = new SupplierServiceImpl();
            CategoryService catService = new CategoryServiceImpl();
            
            Brand brand = null;
            Supplier supplier = null;
            Category category = null;
            
            while(brand == null) {
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
            System.out.println("");
            
            while(category == null) {
                int categoryId = AppScanner.scanIntWithi18Message("product.scan.category");
                
                I18n.print("product.msg.checkingCategoryId");
                category = catService.findById(categoryId);

                if (category == null) {
                    I18n.printEntityMessage("category", "entity.error.idNotFound");
                } else {
                    I18n.print("entity.msg.foundName", I18n.getMessage("category.label.singular"), category.getName());
                    product.setCategory(category);
                }
            }
            System.out.println("");
            
            while(supplier == null) {
                int supplierId = AppScanner.scanIntWithi18Message("product.scan.supplier");
                
                I18n.print("product.msg.checkingSupplierId");
                supplier = supService.findById(supplierId);
                
                if (supplier == null) {
                    I18n.printEntityMessage("supplier", "entity.error.idNotFound");
                } else {
                    I18n.print("entity.msg.foundName", I18n.getMessage("supplier.label.singular"), supplier.getName());
                    product.setSupplier(supplier);
                }
            }
            System.out.println("");
            
            String name = AppScanner.scanStringWithi18Message("product.scan.name");
            
            double price = 0;
            while (price <= 100) {
                price = AppScanner.scanDoubleWithMessage(I18n.getMessage("product.scan.price"));
                
                if (price <= 100){
                    I18n.print("product.error.invalidPrice");
                }
            }
            
            int qty = 0;
            while (qty <= 0) {
                qty = AppScanner.scanIntWithi18Message("product.scan.qty");
                
                if (qty <= 0){
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
        } catch (Exception ex) {
            Logger.getLogger(ProductCreatePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("product", "entity.title.create");
    }

}
