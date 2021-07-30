/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Brand;
import vn.aptech.quanlybanhang.exception.InputInvalidException;
import vn.aptech.quanlybanhang.service.BrandService;
import vn.aptech.quanlybanhang.service.BrandServiceImpl;
import vn.aptech.quanlybanhang.ui.HeaderUI;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;

public class BrandMenu extends Menu implements BaseMenu {

    private final String TITLE = "Quản lý Nhãn hàng";
    private final int[] CHOICES = {1,2,3,4,5,0}; // for validation purpose
    private final String[] MENU_ITEMS = {
        "1. Danh sách Nhãn hàng",
        "2. Thêm Nhãn hàng",
        "3. Sửa Nhãn hàng",
        "4. Xóa Nhãn hàng",
        "5. Tìm Nhãn hàng",
        "0. Thoát",
    };
    
    private final BrandService brandService;

    public BrandMenu() {
        
        this.brandService = new BrandServiceImpl();
        
        this.setMenuItems(this.MENU_ITEMS);
        this.setTitle(this.TITLE);
        this.setChoices(this.CHOICES);
        
    }

    public void handle(int choice) {
        try {
            
            switch (choice) {
                case 1:
                    this.handleDisplayAllBrands();
                    break;
                case 2:
                    HeaderUI.display("Thêm Nhãn Hàng");
                    this.handleAddBrand();
                    break;
                case 3:
                    HeaderUI.display("Sửa Nhãn Hàng");
                    this.handleEditBrand();
                    break;
                case 4:
                    this.handleDeleteBrand();
                    break;
                case 5:
                    this.handleSearchBrand();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BrandMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleDisplayAllBrands() throws SQLException {
        
        List<Brand> brands = brandService.findAll();
        List<Object[]> rows = new ArrayList<Object[]>();

        // transfer data to table row
        for (Brand brand : brands) {
            Object[] row = {
                brand.getBrandId(),
                brand.getBrandName(),
                brand.getBrandAdd()
            };

            rows.add(row);
        }

        String[] headers = {"ID", "Name", "Address"};

        TableUI theTable = new TableUI(headers, rows);
        theTable.display();

    }

    public void handleAddBrand() throws SQLException, Exception {
        
        Brand brand = new Brand();

        brand.setBrandName(AppScanner.scanStringWithMessage("[Tên Nhãn hàng]: "));
        brand.setBrandAdd(AppScanner.scanStringWithMessage("[�?ịa chỉ Nhãn hàng]: "));

        this.brandService.create(brand);

        System.out.println("\nThêm Nhãn hàng thành công");
        
    }

    public void handleEditBrand() throws SQLException, ClassNotFoundException, Exception {
        
        this.handleSearchBrand();
        
        boolean retry;
        
        do{
            
            retry = false;
            
            try{
                int id = AppScanner.scanIntWithMessage("Nhập ID nhãn hàng cần sửa: ");
                Brand brand = this.brandService.findById(id);

                if(brand == null){
                    System.out.println("ID không tồn tại");
                    retry = true;
                }else{
                    System.out.println("\n\nNhập thông tin mới cho nhãn hàng, b�? trống nếu giữ nguyên.");
                    String newName = AppScanner.scanStringWithMessage("[Tên Nhãn hàng]: ", true);
                    String newAdd = AppScanner.scanStringWithMessage("[�?ịa chỉ Nhãn hàng]: ", true);

                    if(newName.length() > 0){
                        brand.setBrandName(newName);
                    }

                    if(newAdd.length() > 0){
                        brand.setBrandAdd(newAdd);
                    }

                    this.brandService.update(brand);

                    System.out.println("Cập nhật thông tin thành công!");
                }
            }catch(InputInvalidException e){
                System.out.println(e.getMessage());
                retry = true;
            }
            
            
        }while(retry);
        
    }

    public void handleDeleteBrand() throws SQLException, ClassNotFoundException, Exception {
        
        this.handleSearchBrand();
        
        boolean retry;
        
        do{
            
            retry = false;
            
            try{
                int id = AppScanner.scanIntWithMessage("Nhập ID nhãn hàng muốn xóa: ");
                Brand brand = this.brandService.findById(id);

                if (brand == null) {
                    System.out.println("ID không tồn tại");
                    retry = true;
                } else {
                    this.brandService.deleteById(brand.getBrandId());
                }
            }catch(InputInvalidException e){
                System.out.println(e.getMessage());
                retry = true;
            }
            
            
        }while(retry);
        
    }
    
    public void handleSearchBrand() throws SQLException, ClassNotFoundException{
        boolean repeat = false;
        
        do{
            System.out.println("\n\n");
            String search = AppScanner.scanStringWithMessage("Tìm nhãn hàng cần sửa theo tên: ");
            System.out.println("\n\n");
            
            List<Brand> brands = this.brandService.searchByName(search);
            

            if (!brands.isEmpty()) {
                
                System.out.println( String.format( "Các nhãn hàng được tìm thấy dựa theo tên \"%s\"", search ) );
                
                List<Object[]> rows = new ArrayList<Object[]>();

                // transfer data to table row
                for (Brand brand : brands) {
                    Object[] row = {
                        brand.getBrandId(),
                        brand.getBrandName(),
                        brand.getBrandAdd()
                    };

                    rows.add(row);
                }

                String[] headers = {"ID", "Name", "Address"};

                TableUI theTable = new TableUI(headers, rows);
                theTable.display();
                
            } else {
                System.out.println("Không tìm thấy nhãn hàng nào.");
                repeat = true;
            }
        }while(repeat);
    }
}
