/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

import java.util.Date;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class BaseEntity {
    protected Date createdAt;
    protected Date updatedAt;

    public BaseEntity() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
