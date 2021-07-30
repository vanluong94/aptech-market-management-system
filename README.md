# Phần mềm quản lý bán hàng

## Hướng dẫn
1. 

## Hướng dẫn sử dụng Base UI

### Giới thiệu sơ
Giao diện của app sẽ được hình thành xoay quanh 2 thực thể là `Menu` và `Page`, cả 2 đều là `abstract class extends MenuItem`
![Cấu trúc Menu Entity](https://i.imgur.com/m02U7Cr.png)

### Xây dựng Menu
Menu là một `abstract class` sử dụng để hiển thị và vận hành cho Menu.

#### Ví dụ
```java
public class BrandMenu extends Menu {

    @Override
    protected LinkedHashMap<Integer, MenuItem> registerMenuItems() {
        LinkedHashMap<Integer, MenuItem> menuItems = new LinkedHashMap<>();
        
        menuItems.put(1, new BrandListingPage());
        menuItems.put(2, new BrandCreatePage());
        menuItems.put(3, new BrandEditPage());
        menuItems.put(4, new BrandDeletePage());
        menuItems.put(5, new BrandSearchPage());
        
        menuItems.put(0, new ExitMenuItem());
        menuItems.put(-1, new GoBackMenuItem());
        
        return menuItems;
    }

    @Override
    protected String registerMenuTitle() {
        return "Quản lý Nhãn hàng";
    }
}
```

#### Implements các abstract methods
- ` String registerMenuTitle()` - Required - Trả lại chuỗi cho Tiêu đề của Menu. (Tiêu đề này xuất hiện trên Title, Breadcrumb, và parent Menu).
- `LinkedHashMap<Integer, MenuItem> registerMenuItems()` - Required - Khởi tạo và trả lại `LinkedHashMap<Integer, MenuItem>` chứa các cặp `<Key,Value>` tương ứng với số lựa chọn cho MenuItem và instance của MenuItem (có thể là `Page` hoặc `Menu`) đó.
- `String getBreadcrumbPathName()` - Optional - Sử dụng tên khác để hiển thị trên Breadcrumb

#### Kết quả
```
+------------------------------------------------+
|            Menu > Quản lý Nhãn hàng            |
+------------------------------------------------+
|                                                |
|               Quản lý Nhãn hàng                |
|                                                |
+------------------------------------------------+
| 1. Danh Sach Nhan Hang                         |
| 2. Them Nhan Hang                              |
| 3. Sua Nhan Hang                               |
| 4. Xoa Nhan Hang                               |
| 5. Tim Nhan Hang                               |
| 0. Thoat                                       |
| -1. Quay lai                                   |
+------------------------------------------------+
Vui lòng nhập lựa chọn: kokoeg
Giá trị không phù hợp.
Vui lòng nhập lựa chọn: 1
Connecting to MySQL...
```

### Xây dựng Page
`Page` là một `MenuItem`. Khác với `Menu`, `Page` chỉ để hiển thị nội dung.

```java
public class BrandListingPage extends Page {

    @Override
    public void displayContent() {
        // Code hiển thị bảng danh sách các nhãn hàng tại đây
    }

    @Override
    public String getTitle() {
        return "Danh Sach Nhan Hang";
    }
    
}
```
#### Implements các abstract methods
- `void displayContent()` - Required - Nơi đặt code hiển thị và xử lý các tính năng trong Page.
- `String getTitle()` - Required - Trả lại Tiêu đề cho Page (tiêu đề hiển thị ở Header, Breadcrumb và parent Menu).
- `String getBreadcrumbPathName()` - Optional - Sử dụng tên khác để hiển thị trên Breadcrumb

#### Kết quả
```
+----------------------------------------------------------------+
|         Menu > Quản lý Nhãn hàng > Danh Sach Nhan Hang         |
+----------------------------------------------------------------+
|                                                                |
|                      Danh Sach Nhan Hang                       |
|                                                                |
+----------------------------------------------------------------+
...Connecting to MySQL
...Connected to database.
+----------------------------------------------------------------------------------------------------+
| ID  | Name                 | Address                                                               |
+----------------------------------------------------------------------------------------------------+
| 1   | P&G                  | 11/F, MPlaza, 39 Le Duan Blvd. District 1, Ho Chi Minh City Vietnam   |
| 2   | Unilever             | A2-3, Khu công nghiệp Tây Bắc Củ Chi, Huyện Củ Chi, Tp Hồ Chí Minh    |
| 3   | DH FOODS             | Lầu 9, 728-730 Võ Văn Kiệt, Phường 01, Quận 5, Thành phố Hồ Chí Minh  |
| 4   | Vinamilk             | Số 10, Đường Tân Trào, phường Tân Phú, quận 7, Tp. HCM                |
| 5   | Masan                | Số 39 Lê Duẩn, Phường Bến Nghé, Quận 1, Tp. Hồ Chí Minh, Việt Nam     |
| 6   | Trung Nguyên Legend  | 82-84 Bùi Thị Xuân, P. Bến Thành, Q.1, Tp Hồ Chí Minh                 |
| 7   | ACECOOK              | Lô số II-3,Đường số 11,Nhóm CN II, Khu Công nghiệp Tân Bình, Phườn... |
+----------------------------------------------------------------------------------------------------+
Nhan phim [enter] de quay tro lai:...  
```

#### Lưu ý
Phần `Nhan phim [enter] de quay tro lai:...` ở cuối Page sẽ được tự động xử lý, không cần thêm code cho tính năng này.

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

#### Các kiểu sử dụng TableUI khác
Sử dụng TableUI để hiển thị chi tiết nhân viên

Ví dụ 1:
```java
System.out.println("====== Chi tiet nhan vien ======");

List<Object[]> rows = new ArrayList<>();
String[] headers = {"", ""};


Object[] rowName = {"Ho Ten", name};
Object[] rowAddr = {"Dia Chi", address};
Object[] rowPhone = {"So Dien Thoai", phone};
Object[] rowDept = {"Chuc Vu", department};
Object[] rowID = {"ID", employeeId};
Object[] rowUsername = {"Username", userName};
Object[] rowPassword = {"Password", password};

Collections.addAll(rows, rowName, rowAddr, rowPhone, rowDept, rowID, rowUsername, rowPassword );

TableUI theTable = new TableUI(headers, rows);
theTable.displayBorder();
theTable.displayRows();
theTable.displayBorder();
```

```
====== Chi tiet nhan vien ======
+----------------------------------+
| Ho Ten         | Nguyen Van A    |
| Dia Chi        | 19 Nguyen Trai  |
| So Dien Thoai  | 0123456789      |
| Chuc Vu        | ROLE_ADMIN      |
| ID             | 1               |
| Username       | admin           |
| Password       | admin           |
+----------------------------------+
```

Ví dụ 2:

```java
System.out.println("====== Chi tiet nhan vien ======");

List<Object[]> rows = new ArrayList<>();
String[] headers = {"", ""};


Object[] rowName = {"Ho Ten", name};
Object[] rowAddr = {"Dia Chi", address};
Object[] rowPhone = {"So Dien Thoai", phone};
Object[] rowDept = {"Chuc Vu", department};
Object[] rowID = {"ID", employeeId};
Object[] rowUsername = {"Username", userName};
Object[] rowPassword = {"Password", password};

Collections.addAll(rows, rowName, rowAddr, rowPhone, rowDept, rowID, rowUsername, rowPassword );

TableUI theTable = new TableUI(headers, rows);

theTable.displayBorder();
for (Object[] row : theTable.getRows()) {
    theTable.displayRow(row);
    theTable.displayBorder();
}
```

```
====== Chi tiet nhan vien ======
+----------------------------------+
| Ho Ten         | Nguyen Van A    |
+----------------------------------+
| Dia Chi        | 19 Nguyen Trai  |
+----------------------------------+
| So Dien Thoai  | 0123456789      |
+----------------------------------+
| Chuc Vu        | ROLE_ADMIN      |
+----------------------------------+
| ID             | 1               |
+----------------------------------+
| Username       | admin           |
+----------------------------------+
| Password       | admin           |
+----------------------------------+
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
