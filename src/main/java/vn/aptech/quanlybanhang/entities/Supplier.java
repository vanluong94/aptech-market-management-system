/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.entities;

/**
 *
 * @author VuxxLong
 */
public class Supplier {
    private int supplierId;
    private String supplierName;
    private String supplierAddress;
    
    public Supplier(){
        
    }

    public Supplier(String supplierName, String supplierAddress) {
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
    }

    /**
     * @return the supplierId
     */
    public int getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @return the supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @return the supplierAddress
     */
    public String getSupplierAddress() {
        return supplierAddress;
    }

    /**
     * @param supplierAddress the supplierAddress to set
     */
    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    @Override
    public String toString() {
        return "Supplier { " + "ID : " + supplierId + " || SupplierName = " + supplierName + " || SupplierAdress = " + supplierAddress + " }";
    }
    
    
    
    
    
}
