/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.entities.ProductDiscount;
import vn.aptech.quanlybanhang.service.DiscountService;
import vn.aptech.quanlybanhang.service.DiscountServiceImpl;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class DiscountCreatePage extends Page {

    @Override
    public void displayContent() {

        DiscountService discountService = new DiscountServiceImpl();

        String discountName = AppScanner.scanStringWithMessage("Nhập tên chương trình giảm giá : ");

        try {
            
            Discount discount = new Discount(discountName);
            
            if(AppScanner.confirm("Bạn có muốn thêm sản phẩm cho chương trình giảm giá (y/n)? ")) {
                
                List<ProductDiscount> dProducts = new ArrayList<>();
                
                do{
                    ProductDiscount dProduct = this.scanNewDiscountProduct();
                    ProductDiscount oDisProduct = discountService.findOverlapDiscountProduct(dProduct);
                    
                    if (oDisProduct != null) {
                        System.out.println("Sản phẩm này đã có chương trình giảm giá khác vào khoảng thời gian này");
                    } else {
                        dProducts.add(dProduct);
                    }
                    
                }while(AppScanner.confirm("Bạn có muốn thêm sản phẩm khác (y/n)? "));
                
                discount.setProductDiscounts(dProducts);
                
            }
            
            if (!discountService.create(discount) || discount.getId() == 0 ) {
                System.out.println("Đã xảy ra lỗi!");
            }else{
                System.out.println("Thêm chương trình giảm giá thành công!");
            }
            

        } catch (Exception ex) {
            Logger.getLogger(DiscountCreatePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return "Thêm Chương trình giảm giá";
    }
    
    private ProductDiscount scanNewDiscountProduct() {
        
        /**
         * input product_id
         */
        Product product = null;
        ProductService productService = new ProductServiceImpl();
        try {
            while(product == null){
                
                product = productService.findById(AppScanner.scanIntWithMessage("Nhập ID sản phẩm: "));
                
                if (product == null) {
                    System.out.println("Không tìm thấy Sản phẩm nào với ID trên, vui lòng thử lại.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DiscountCreatePage.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        ProductDiscount pDiscount = new ProductDiscount();
        pDiscount.setDiscountPercentage(pDiscount.scanDiscountPercentage());
        pDiscount.setProduct(product);
        pDiscount.setStartDate(pDiscount.scanStartDate());
        pDiscount.setEndDate(pDiscount.scanEndDate());

        return pDiscount;
    }

}
