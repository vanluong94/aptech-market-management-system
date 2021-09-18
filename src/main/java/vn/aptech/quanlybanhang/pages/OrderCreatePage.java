/*
 *  Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.common.StringCommon;
import vn.aptech.quanlybanhang.constant.Constant;
import vn.aptech.quanlybanhang.dao.BrandDAOImpl;
import vn.aptech.quanlybanhang.entities.Customer;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.entities.OrderItem;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.service.OrderService;
import vn.aptech.quanlybanhang.service.OrderServiceImpl;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;
import vn.aptech.quanlybanhang.utilities.FileUtils;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class OrderCreatePage extends Page {

    private final ProductService productService;
    private OrderService orderService;

    public OrderCreatePage() {
        this.productService = new ProductServiceImpl();
        this.orderService = new OrderServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            String choice;
            Order order = new Order();
            double amount = 0;
            while (true) {
                int productId = AppScanner.scanIntWithi18Message("order.scan.product.id");
                OrderItem orderItem = new OrderItem();
                Product product = productService.findById(productId);
                if (product == null) {
                    I18n.print("order.error.product.notFound");
                    continue;
                }
                
                I18n.print("order.msg.product.foundOne", product.getName());
                orderItem.setProduct(product);
                
                int qty = AppScanner.scanIntWithi18Message("order.scan.product.qty");
                if (product.getQuantityInStock() < 1) {
                    I18n.print("order.error.product.outOfStock");
                    continue;
                }
                if (product.getQuantityInStock() < qty) {
                    I18n.print("order.error.product.insufStock", orderItem.getProduct().getQuantityInStock());
                    qty = product.getQuantityInStock(); // Lấy số lượng tồn kho
                }
                
                // Kiểm tra xem SP đã tồn tại trong Order hay chưa
                boolean alreadyExist = false;
                for (OrderItem od : order.getOrderItems()) {
                    if (od.getProduct().getId() == productId) {
                        qty += od.getQuantity();
                        alreadyExist = true;
                        // Nếu tồn tại thì công thêm số lượng
                        od.getProduct().setQuantityInStock(qty);
                        od.setQuantity(qty);
                    }
                }
                if (!alreadyExist) {
                    // Nếu chưa tồn tại thì thêm mới SP
                    orderItem.getProduct().setQuantityInStock(qty);
                    orderItem.setQuantity(qty);
                    orderItem.setProductName(product.getName());
                    orderItem.setProductPrice(product.getPrice());
                    orderItem.setDiscountPrice(product.getDiscountPrice());
                    orderItem.setDiscount(product.getDiscount());
                    
                    order.getOrderItems().add(orderItem);
                }
                // Set nốt discount va discount Price
                choice = AppScanner.scanStringWithMessage(I18n.getMessage("order.confirm.addAnotherProduct"), true);

                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
            for (OrderItem od : order.getOrderItems()) {
                amount += od.getTotal(); // Tính tổng giá trị đơn hàng
            }
            order.setAmount(amount);
            order.setEmployee(AuthServiceImpl.getCurrentEmployee()); // Set nhân viên hiện tại đang đăng nhập
            order.setCustomer(new Customer(-1)); // Không có khách hàng nào

            List<Object[]> rows = new ArrayList<>();

            I18n.print("order.msg.detail");
            for (OrderItem orderItem : order.getOrderItems()) {
                Object[] row = {
                    orderItem.getProduct().getId(),
                    orderItem.getProductName(),
                    orderItem.getProductPriceString(),
                    orderItem.getProductFinalPriceString(),
                    orderItem.getQuantity(),
                    orderItem.getTotalString()
                };

                rows.add(row);
            }

            String[] headers = {
                "ID", 
                I18n.getMessage("product.label.singular"), 
                I18n.getMessage("product.price"),
                I18n.getMessage("product.price.sale"),
                I18n.getMessage("product.qty"),
                I18n.getMessage("order.subtotal")
            };

            TableUI theTable = new TableUI(headers, rows);
            theTable.display();
            
            System.out.println(I18n.getMessage("order.total") + ": " + StringCommon.convertDoubleToVND(order.getAmount()));

            choice = AppScanner.scanStringWithMessage(I18n.getMessage("order.confirm.saveOrder"), true);
            if ("y".equalsIgnoreCase(choice)) {
                if (orderService.create(order)) {
                    I18n.printEntityMessage("order", "entity.msg.created");

                    choice = AppScanner.scanStringWithMessage(I18n.getMessage("order.export.confirm"), true);
                    if ("y".equalsIgnoreCase(choice)) {
                        String reportFile = orderService.requestReportXlsx(order);
                        if (reportFile != null) {
                            I18n.print("order.export.path", reportFile);
                        } else {
                            I18n.print("order.export.error");
                        }
                    }
                } else {
                    I18n.printEntityMessage("order", "entity.error.createFailed");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(OrderCreatePage.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("order", "entity.title.create");
    }

}
