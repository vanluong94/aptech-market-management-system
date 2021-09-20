/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.dao;

import java.util.List;
import vn.aptech.quanlybanhang.entities.Discount;
import vn.aptech.quanlybanhang.entities.ProductDiscount;

/**
 *
 * @author Vu Duy Long <vuduylong1999@gmail.com>
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public interface DiscountDAO extends BaseDAO<Discount> {

    List<ProductDiscount> getDiscountProducts(Discount discount);

    boolean deleteDiscountProduct(ProductDiscount dProduct);

    boolean createDiscountProduct(ProductDiscount dProduct);

    ProductDiscount findOverlapDiscountProduct(ProductDiscount dProduct);
    
    boolean discountHasData(Discount discount);
}
