/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.util.ArrayList;
import java.util.List;
import vn.aptech.quanlybanhang.common.StringCommon;
import vn.aptech.quanlybanhang.entities.Customer;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.entities.OrderItem;
import vn.aptech.quanlybanhang.entities.Product;
import vn.aptech.quanlybanhang.service.AuthServiceImpl;
import vn.aptech.quanlybanhang.service.CustomerService;
import vn.aptech.quanlybanhang.service.CustomerServiceImpl;
import vn.aptech.quanlybanhang.service.OrderService;
import vn.aptech.quanlybanhang.service.OrderServiceImpl;
import vn.aptech.quanlybanhang.service.ProductService;
import vn.aptech.quanlybanhang.service.ProductServiceImpl;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;

/**
 *
 * @author Pham Vu Tan <C2101LM.PVTAN@APTECH.VN>
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class OrderCreateWithCustomerPage extends Page {

    private final ProductService productService;
    private final OrderService orderService;
    private final CustomerService customerService;

    public OrderCreateWithCustomerPage() {
        this.productService = new ProductServiceImpl();
        this.orderService = new OrderServiceImpl();
        this.customerService = new CustomerServiceImpl();
    }

    @Override
    public void displayContent() {
        try {
            int customerId = AppScanner.scanIntWithMessage("Nhap ID khach hang: ", false);
            Customer customer = customerService.findById(customerId);
            while (customer == null) {
                System.out.println("ID khong ton tai !");
                customerId = AppScanner.scanIntWithMessage("Nhap ID khach hang: ", false);
            }
            String choice;
            Order order = new Order();
            double amount = 0;
            while (true) {
                int productId = AppScanner.scanIntWithMessage("Nhập mã sản phẩm: ", false);
                OrderItem orderItem = new OrderItem();
                Product product = productService.findById(productId);
                if (product == null) {
                    System.out.println("Không tìm thấy sản phẩm trong kho. Vui lòng nhập lại!");
                    continue;
                }
                System.out.println("Tìm thấy sản phẩm '" + product.getName() + "'");
                orderItem.setProduct(product);
                int qty = AppScanner.scanIntWithMessage("Nhập số lượng: ", false);
                if (product.getQuantityInStock() < 1) {
                    System.out.println("Sản phẩm đã hết hàng!");
                    continue;
                }
                if (product.getQuantityInStock() < qty) {
                    System.out.println("Vượt quá số lượng tồn kho. Chỉ có thể mua tối đa là '" + orderItem.getProduct().getQuantityInStock() + "'");
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
                choice = AppScanner.scanStringWithMessage("Bạn có muốn thêm sản phẩm khác vào đơn hàng không? [y/N]: ", true);
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
            for (OrderItem od : order.getOrderItems()) {
                amount += od.getTotal();  // Tính tổng giá trị đơn hàng
            }
            order.setAmount(amount);
            order.setEmployee(AuthServiceImpl.getCurrentEmployee()); // Set nhân viên hiện tại đang đăng nhập
            order.setCustomer(customer);

            List<Object[]> rows = new ArrayList<>();
            System.out.println("\nGio hang cua khach hang " + customer.getName() + ": ");
            for (OrderItem orderItem : order.getOrderItems()) {
                Object[] row = {
                    orderItem.getProduct().getId(),
                    orderItem.getProductName(),
                    StringCommon.convertDoubleToVND(orderItem.getProductPrice()),
                    orderItem.getQuantity(),
                    StringCommon.convertDoubleToVND(orderItem.getProductPrice() * orderItem.getQuantity())
                };

                rows.add(row);
            }

            String[] headers = {"ID", "Sản phẩm", "Giá", "Số lượng", "Tạm tính"};

            TableUI theTable = new TableUI(headers, rows);
            theTable.display();
            System.out.println("Tổng ti�?n của hóa đơn: " + StringCommon.convertDoubleToVND(order.getAmount()));

            choice = AppScanner.scanStringWithMessage("Bạn có muốn lưu lại đơn hàng không? [y/N]: ", true);
            if ("y".equalsIgnoreCase(choice)) {
                if (orderService.create(order)) {
                    System.out.println("Tạo đơn hàng thành công!");
                    choice = AppScanner.scanStringWithMessage("Bạn có muốn xuất hóa đơn không? [y/N]: ", true);
                    if ("y".equalsIgnoreCase(choice)) {
                        String reportFile = orderService.requestReportXlsx(order);
                        if (reportFile != null) {
                            System.out.println("Hóa đơn được lưu tại đường dẫn: " + reportFile);
                        } else {
                            System.out.println("Đã xảy ra lỗi khi xuất hóa đơn");
                        }
                    }
                } else {
                    System.out.println("�?ã xảy ra lỗi khi tạo đơn hàng");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getTitle() {
        return "Tao don hang cho khach hang co the";
    }

}
