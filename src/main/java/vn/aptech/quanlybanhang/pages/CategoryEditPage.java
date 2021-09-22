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
 *
 * @author vanluong
 */
public class CategoryEditPage extends Page {

    @Override
    public void displayContent() {

        try {

            Category theCat;

            do {
                int id = AppScanner.scanIntWithMessage(I18n.getEntityMessage("category", "entity.scan.id.edit"));

                CategoryService categoryService = new CategoryServiceImpl();
                theCat = categoryService.findById(id);

                if (theCat != null) {

                    HeaderUI.display(I18n.getEntityMessage("category", "category.information"));
                    I18n.print("entity.msg.foundName", I18n.getMessage("category.label.singular"), theCat.getName());

                    HeaderUI.display(I18n.getMessage("category.msg.update"));
                    String newName = AppScanner.scanStringWithi18Message("category.scan.newName");
                    theCat.setName(newName);

                    System.out.println(""); //margin line
                    if (categoryService.update(theCat)) {
                        I18n.printEntityMessage("category", "entity.msg.updated");
                    } else {
                        I18n.printEntityMessage("category", "entity.error.updateFailed");
                    }
                } else {
                    I18n.printEntityMessage("category", "entity.error.idNotFound");
                }

            } while (theCat == null);

        } catch (Exception ex) {
            Logger.getLogger(CategoryEditPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("category", "entity.title.edit");
    }

}
