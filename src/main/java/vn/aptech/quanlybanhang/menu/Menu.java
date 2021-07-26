/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.menu;

import java.util.Arrays;
import java.util.Scanner;
import vn.aptech.quanlybanhang.ui.MenuUI;
import vn.aptech.quanlybanhang.utilities.AppScanner;

public abstract class Menu extends MenuUI implements BaseMenu {
    
    public abstract void handle(int choice);

    @Override
    public void displayMenu() {
        this.display();
    }

    @Override
    public void start() {
        
        this.displayMenu();
        
        int choice = -1;
        
        do{
            choice = AppScanner.scanIntWithMessage("Vui lòng nhập lựa chọn: ");

            if( isChoiceValid(choice) ){
                this.handle(choice);
                this.start(); // start this menu again after handle job is done
            }else{
                System.out.println("Lựa chọn không khả dụng");
            }

        }while( !isChoiceValid(choice) );

    }
    
    
    public boolean isChoiceValid(int choice){
        for(int _choice : this.getChoices()){
            if(_choice == choice){
                return true;
            }
        }
        return false;
    }

}
