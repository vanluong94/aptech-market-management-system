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
import vn.aptech.quanlybanhang.service.BrandService;
import vn.aptech.quanlybanhang.service.BrandServiceImpl;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author vanluong
 */
public class BrandDeletePage extends Page {

    @Override
    public void displayContent() {
        BrandService brandService = new BrandServiceImpl();
        
        boolean retry;
        
        do{
            
            retry = false;
            
            try{
                int id = AppScanner.scanIntWithMessage(I18n.getEntityMessage("brand", "entity.scan.id.delete"));
                Brand brand = brandService.findById(id);

                if (brand == null) {
                    I18n.getEntityMessage("brand", "entity.error.idNotFound");
                    retry = true;
                } else if( brandService.deleteById(brand.getBrandId()) ){
                    I18n.printEntityMessage("brand", "entity.msg.deleted");
                } else {
                    I18n.printEntityMessage("brand", "entity.error.deleteFailed");
                }
            }catch(InputInvalidException e){
                System.out.println(e.getMessage());
                retry = true;
            } catch (Exception ex) {
                Logger.getLogger(BrandDeletePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }while(retry);
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("brand", "entity.title.delete");
    }
    
}
