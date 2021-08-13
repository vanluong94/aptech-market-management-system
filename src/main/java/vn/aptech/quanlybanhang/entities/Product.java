/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public class Product extends BaseEntity {

    private int id;
    private Brand brand;
    private Category category;
    private Employee employee;
    private Supplier supplier;
    private String name;
    private double price;
    private int quantityInStock;
    
    public Product() {
        // Init object
        this.brand = new Brand();
        this.supplier = new Supplier();
        this.employee = new Employee();
        this.category = new Category();
    }

    /**
     *
     * @param brand
     * @param category
     * @param employee
     * @param name
     * @param price
     * @param quantityInStock
     */
    public Product(Brand brand, Category category, Employee employee, String name, double price, int quantityInStock) {
        this.brand = brand;
        this.category = category;
        this.employee = employee;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    /**
     *
     * @param id
     * @param brand
     * @param category
     * @param employee
     * @param name
     * @param price
     * @param quantityInStock
     */
    public Product(int id, Brand brand, Category category, Employee employee, String name, double price, int quantityInStock) {
        this.id = id;
        this.brand = brand;
        this.category = category;
        this.employee = employee;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     *
     * @param brand
     */
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    /**
     *
     * @return
     */
    public Category getCategory() {
        return category;
    }

    /**
     *
     * @param category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     *
     * @return
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     *
     * @param employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public int getQuantityInStock() {
        return quantityInStock;
    }

    /**
     *
     * @param quantityInStock
     */
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "Product { " + "ID : " + id + " || Brand ID : " + getBrand() + " || Category ID : " + getCategory() + " || Employee ID : " + getEmployee() + " || Product Name : " + name + " || Product Price : " + price + " || Product Stock : " + quantityInStock + " } ";
    }

}