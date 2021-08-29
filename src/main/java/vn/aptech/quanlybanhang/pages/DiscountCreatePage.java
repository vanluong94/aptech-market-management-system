/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.DateCommon;
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.entities.ProductDiscount;
import vn.aptech.quanlybanhang.service.DiscountService;
import vn.aptech.quanlybanhang.service.DiscountServiceImpl;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;

public class DiscountCreatePage extends Page {

    @Override
    public void displayContent() {

        DiscountService discountService = new DiscountServiceImpl();

        String discountName = AppScanner.scanStringWithMessage("Nhập tên chương trình giảm giá : ");

        try {
            
            Discount discount = new Discount(discountName);
            
            if(AppScanner.scanStringWithMessage("Bạn có muốn thêm sản phẩm cho chương trình giảm giá (y/n)? ").equalsIgnoreCase("y")) {
                
                List<ProductDiscount> dProducts = new ArrayList<>();
                
                do{
                    dProducts.add(this.addDiscountProduct());
                }while(AppScanner.scanStringWithMessage("Bạn có muốn thêm sản phẩm khác (y/n)? ").equalsIgnoreCase("y"));
                
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
    
    public ProductDiscount addDiscountProduct() {
        
        /**
         * input product_id
         */
        int productId = 0;
        ProductService productService = new ProductServiceImpl();
        try {
            while(productId == 0){
                
                productId = AppScanner.scanIntWithMessage("Nhập ID sản phẩm: ");
                
                if (productService.findById(productId) == null) {
                    System.out.println("Không tìm thấy Sản phẩm nào với ID trên, vui lòng thử lại.");
                    productId = 0;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DiscountCreatePage.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
                
        /**
         * input discount time range
         */
        Date dateFrom = null, dateTo = null;
        String dateFormatPattern = "dd/MM/yyyy HH:mm";
        do {
            String dateFromStr = AppScanner.scanStringWithMessage("Thời gian bắt đầu giảm giá [" + dateFormatPattern + "]: ");
            dateFrom = DateCommon.convertStringToDateByPattern(dateFromStr, dateFormatPattern);

            if (dateFrom == null) {
                System.out.println("Ngày đã nhập không hợp lệ, vui lòng thử lại.");
            }
        } while (dateFrom == null);

        do {
            String dateToStr = AppScanner.scanStringWithMessage("Thời gian kết thúc giảm giá [" + dateFormatPattern + "]: ");
            dateTo = DateCommon.convertStringToDateByPattern(dateToStr, dateFormatPattern);

            if (dateTo == null) {
                System.out.println("Ngày đã nhập không hợp lệ, vui lòng thử lại.");
            }
        } while (dateTo == null);
        
        
        /** 
         * input discount percentage
         */
        float discountPercent = AppScanner.scanFloatWithMessage("Nhập % giảm giá cho sản phẩm: ");

        ProductDiscount pDiscount = new ProductDiscount();
        pDiscount.setDiscount(discountPercent);
        pDiscount.setProductId(productId);
        pDiscount.setStartDate(dateFrom);
        pDiscount.setEndDate(dateTo);

        return pDiscount;
    }

}
