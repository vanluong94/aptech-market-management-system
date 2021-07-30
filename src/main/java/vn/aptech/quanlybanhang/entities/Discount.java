/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 */
public class Discount extends BaseEntity {

    private int id;
    private String name;

    public Discount() {
    }

    public Discount(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Discount { " + "DiscountId = " + id + ", DiscountName = " + name + " } ";
    }

}
