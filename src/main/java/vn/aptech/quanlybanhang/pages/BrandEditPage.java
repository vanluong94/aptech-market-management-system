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
public class BrandEditPage extends Page {

    @Override
    public void displayContent() {
        
        BrandService brandService = new BrandServiceImpl();
        
        boolean retry;
        
        do{
            
            retry = false;
            
            try{
                int id = AppScanner.scanIntWithMessage(I18n.getEntityMessage("brand", "entity.scan.id.edit"));
                Brand brand = brandService.findById(id);

                if(brand == null){
                    I18n.getEntityMessage("brand", "entity.error.idNotFound");
                    retry = true;
                }else{
                    System.out.println("\n");
                    I18n.print("brand.msg.update");
                    
                    String newName = AppScanner.scanStringWithMessage(I18n.getMessage("brand.scan.name"), true);
                    String newAdd = AppScanner.scanStringWithMessage(I18n.getMessage("brand.scan.address"), true);

                    if(newName.length() > 0){
                        brand.setBrandName(newName);
                    }

                    if(newAdd.length() > 0){
                        brand.setBrandAdd(newAdd);
                    }

                    brandService.update(brand);

                    I18n.printEntityMessage("brand", "entity.msg.updated");
                }
            }catch(InputInvalidException e){
                System.out.println(e.getMessage());
                retry = true;
            } catch (Exception ex) {
                Logger.getLogger(BrandEditPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }while(retry);
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("brand", "entity.title.edit");
    }
    
}
