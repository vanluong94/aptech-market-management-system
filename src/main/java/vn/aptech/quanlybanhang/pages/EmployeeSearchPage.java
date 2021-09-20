/*
 * Do an Java tai Hanoi Aptech
 */
package vn.aptech.quanlybanhang.pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.dao.EmployeeDAOImpl;
import vn.aptech.quanlybanhang.entities.Employee;
import vn.aptech.quanlybanhang.ui.TableUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;
import vn.aptech.quanlybanhang.utilities.I18n;

public class EmployeeSearchPage extends Page {

    @Override
    public void displayContent() {
        int check = 0;
        EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
        do {
            try {
                String keyword = AppScanner.scanStringWithi18Message("employee.scan.searchName");
                List<Employee> employees = employeeDAOImpl.findByNameEmployee(keyword);
                if (employees.isEmpty()) {
                    I18n.printEntityMessage("employee", "entity.msg.emptyResults");
                } else {
                    List<Object[]> rows = new ArrayList<>();
                    for (Employee ep : employees) {
                        Object[] row = {
                            ep.getId(),
                            ep.getName(),
                            ep.getAddress(),
                            ep.getPhone(),
                            ep.getDepartment(),
                            ep.getUsername()
                        };
                        rows.add(row);
                    }
                    String[] headers = {
                        "ID",
                        I18n.getMessage("employee.name"),
                        I18n.getMessage("employee.addr"),
                        I18n.getMessage("employee.phone"),
                        I18n.getMessage("employee.dept"),
                        I18n.getMessage("employee.username")
                    };
                    TableUI tableUI = new TableUI(headers, rows);
                    tableUI.display();
                }
                String choice = AppScanner.scanStringWithMessage(I18n.getMessage("entity.confirm.searchAnOther"));
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            } catch (SQLException e) {
                System.out.println("Exception when Employee.handleSearch: " + e.getMessage());
            } catch (Exception ex) {
                Logger.getLogger(EmployeeSearchPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (check == 0);
    }

    @Override
    public String getTitle() {
        return I18n.getEntityMessage("employee", "entity.title.search", true);
    }

}
