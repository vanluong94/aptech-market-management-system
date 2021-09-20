/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.exception.InputInvalidException;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.service.BrandService;
import vn.aptech.quanlybanhang.service.BrandServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class BrandDeletePage extends Page {

    @Override
    public void displayContent() {
        
        BrandService brandService = new BrandServiceImpl();
        ProductService productService = new ProductServiceImpl();
        
        while(true){
            
            try{
                int id = AppScanner.scanIntWithMessage(I18n.getEntityMessage("brand", "entity.scan.id.delete"));
                Brand brand = brandService.findById(id);

                if (brand == null) {
                    I18n.printEntityMessage("brand", "entity.error.idNotFound");
                    continue;
                } 
                
                System.out.println("");
                I18n.print("entity.msg.foundName", I18n.getMessage("brand.label.singular"), brand.getName());
                System.out.println("");
                
                if (AppScanner.confirm(I18n.getEntityMessage("brand", "entity.confirm.delete"))) {
                    if (productService.findFirstProductByBrand(brand) != null) {
                        I18n.print("brand.error.delete");
                    } else if (brandService.deleteById(brand.getId())) {
                        I18n.printEntityMessage("brand", "entity.msg.deleted");
                    } else {
                        I18n.printEntityMessage("brand", "entity.error.deleteFailed");
                    }
                }
                
                System.out.println("");
                if (!AppScanner.confirm(I18n.getEntityMessage("brand", "entity.confirm.deleteAnother"))) {
                    System.out.println("");
                    break;
                }
            }catch(InputInvalidException e){
                System.out.println(e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(BrandDeletePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("brand", "entity.title.delete");
    }
    
}
