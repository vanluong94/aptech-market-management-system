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
public abstract class MenuUI {
    
    protected String title;
    protected String[] menuItems;
    protected int[] choices;
    
    private int lineLength = 50;
    
    public void display() {

        // minimum 50 chars
        for (String menuItem : this.getMenuItems()) {
            if (menuItem.length() > lineLength) {
                this.setLineLength(menuItem.length());
            }
        }

        if (this.getTitle().length() > lineLength) {
            this.setLineLength(this.getTitle().length());
        }

        // Print Header
        displayBorder();
        displayHeader(this.getTitle());
        displayBorder();

        // Print Content
        for (String menuItem : this.getMenuItems()) {
            displayLine(menuItem);
        }

        // Print close border
        displayBorder();
    }

    protected void displayLine(String rowContent) {
        System.out.println(String.format("| %-" + (this.getLineLength() - 3) + "s|", rowContent));
    }

    protected void displayHeader(String title) {

        int spaceLength = this.getLineLength() - 2 - title.length();
        int paddingLeft, paddingRight;

        if (spaceLength % 2 == 1) {
            paddingLeft = spaceLength / 2;
            paddingRight = paddingLeft + 1;
        } else {
            paddingLeft = paddingRight = spaceLength / 2;
        }

        String paddingLeftFormat = "%1$" + paddingLeft + "s";
        String paddingRightFormat = "%1$" + paddingRight + "s";

        String lineFormat = "|" + paddingLeftFormat + title + paddingRightFormat + "|";

        System.out.println(String.format(lineFormat, " "));
    }

    protected void displayBorder() {
        int dotCount = this.getLineLength() - 2;
        String borderFormat = "+%" + dotCount + "s+";
        System.out.println(String.format(borderFormat, " ").replace(" ", "-"));
    }
    
    protected int[] getChoices() {
        return choices;
    }

    protected void setChoices(int[] choices) {
        this.choices = choices;
    }

    protected String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    protected String[] getMenuItems() {
        return menuItems;
    }

    protected void setMenuItems(String[] menuItems) {
        this.menuItems = menuItems;
    }

    private int getLineLength() {
        return lineLength;
    }

    private void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }
    
}
