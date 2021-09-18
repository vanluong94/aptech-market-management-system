/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.service.CategoryService;
import vn.aptech.quanlybanhang.service.CategoryServiceImpl;
import vn.aptech.quanlybanhang.ui.HeaderUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

/**
 * @author vanluong
 */
public class CategoryDeletePage extends Page {

    @Override
    public void displayContent() {
        try {

            Category theCat;

            do {
                System.out.println("\n");
                int id = AppScanner.scanIntWithMessage(I18n.getEntityMessage("category", "entity.scan.id.delete"));

                CategoryService categoryService = new CategoryServiceImpl();
                theCat = categoryService.findById(id);

                if (theCat != null) {

                    HeaderUI.display(I18n.getEntityMessage("category", "entity.title.detail"));
                    System.out.println(theCat.toString());

                    String confirm = AppScanner.scanStringWithMessage(I18n.getEntityMessage("category", "entity.confirm.delete"));

                    if (confirm.toLowerCase().equals("y")) {
                        System.out.println(""); //margin line
                        if (categoryService.deleteById(theCat.getCategoryId())) {
                            I18n.printEntityMessage("category", "entity.msg.deleted");
                        } else {
                            I18n.printEntityMessage("category", "entity.error.deleteFailed");
                        }
                    } else {
                        theCat = null; // let user select category again
                    }
                } else {
                    System.out.println("ID Danh mục không tồn tại");
                }

            } while (theCat == null);

        } catch (Exception ex) {
            Logger.getLogger(CategoryEditPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("category", "entity.title.delete");
    }

}
