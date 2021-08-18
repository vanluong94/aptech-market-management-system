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
public class BrandEditPage extends Page {

    @Override
    public void displayContent() {
        
        BrandService brandService = new BrandServiceImpl();
        
        boolean retry;
        
        do{
            
            retry = false;
            
            try{
                int id = AppScanner.scanIntWithMessage("Nhập ID nhãn hàng cần sửa: ");
                Brand brand = brandService.findById(id);

                if(brand == null){
                    System.out.println("ID không tồn tại");
                    retry = true;
                }else{
                    System.out.println("\n\nNhập thông tin mới cho nhãn hàng, bỏ trống nếu giữ nguyên.");
                    String newName = AppScanner.scanStringWithMessage("[Tên Nhãn hàng]: ", true);
                    String newAdd = AppScanner.scanStringWithMessage("[Địa chỉ Nhãn hàng]: ", true);

                    if(newName.length() > 0){
                        brand.setBrandName(newName);
                    }

                    if(newAdd.length() > 0){
                        brand.setBrandAdd(newAdd);
                    }

                    brandService.update(brand);

                    System.out.println("Cập nhật thông tin thành công!");
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
        return "Sửa Nhãn hàng";
    }
    
}
