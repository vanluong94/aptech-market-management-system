/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

/**
 *
 * @author VuxxLong
 */
public class Supplier extends BaseEntity {

    private int id;
    private String name;
    private String address;

    public Supplier() {

    }

    public Supplier(String supplierName, String supplierAddress) {
        this.name = supplierName;
        this.address = supplierAddress;
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

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    

}
