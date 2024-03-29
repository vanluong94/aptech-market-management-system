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
            setScanner(new Scanner(System.in, "UTF-8"));
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
                System.out.print(formatScanMessage(message));
                String str = getScanner().nextLine().trim();
                
                if(!canBeEmpty && str.length() == 0){
                    I18n.print("input.required");
                }else{
                    return str;
                }
            } catch (java.util.InputMismatchException e) {
                I18n.print("input.invalidType");
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
        return scanIntWithMessage(message, false);
    }

    public static int scanIntWithMessage(String message, boolean canBeEmpty){
        while (true) {
            try {
                System.out.print(formatScanMessage(message));
                int input = getScanner().nextInt();
                getScanner().nextLine(); // clear line
                
                if(!canBeEmpty && input == 0) {
                    I18n.print("input.required");
                } else {
                    return input;
                }
            } catch (java.util.InputMismatchException e) {
                getScanner().nextLine(); // clear line in case input mismatch
                I18n.print("input.invalidType");
            }
        }
    }

    public static double scanDoubleWithMessage(String message) {
        return scanDoubleWithMessage(message, false);
    }
    
    public static double scanDoubleWithMessage(String message, boolean canBeEmpty) {
        while (true) {
            double input = 0;
            try {
                System.out.print(formatScanMessage(message));
                input = getScanner().nextDouble();
                getScanner().nextLine(); // clear line
                
                if(!canBeEmpty && input == 0) {
                    I18n.print("input.required");
                } else {
                    return input;
                }
            } catch (java.util.InputMismatchException e) {
                I18n.print("input.invalidType");
            }
        }
    }
    
    public static float scanFloatWithMessage(String message) {
        return scanFloatWithMessage(message, false);
    }
    
    public static float scanFloatWithMessage(String message, boolean canBeEmpty) {
        while (true) {
            float input = 0;
            try {
                System.out.print(formatScanMessage(message));
                input = getScanner().nextFloat();
                getScanner().nextLine(); // clear line
                
                if(!canBeEmpty && input == 0) {
                    I18n.print("input.required");
                } else {
                    return input;
                }
            } catch (java.util.InputMismatchException e) {
                I18n.print("input.invalidType");
            }
        }
    }
    
    public static boolean confirm(String message) {
        return scanStringWithMessage(message).equalsIgnoreCase("y");
    }
    
    public static String scanStringWithi18Message(String messageKey, Object... args){
        return scanStringWithMessage(I18n.getMessage(messageKey, args));
    }
    
    public static int scanIntWithi18Message(String messageKey,Object... args){
        return scanIntWithMessage(I18n.getMessage(messageKey, args));
    }
    
    public static float scanFloatWithi18Message(String messageKey,Object... args){
        return scanFloatWithMessage(I18n.getMessage(messageKey, args));
    }
    
    public static String formatScanMessage(String message) {
        return message.trim().replaceFirst(":$", "") + ": ";
    }
}
