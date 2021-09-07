/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.DateCommon;
import vn.aptech.quanlybanhang.constant.Constant;
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.entities.ProductDiscount;
import vn.aptech.quanlybanhang.service.DiscountService;
import vn.aptech.quanlybanhang.service.DiscountServiceImpl;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;

public class DiscountEditPage extends Page {

    private Discount editingDiscount;
    private final DiscountService service;
    
    public DiscountEditPage() {
        this.service = new DiscountServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            
            do {
                
                int id = AppScanner.scanIntWithMessage("Nhập ID Chương trình khuyến mãi cần sửa : ", false);
                editingDiscount = service.findById(id);

                System.out.println("\n");
                
                if (editingDiscount == null) {
                    System.out.println("ID không hợp lệ!");
                } else {
                    System.out.println("Chương trình khuyến mại tìm thấy: " + editingDiscount.getName());
                    editingDiscount.setProductDiscounts(service.getDiscountProducts(editingDiscount));
                    
                    this.displaySubmenu();
                }
                
            }while(editingDiscount == null || AppScanner.confirm("\nBạn có muốn sửa chương trình khuyến mãi khác (y/n)? "));
            
        } catch (Exception e) {
            Logger.getLogger(DiscountEditPage.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public String getTitle() {
        return "Sửa Chương trình giảm giá";
    }
    
    private void displaySubmenu() throws Exception {
        
        do{
            System.out.println("\n");
            System.out.println("1. Sửa tên chương trình giảm giá");
            System.out.println("2. Xóa Sản phẩm khỏi chương trình khuyến mãi");
            System.out.println("3. Thêm Sản phẩm vào chương trình khuyến mãi");
            System.out.println("4. Sửa Sản phẩm trong chương trình khuyến mãi");
            System.out.println("\n");
            
            int choice;
            do{
                choice = AppScanner.scanIntWithMessage("Nhập lựa chọn: ");
            }while(choice < 1 && choice > 4);

            System.out.println("\n");
            switch(choice) {
                case 1:
                    this.handleEditDiscountName();
                    break;
                case 2:
                    this.handleDeleteProduct();
                    break;
                case 3:
                    this.handleAddProduct();
                    break;
                case 4:
                    this.handleEditProduct();
                    break;
            }

        }while(AppScanner.confirm("\nTiếp tục sửa chương trình khuyến mãi này (y/n)? "));
        
    }
    
    private void handleEditDiscountName() throws Exception {
        String newName = AppScanner.scanStringWithMessage("Nhập tên mới cho chương trình giảm giá: ");
        this.editingDiscount.setName(newName);
        
        if (this.service.update(editingDiscount)) {
            System.out.println("Cập nhật tên chương trình giảm giá thành công!");
        } else {
            System.out.println("Đã có lỗi xảy ra, không thể cập nhật tên cho chương trình giảm giá");
        }
    }
    
    private void handleDeleteProduct() {
        ProductDiscount dProduct = this.scanExistedDiscountProduct();
        if (service.deleteDiscountProduct(dProduct)) {
            System.out.println("Xóa thành công sản phẩm khuyến mãi");
            System.out.println(this.editingDiscount.getProductDiscounts().size());
            this.editingDiscount.getProductDiscounts().remove(dProduct);
            System.out.println(this.editingDiscount.getProductDiscounts().size());
        } else {
            System.out.println("Đã có lỗi xảy ra, không thể xóa sản phẩm");
        }
    }
    
    private void handleAddProduct() {
        ProductDiscount dProduct = this.scanNewDiscountProduct();
        ProductDiscount oDisProduct = service.findOverlapDiscountProduct(dProduct);

        if (oDisProduct != null) {
            System.out.printf(
                    "Sản phẩm \"%s\" đã có chương trình giảm giá khác vào từ %s tới %s\n", 
                    oDisProduct.getProduct().getName(),
                    DateCommon.convertDateToString(oDisProduct.getStartDate(), Constant.DATE_TIME_SIMPLE_FORMAT),
                    DateCommon.convertDateToString(oDisProduct.getEndDate(), Constant.DATE_TIME_SIMPLE_FORMAT)
            );
        } else if (service.createDiscountProduct(dProduct)) {
            System.out.println("Thêm sản phẩm giảm giá thành công!");
            editingDiscount.getProductDiscounts().add(dProduct);
        } else {
            System.out.println("Đã có lỗi xảy ra khi thêm sản phẩm, hãy thử lại sau");
        }
    }
    
    private void handleEditProduct() {
        ProductDiscount dProduct = this.scanExistedDiscountProduct();
        
        if(AppScanner.confirm("Bạn có muốn sửa % giảm giá của sản phẩm (y/n)? " )) {
            dProduct.setDiscountPercentage(dProduct.scanDiscountPercentage());
        }
        
        if(AppScanner.confirm("Bạn có muốn sửa ngày bắt đầu giảm giá của sản phẩm (y/n)? " )) {
            dProduct.setStartDate(dProduct.scanStartDate());
        }
        
        if(AppScanner.confirm("Bạn có muốn sửa ngày kết thúc giảm giá của sản phẩm (y/n)? " )) {
            dProduct.setEndDate(dProduct.scanEndDate());
        }
    }
    
    private ProductDiscount scanNewDiscountProduct() {
        
        Product product = null;
        ProductService productService = new ProductServiceImpl();
        
        try {
            while(product == null){
                
                int productId = AppScanner.scanIntWithMessage("Nhập ID sản phẩm: ");
                
                if (this.editingDiscount.findProductDiscount(productId) != null) {
                    System.out.println("Sản phẩm này đã tồn tại trong chương trình khuyến mãi này. Hãy chọn sản phẩm khác");
                } else {
                    
                    product = productService.findById(productId);
                    
                    if (product == null) {
                        System.out.println("Không tìm thấy Sản phẩm nào với ID trên, vui lòng thử lại.");
                    }
                    
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DiscountEditPage.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        ProductDiscount dProduct = new ProductDiscount();
        dProduct.setDiscountPercentage(dProduct.scanDiscountPercentage());
        dProduct.setProduct(product);
        dProduct.setStartDate(dProduct.scanStartDate());
        dProduct.setEndDate(dProduct.scanEndDate());
        dProduct.setDiscountId(this.editingDiscount.getId());

        return dProduct;
    }
 
    private ProductDiscount scanExistedDiscountProduct() {
        ProductDiscount pDiscount = null;
        while (pDiscount == null) {
            pDiscount = this.editingDiscount.findProductDiscount(AppScanner.scanIntWithMessage("Hãy nhập ID sản phẩm: ", false));

            if (pDiscount == null) {
                System.out.println("ID Sản phẩm không tồn tại trong chương trình khuyến mãi này");
            }
        }
        
        return pDiscount;
    }

}
