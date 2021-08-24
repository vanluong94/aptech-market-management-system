/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.entities.OrderItem;

/**
 *
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class OrderUI {
    
    private final int lineLength = 60;
    
    private Order order;
    
    public OrderUI(Order order){
        this.order = order;
    }
    
    public void display(){
        
        this.displayBorder();
        this.displayHeader();
        this.displayBorder();
        this.displayTitle();
        this.displayProductsTable();
        this.displayOrderDetail();
        this.displayBorder();
    }
    
    public void displayHeader() {
        this.displayMargin();
        this.displayCenter("SUPERMAKERT");
        this.displayCenter("Addr: 19 Nguyễn Trãi, Thanh Xuân, Hà Nội");
        this.displayCenter("SDT: 0912345678");
        this.displayMargin();
    }
    
    public void displayTitle() {
        this.displayMargin();
        this.displayCenter("HÓA ĐƠN THANH TOÁN");
        this.displayMargin();
    }
    
    public void displayProductsTable() {
        String[] headers = {"Sản Phẩm", "SL", "Đơn Giá", "Thành Tiền"};
        List<Object[]> rows = new ArrayList<>();
        List<Integer> colsLength = Arrays.asList(26, 5, 13, 15);
        List<String> colsAlign = Arrays.asList("left", "right", "right", "right");
        
        for(OrderItem item : order.getOrderItems()) {
            Object[] row = {
                item.getProductName(),
                item.getQuantity(),
                item.getProductPriceString(),
                item.getTotalPriceString()
            };
            rows.add(row);
        }
        
        TableUI table = new TableUI(headers, rows);
        table.setLineLength(lineLength);
        table.setColMaxLength(lineLength);
        table.setColumnsLength(colsLength);
        table.setColumnsAlign(colsAlign);
        table.display();
        
        System.out.println(String.format("| %-41s | %12s |", " ", " "));
        System.out.println(String.format("| %-41s | %12s |", "TỔNG TIỀN", this.order.getAmountString()));
        System.out.println(String.format("| %-41s | %12s |", " ", " "));
        this.displayBorder();
    }
    
    public void displayOrderDetail() {
        this.displayLine(String.format("%-11s: %s", "Mã hóa đơn", order.getId()));
        this.displayLine(String.format("%-11s: %s", "Thời gian", order.getDatetimeString()));
        this.displayLine(String.format("%-11s: %s (Mã KH: %d)", "Khách hàng", order.getCustomer().getName(), order.getCustomer().getId()));
        this.displayLine(String.format("%-11s: %s (Mã Thu Ngân: %d)", "Thu ngân", order.getEmployee().getName(), order.getEmployee().getEmployeeId()));
    }
    
    public void displayBorder() {
        HelperUI.displayBorder(lineLength);
    }
    
    public void displayMargin() {
        HelperUI.displayBorderLineLeft("", lineLength);
    }
    
    public void displayCenter(String content){
        HelperUI.displayBorderLineCenter(content, lineLength);
    }
    
    public void displayLine(String content){
        HelperUI.displayBorderLineLeft(content, lineLength);
    }
}
