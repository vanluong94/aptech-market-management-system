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
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 * 
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
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
                
                int id = AppScanner.scanIntWithMessage(I18n.getEntityMessage("discount", "entity.scan.id.edit"));
                editingDiscount = service.findById(id);

                System.out.println("\n");
                
                if (editingDiscount == null) {
                    I18n.getEntityMessage("discount", "entity.error.idNotFound");
                } else {
                    System.out.println(I18n.getMessage("discount.msg.foundOne") + editingDiscount.getName());
                    editingDiscount.setProductDiscounts(service.getDiscountProducts(editingDiscount));
                    
                    this.displaySubmenu();
                }
                
            }while(editingDiscount == null || AppScanner.confirm("\n" + I18n.getEntityMessage("discount", "entity.confirm.editAnOther")));
            
        } catch (Exception e) {
            Logger.getLogger(DiscountEditPage.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("discount", "entity.title.edit");
    }
    
    private void displaySubmenu() throws Exception {
        
        do{
            System.out.println("\n");
            I18n.print("discount.submenu.editName");
            I18n.print("discount.submenu.deleteProduct");
            I18n.print("discount.submenu.addProduct");
            I18n.print("discount.submenu.editProduct");
            System.out.println("\n");
            
            int choice;
            do{
                choice = AppScanner.scanIntWithi18Message("msg.choice.enter");
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
                default: 
                    System.out.println(I18n.getMessage("msg.choice.invalid"));
                    break;
            }

        }while(AppScanner.confirm("\n" + I18n.getMessage("discount.confirm.keepEditing")));
        
    }
    
    private void handleEditDiscountName() throws Exception {
        String newName = AppScanner.scanStringWithMessage(I18n.getMessage("discount.scan.name.new"));
        this.editingDiscount.setName(newName);
        
        if (this.service.update(editingDiscount)) {
            I18n.printEntityMessage("discount", "entity.msg.updated");
        } else {
            I18n.printEntityMessage("discount", "entity.error.updateFailed");
        }
    }
    
    private void handleDeleteProduct() {
        ProductDiscount dProduct = this.scanExistedDiscountProduct();
        if (service.deleteDiscountProduct(dProduct)) {
            I18n.print("discount.msg.productDeleted");
            this.editingDiscount.getProductDiscounts().remove(dProduct);
        } else {
            I18n.print("discount.error.product.deleteFailed");
        }
    }
    
    private void handleAddProduct() {
        ProductDiscount dProduct = this.scanNewDiscountProduct();
        ProductDiscount oDisProduct = service.findOverlapDiscountProduct(dProduct);

        if (oDisProduct != null) {
            I18n.print(
                    "discount.msg.productOverlap",
                    oDisProduct.getProduct().getName(),
                    DateCommon.convertDateToString(oDisProduct.getStartDate(), Constant.DATE_TIME_SIMPLE_FORMAT),
                    DateCommon.convertDateToString(oDisProduct.getEndDate(), Constant.DATE_TIME_SIMPLE_FORMAT)
            );
            System.out.println("\n");
        } else if (service.createDiscountProduct(dProduct)) {
            I18n.print("discount.msg.productAdded");
            editingDiscount.getProductDiscounts().add(dProduct);
        } else {
            I18n.print("discount.error.product.addFailed");
        }
    }
    
    private void handleEditProduct() {
        ProductDiscount dProduct = this.scanExistedDiscountProduct();
        
        if(AppScanner.confirm(I18n.getMessage("discount.confirm.product.editDiscount"))) {
            dProduct.setDiscountPercentage(dProduct.scanDiscountPercentage());
        }
        
        if(AppScanner.confirm(I18n.getMessage("discount.confirm.product.editStartDatetime"))) {
            dProduct.setStartDate(dProduct.scanStartDate());
        }
        
        if(AppScanner.confirm(I18n.getMessage("discount.confirm.product.editEndDatetime"))) {
            dProduct.setEndDate(dProduct.scanEndDate());
        }
    }
    
    private ProductDiscount scanNewDiscountProduct() {
        
        Product product = null;
        ProductService productService = new ProductServiceImpl();
        
        try {
            while(product == null){
                
                int productId = AppScanner.scanIntWithi18Message("discount.scan.productId");
                
                if (this.editingDiscount.findProductDiscount(productId) != null) {
                    I18n.print("discount.error.product.existed");
                } else {
                    
                    product = productService.findById(productId);
                    
                    if (product == null) {
                        I18n.printEntityMessage("product", "entity.error.idNotFound");
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
            pDiscount = this.editingDiscount.findProductDiscount(AppScanner.scanIntWithi18Message("discount.scan.productId"));

            if (pDiscount == null) {
                I18n.print("discount.error.product.notExisted");
            }
        }
        
        return pDiscount;
    }

}
