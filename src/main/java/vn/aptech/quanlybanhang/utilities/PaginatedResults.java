/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.utilities;

import java.util.List;

/**
 *
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class PaginatedResults<T> {

    private final int GO_NEXT = 1;
    private final int GO_PREV = 2;
    private final int GO_PAGE = 3;
    private final int GO_BACK = -1;

    private List<T> results;
    private int perPage;
    private int totalItems;
    private int currentPage;

    public PaginatedResults(int currentPage, int perPage) {
        this.currentPage = currentPage;
        this.perPage = perPage;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> items) {
        this.results = items;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getOffset() {
        return (this.getCurrentPage() - 1) * this.getPerPage();
    }

    public int getTotalPages() {
        int totalPages = Math.round(this.getTotalItems() / this.getPerPage());
        totalPages += this.getTotalItems() % this.getPerPage() > 0 ? 1 : 0;
        return totalPages;
    }

    public void displayPagination() {
        System.out.println(String.format("Trang: %d/%d\n", this.getCurrentPage(), this.getTotalPages()));
    }

    public void displayPaginationMenu() {

        if (this.needsPagination()) {
            if (this.hasNextPage()) {
                System.out.println("1. Trang sau");
            }

            if (this.hasPrevPage()) {
                System.out.println("2. Trang truoc");
            }

            System.out.println("3. Chọn trang");
            System.out.println("-1. Quay lai");
        }

    }

    public int scanGoPage() {

        int targetPage = 0;

        if (this.needsPagination()) {
            do {

                int choice = AppScanner.scanIntWithMessage("Vui lòng nhập lựa chọn: ");

                switch (choice) {
                    case GO_NEXT:
                        if (this.hasNextPage()) {
                            targetPage = this.getCurrentPage() + 1;
                        } else {
                            System.out.println("Không có trang sau");
                        }
                        break;
                    case GO_PREV:
                        if (this.hasPrevPage()) {
                            targetPage = this.getCurrentPage() - 1;
                        } else {
                            System.out.println("Không có trang trước");
                        }
                        break;
                    case GO_PAGE:
                        targetPage = AppScanner.scanIntWithMessage("Đi tới trang: ");
                        if (!this.isPageValid(targetPage)) {
                            System.out.println("Không tồn tại trang này");
                            targetPage = 0;
                        }
                        break;
                    case GO_BACK:
                        targetPage = -1;
                        break;
                    default:
                        targetPage = 0;
                        break;
                }
            } while (targetPage == 0);
        }

        return targetPage;

    }

    public boolean needsPagination() {
        return this.getTotalPages() > 1;
    }

    public boolean hasNextPage() {
        return this.getCurrentPage() < this.getTotalPages();
    }

    public boolean hasPrevPage() {
        return this.getCurrentPage() > 1;
    }

    public boolean isPageValid(int checkPage) {
        return checkPage >= 1 && checkPage <= this.getTotalPages();
    }
    
    public static String parseCountSQL(String selectSQL) {
        return selectSQL
                .replaceFirst("SELECT((\\n|.)+)FROM", "SELECT COUNT(*) FROM")
                .replaceFirst("LIMIT (\\n|.+)", "");
    }
}
