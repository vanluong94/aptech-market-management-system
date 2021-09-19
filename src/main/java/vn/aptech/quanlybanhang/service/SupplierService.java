/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.service;

import java.util.List;
import vn.aptech.quanlybanhang.entities.Supplier;

/**
 *
 * @author VuxxLong
 */
public interface SupplierService extends BaseService<Supplier> {

    public List<Supplier> searchByName(String name) throws Exception;

}
