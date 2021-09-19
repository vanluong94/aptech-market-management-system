/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import vn.aptech.quanlybanhang.entities.Order;
import vn.aptech.quanlybanhang.entities.OrderItem;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 *
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class OrderUI {
    
    private final int lineLength = 75;
    
    private final Order order;
    
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
        this.displayCenter(I18n.getMessage("order.receipt.addr", "19 Nguyễn Trãi, Thanh Xuân, Hà Nội"));
        this.displayCenter(I18n.getMessage("order.receipt.phone", "0912345678"));
        this.displayMargin();
    }
    
    public void displayTitle() {
        this.displayMargin();
        this.displayCenter(I18n.getMessage("order.receipt.title"));
        this.displayMargin();
    }
    
    public void displayProductsTable() {
        String[] headers = {
            I18n.getMessage("product.label.singular"), 
            I18n.getMessage("product.qty"),
            I18n.getMessage("product.price"),
            I18n.getMessage("product.price.sale"),
            I18n.getMessage("order.subtotal"),
        };
        
        List<Object[]> rows = new ArrayList<>();
        List<Integer> colsLength = Arrays.asList(26, 5, 13, 13, 17);
        List<String> colsAlign = Arrays.asList("left", "right", "right", "right", "right");
        
        for(OrderItem item : order.getOrderItems()) {
            Object[] row = {
                item.getProductName(),
                item.getQuantity(),
                item.getProductPriceString(),
                item.getProductFinalPriceString(),
                item.getTotalString()
            };
            rows.add(row);
        }
        
        TableUI table = new TableUI(headers, rows);
        table.setLineLength(lineLength);
        table.setColMaxLength(lineLength);
        table.setColumnsLength(colsLength);
        table.setColumnsAlign(colsAlign);
        table.display();
        
        System.out.println(String.format("| %-54s | %14s |", " ", " "));
        System.out.println(String.format("| %-54s | %14s |", I18n.getMessage("order.receipt.total"), this.order.getAmountString()));
        System.out.println(String.format("| %-54s | %14s |", " ", " "));
        this.displayBorder();
    }
    
    public void displayOrderDetail() {
        this.displayLine(String.format("%-11s: %s", I18n.getMessage("order.receipt.id"), order.getId()));
        this.displayLine(String.format("%-11s: %s", I18n.getMessage("order.receipt.timestamp"), order.getDatetimeString()));
        this.displayLine(String.format("%-11s: %s (Mã KH: %d)", I18n.getMessage("customer.label.singular"), order.getCustomer().getName(), order.getCustomer().getId()));
        this.displayLine(String.format("%-11s: %s (Mã Thu Ngân: %d)", I18n.getMessage("order.receipt.cashier"), order.getEmployee().getName(), order.getEmployee().getId()));
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
