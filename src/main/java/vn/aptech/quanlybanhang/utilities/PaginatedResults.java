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
        System.out.println(I18n.getMessage("pagination.page", this.getCurrentPage(), this.getTotalPages()));
        System.out.println("\n");
    }

    public void displayPaginationMenu() {

        if (this.needsPagination()) {
            if (this.hasNextPage()) {
                System.out.println("1. " + I18n.getMessage("pagination.next"));
            }

            if (this.hasPrevPage()) {
                System.out.println("2. " + I18n.getMessage("pagination.prev"));
            }

            System.out.println("3. " + I18n.getMessage("pagination.goPage"));
            System.out.println("-1. " + I18n.getMessage("app.goback"));
        }

    }

    public int scanGoPage() {

        int targetPage = 0;

        if (this.needsPagination()) {
            do {

                int choice = AppScanner.scanIntWithi18Message("msg.choice.enter");

                switch (choice) {
                    case GO_NEXT:
                        if (this.hasNextPage()) {
                            targetPage = this.getCurrentPage() + 1;
                        } else {
                            I18n.print("pagination.error.noNextPage");
                        }
                        break;
                    case GO_PREV:
                        if (this.hasPrevPage()) {
                            targetPage = this.getCurrentPage() - 1;
                        } else {
                            I18n.print("pagination.error.noPrevPage");
                        }
                        break;
                    case GO_PAGE:
                        targetPage = AppScanner.scanIntWithMessage(I18n.getMessage("pagination.scan.goPage"));
                        if (!this.isPageValid(targetPage)) {
                            I18n.print("pagination.error.pageNotFound", targetPage);
                            targetPage = 0;
                        }
                        break;
                    case GO_BACK:
                        targetPage = -1;
                        break;
                    default:
                        System.out.println(I18n.getMessage("msg.choice.invalid"));
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
