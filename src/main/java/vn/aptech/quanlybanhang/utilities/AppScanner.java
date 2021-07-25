/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.utilities;

import java.util.Scanner;

/**
 *
 * @author vanluong
 */
public class AppScanner {

    private static Scanner scanner;

    public static Scanner getScanner() {
        if (AppScanner.scanner == null) {
            setScanner(new Scanner(System.in));
        }
        return scanner;
    }

    public static void setScanner(Scanner instance) {
        AppScanner.scanner = instance;
    }
    
    /**
     * Scan string input and validate them, repeat when input type mismatch
     * @param message
     * @param canBeEmpty
     * @return string scanned
     */
    public static String scanStringWithMessage(String message, boolean canBeEmpty){
        while (true) {
            try {
                System.out.print(message);
                String str = getScanner().nextLine().trim();
                
                if(!canBeEmpty && str.length() == 0){
                    System.out.println("Giá trị không được bỏ trống");
                }else{
                    return str;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Giá trị không phù hợp.");
            }
        }
    }
    
    /**
     * this method always validate empty string
     * @param message
     * @return string scanned
     */
    public static String scanStringWithMessage(String message){
        return scanStringWithMessage(message, false);
    }
    
    /**
     * Scan integer input and validate them, repeat when input type mismatch
     * @param message
     * @return 
     */
    public static int scanIntWithMessage(String message){
        while (true) {
            try {
                System.out.print(message);
                int input = getScanner().nextInt();
                getScanner().nextLine(); // clear line
                return input;
            } catch (java.util.InputMismatchException e) {
                getScanner().nextLine(); // clear line in case input mismatch
                System.out.println("Giá trị không phù hợp.");
            }
        }
    }
}
