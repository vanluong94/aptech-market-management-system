/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.ui;

/**
 *
 * @author vanluong
 */
public class HelperUI {

    public static void displayMargin() {
        System.out.println("\n\n");
    }

    public static void displayBorder(int length) {
        int dotCount = length - 2;
        String borderFormat = "+%" + dotCount + "s+";
        System.out.println(String.format(borderFormat, " ").replace(" ", "-"));
    }

    public static void displayBorderLineCenter(String content, int lineLength) {
        int spaceLength = lineLength - 2 - content.length();
        int paddingLeft, paddingRight;

        if (spaceLength % 2 == 1) {
            paddingLeft = spaceLength / 2;
            paddingRight = paddingLeft + 1;
        } else {
            paddingLeft = paddingRight = spaceLength / 2;
        }

        String paddingLeftFormat = "%1$" + paddingLeft + "s";
        String paddingRightFormat = "%1$" + paddingRight + "s";

        String lineFormat = "|" + paddingLeftFormat + content + paddingRightFormat + "|";

        System.out.println(String.format(lineFormat, " "));
    }

    public static void displayBorderLineLeft(String content, int lineLength) {
        if (content.length() > lineLength) {
            content = content.substring(0, lineLength - 4) + "...";
        }

        System.out.println(String.format("| %-" + (lineLength-3) + "s|", content));
    }

    public static void displayBorderLineRight(String content, int lineLength) {
        if (content.length() > lineLength) {
            content = content.substring(0, lineLength - 4) + "...";
        }

        System.out.println(String.format("|%" + (lineLength-3) + "s |", content));
    }

}
