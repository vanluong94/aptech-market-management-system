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
                int id = AppScanner.scanIntWithMessage("Nhập ID nhãn hàng muốn xóa: ");
                Brand brand = brandService.findById(id);

                if (brand == null) {
                    System.out.println("ID không tồn tại");
                    retry = true;
                } else if( brandService.deleteById(brand.getBrandId()) ){
                    System.out.println("Xóa thành công!");
                } else {
                    System.out.println("Đã xảy ra lỗi");
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
        return "Xóa Nhãn hàng";
    }
    
}
