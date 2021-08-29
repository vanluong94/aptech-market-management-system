/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import vn.aptech.quanlybanhang.common.StringCommon;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class Product extends BaseEntity {

    private int id;
    private Brand brand;
    private Category category;
    private Employee employee;
    private Supplier supplier;
    private String name;
    private double price;
    private ProductDiscount discount;
    private int quantityInStock;
    private int unitsOnOrder;
    private List<OrderItem> orderItems;

    public Product() {
        // Init object
        this.brand = new Brand();
        this.supplier = new Supplier();
        this.employee = new Employee();
        this.category = new Category();
        this.orderItems = new ArrayList<>();
        this.discount = new ProductDiscount();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the brand
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @return the supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public ProductDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(ProductDiscount discount) {
        this.discount = discount;
    }
    
    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }
    
    public String getPriceString() {
        return StringCommon.convertDoubleToVND(price);
    }
    
    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the quantityInStock
     */
    public int getQuantityInStock() {
        return quantityInStock;
    }

    /**
     * @param quantityInStock the quantityInStock to set
     */
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    
    public double getDiscountPrice() {
        return this.getDiscount().getDiscountId() > 0 ? this.getPrice() * ( 100 - this.getDiscount().getDiscount()) / 100 : 0; 
    }
    
    /**
     *
     * @return
     */
    public int getUnitsOnOrder() {
        return unitsOnOrder;
    }

    /**
     *
     * @param unitsOnOrder
     */
    public void setUnitsOnOrder(int unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }
    
    public String getDiscountPriceString() {
        return StringCommon.convertDoubleToVND(this.getDiscountPrice());
    }
    
    /**
     * @return the orderItems
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public static TableUI toTable(List<Product> products) {

        // transfer data to table row
        List<Object[]> rows = new ArrayList<>();
        

        for (Product product : products) {

            List<Object> row = new ArrayList<>(Arrays.asList(
                    product.getId(),
                    product.getName(),
                    product.getPriceString(),
                    product.getQuantityInStock(),
                    product.getCategory().getCategoryName() != null ? product.getCategory().getCategoryName() : "",
                    product.getBrand().getBrandName() != null ? product.getBrand().getBrandName() : ""
            ));

            if (!AuthServiceImpl.getCurrentEmployee().isCashier()) {
                row.add(product.getSupplier().getName() != null ? product.getSupplier().getName() : "");
                row.add(product.getEmployee().getName() != null ? product.getEmployee().getName() : "");
            }

            rows.add(row.toArray());

        }
        
        List<String> headers = new ArrayList<>(Arrays.asList(
                "ID", "Tên SP", "Giá", "SL", "Danh mục", "Nhãn hàng"
        ));
        
        if(!AuthServiceImpl.getCurrentEmployee().isCashier()) {
            headers.add("Nhà cung cấp");
            headers.add("Người thêm");
        }

        return new TableUI(headers.toArray(new String[0]), rows);

    }

}
