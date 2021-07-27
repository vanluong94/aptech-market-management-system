# Phần mềm quản lý bán hàng

## Hướng dẫn
1. 

## Hướng dẫn sử dụng Base UI

### Class extends Menu
Menu là một `abstract class` base sử dụng để hiển thị và vận hành cho Menu.

#### Ví dụ
```java
public class BrandMenu extends Menu implements BaseMenu {

    private final String TITLE = "Quản lý Nhãn hàng";
    private final int[] CHOICES = {1,2,3,4,5,0}; // for validation purpose
    private final String[] MENU_ITEMS = {
        "1. Danh sách Nhãn hàng",
        "2. Thêm Nhãn hàng",
        "3. Sửa Nhãn hàng",
        "4. Xóa Nhãn hàng",
        "5. Tìm Nhãn hàng",
        "0. Thoát",
    };

    private final BrandService brandService;

    public BrandMenu() {
        this.brandService = new BrandServiceImpl();
        
        this.setMenuItems(this.MENU_ITEMS);
        this.setTitle(this.TITLE);
        this.setChoices(this.CHOICES);
    }

    public void handle(int choice) {
        try {
            switch (choice) {
                case 1:
                    this.handleDisplayAllBrands();
                    break;
                case 2:
                    HeaderUI.display("Thêm Nhãn Hàng");
                    this.handleAddBrand();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BrandMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
```

Các bước implement Menu class

1. Class Menu cần phải được set các property sau trong constructor: 

- `this.setMenuItems(String[] menuItems)`
- `this.setTitle(String title)` - Tên Menu
- `this.setChoices(int[] choices)` - Chuỗi các lựa chọn hợp lệ, sử dụng để validate giá trị int choice user nhập vào.

2. Implement abstract method `public void handle(int choice){}` từ parent class Menu.

- Thay vì các code để handle choice đều gộp chung vào switch - case trong `BaseMenu.start(Scanner scanner)`, method `Method.handle(int choice)` truyền vào validated choice user đã chọn, xử lý switch - case trong đây. Nếu code handle cho từng case quá dài, nên chia nhỏ vào các method riêng.

Cách lấy `Scanner scanner` trong class Menu có thể tham khảo tại [đây](#appscanner)
#### Kết quả
```
+------------------------------------------------+
|               Quản lý Nhãn hàng                |
+------------------------------------------------+
| 1. Danh sách Nhãn hàng                         |
| 2. Thêm Nhãn hàng                              |
| 3. Sửa Nhãn hàng                               |
| 4. Xóa Nhãn hàng                               |
| 5. Tìm Nhãn hàng                               |
| 0. Thoát                                       |
+------------------------------------------------+
Vui lòng nhập lựa chọn: askokaos
Giá trị không phù hợp.
Vui lòng nhập lựa chọn: 124981249
Lựa chọn không khả dụng
Vui lòng nhập lựa chọn: 1
Connecting to MySQL...
```

### TableUI
Base UI để hiển thị data thành Table

#### class TableUI
```java
public class TableUI {
    private List<Integer> columns = new ArrayList<>();
    private List<Object[]> rows = new ArrayList<>();

    public TableUI(String[] headers, List<Object[]> rows){}
    public void displayRowHeader(){}
}
```

#### Ví dụ
```java
// Query lấy List<Brand> từ database
List<Brand> brands = brandService.findAll();
List<Object[]> rows = new ArrayList<>();

/*
*   Chuyển List<Brand> sang List<Object[]> rows, 
*   mỗi row sẽ là 1 Array chứa các String data cho mỗi cột trong hàng 
*/
for (Brand brand : brands) {
    Object[] row = {
        brand.getBrandId(),
        brand.getBrandName(),
        brand.getBrandAdd()
    };

    rows.add(row);
}

// Set header cho table
String[] headers = {"ID", "Name", "Address"};

// Khởi tạo TableUI và gọi đến display() method để hiển thị
TableUI theTable = new TableUI(headers, rows);
theTable.display();
```

#### Kết quả
```
+----------------------------------------------------------------------------------------------------+
| ID  | Name                 | Address                                                               |
+----------------------------------------------------------------------------------------------------+
| 1   | P&G                  | 11/F, MPlaza, 39 Le Duan Blvd. District 1, Ho Chi Minh City Vietnam   |
| 2   | Unilever             | A2-3, Khu công nghiệp Tây Bắc Củ Chi, Huyện Củ Chi, Tp Hồ Chí Minh    |
| 3   | DH FOODS             | Lầu 9, 728-730 Võ Văn Kiệt, Phường 01, Quận 5, Thành phố Hồ Chí Minh  |
| 4   | Vinamilk             | Số 10, Đường Tân Trào, phường Tân Phú, quận 7, Tp. HCM                |
| 5   | Masan                | Số 39 Lê Duẩn, Phường Bến Nghé, Quận 1, Tp. Hồ Chí Minh, Việt Nam     |
| 6   | Trung Nguyên Legend  | 82-84 Bùi Thị Xuân, P. Bến Thành, Q.1, Tp Hồ Chí Minh                 |
+----------------------------------------------------------------------------------------------------+
```

### HeaderUI
Có thể sử dụng để hiển thị tiêu đề giao diện trang chức năng.

#### Ví dụ
```java
HeaderUI.display("Thêm Nhãn Hàng");
this.handleAddBrand();
```
#### Kết quả
```
+--------------------------------+
|         Thêm Nhãn Hàng         |
+--------------------------------+
[Tên Nhãn hàng]: ACECOOK
[Địa chỉ Nhãn hàng]: Khu Công nghiệp Tân Bình, Phường Tây Thạnh, Quận Tân Phú, Thành phố Hồ Chí Minh

Thêm Nhãn hàng thành công!
```


## Hướng dẫn sử dụng Utilities

### Class AppScanner
Việc phải truyền tham số Scanner vào mỗi hàm `BaseMenu.start()` khá là bất tiện, nhất là khi có class Menu, luồng xử lý choice đã chia nhỏ thành các method khác nhau để block code xử lý choice dễ nhìn hơn.
Nên mình thêm class `vn.aptech.quanlybanhang.utilities.AppScanner` để gọi đến 1 biến global Scanner xuyên suốt quá trình app chạy.

#### Lợi ích
- Gọi instance Scanner ở bất cứ đâu với method `AppScanner.getScanner()`, ví dụ: `AppScanner.getScanner().nextLine()`
- Các helper method sẽ giúp scan int hoặc scan String và catch mismatch exception khi user nhập sai format giá trị, repeat lại scan input

#### Methods

```java
public class AppScanner {
    public static Scanner getScanner() {}
    public static String scanStringWithMessage(String message, boolean canBeEmpty){}
    public static String scanStringWithMessage(String message){}
    public static int scanIntWithMessage(String message){}
}
```
- `Scanner getScanner()` - trả lại instance Scanner, sử dụng thay vì khởi tạo Scanner mỗi lần cần scan input.

- `int scanIntWithMessage(String message)` - scan int với message được in phía trước

Ví dụ:
```java
AppScanner.scanIntWithMessage("Nhập ID nhãn hàng cần sửa: ");
```

Kết quả:
```
Nhập ID nhãn hàng cần sửa: oeuwuetiwet
Giá trị không phù hợp.
Nhập ID nhãn hàng cần sửa: 
```

- `String scanStringWithMessage(String message, boolean canBeEmpty)` - scan string với message được in phía trước. Truyền vào param `boolean canBeEmpty` sẽ ngăn hoặc cho phép user nhập một String rỗng.
- `String scanStringWithMessage(String message)` - giống scan string bên trên, nhưng luôn ngăn user nhập một String rỗng.
