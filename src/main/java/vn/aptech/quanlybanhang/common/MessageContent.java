/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.common;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public interface MessageContent {

    String OBJECT_NOT_FOUND = "Không tìm thấy đối tượng {0}";
    String OBJECT_INACTIVE = "Đối tượng {0} không ở trạng thái hoạt động";
    String OBJECT_NOT_FOUND_BY_FIELD_VALUE = "Không tìm thấy đối tượng {0} với giá trị của {1} là {2}";
    String OBJECT_NOT_EXISTS = "Đối tượng {0} không tồn tại";
    String OBJECT_NOT_EXISTS_BY_FIELD_VALUE = "Đối tượng {0} với giá trị của {1} là {2} không tồn tại";
    String OBJECT_IS_EXISTS = "Đối tượng {0} đã tồn tại";
    String OBJECT_IS_EXISTS_BY_FIELD_VALUE = "Đối tượng {0} với giá trị của {1} là {2} đã tồn tại";
    String MISSING_PARAM = "Thiếu dữ liệu đầu vào bắt buộc {0}";
}
