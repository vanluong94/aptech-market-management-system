/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.aptech.quanlybanhang.entities.Category;
import vn.aptech.quanlybanhang.menu.AuthMenu;
import vn.aptech.quanlybanhang.menu.BaseMenu;
import vn.aptech.quanlybanhang.menu.CategoryMenu;
import vn.aptech.quanlybanhang.service.CategoryService;
import vn.aptech.quanlybanhang.utilities.DBConnection;

/**
 *
 * @author anhnbt
 */
public class MainMenu implements BaseMenu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(scanner);
    }

    @Override
    public void displayMenu() {
        System.out.println("==============================");
        System.out.println("= Ph?n M?m Qu?n L� Si�u Th? =");
        System.out.println("==============================");
        System.out.println("1. Qu?n l� danh m?c");
        System.out.println("2. Dang nhap");
        System.out.println("0. Tho�t");
    }
    
    @Override
    public void start(Scanner scanner) {
        int choice = -1;
        this.displayMenu();
        System.out.println("Nhập lựa ch�?n [0-1]: ");
        choice = scanner.nextInt();
        scanner.nextLine();
        switch(choice) {
            case 1:
                CategoryMenu categoryMenu = new CategoryMenu();
                categoryMenu.start(scanner);
                break;
            case 2:
                AuthMenu authMenu = new AuthMenu();
                authMenu.start(scanner);
                break;
            case 0:
                System.exit(0);
        }
    }
    
}
