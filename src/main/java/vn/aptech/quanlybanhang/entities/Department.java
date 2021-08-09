/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.entities;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public enum Department {
    ROLE_ADMIN(1),
    ROLE_EMPLOYEE_CASHER(2),
    ROLE_EMPLOYEE_INVENTORY(3);
    private final int value;

    private Department(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static Department fromInt(int i) {
        for (Department d : Department.values()) {
            if (d.getValue() == i) {
                return d;
            }
        }
        return null;
    }
}
