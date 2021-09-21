/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.quanlybanhang.ui;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vanluong
 */
public class TableUI {

    private final int extraSpaces = 3; // 3 characters for 2 spaces and 1 border
    
    private int colMaxLength = 80;
    private int lineLength = 0;

    private String[] headers;
    private List<Integer> columnsLength;
    private List<String> columnsAlign;
    private List<Object[]> rows;

    public TableUI(String[] headers, List<Object[]> rows) {

        this.columnsLength = new ArrayList<>();
        this.columnsAlign = new ArrayList<>();
        this.rows = new ArrayList<>();

        this.setHeaders(headers);
        this.setRows(rows);
        this.init();

    }

    private void init() {
        // find max line length for column
        for (int i = 0; i < this.getHeaders().length; i++) {
            // set column width to be 0 for temporary
            this.columnsLength.add(i, 0);
            this.maybeSetColMaxLength(this.getHeader(i).length(), i);

            // add default align left for this column
            this.columnsAlign.add(i, "left");
        }

        for (Object[] row : this.getRows()) {
            // check if there is any new max length found in these data
            for (int i = 0; i < row.length; i++) {
                this.maybeSetColMaxLength(row[i].toString().length(), i);
            }
        }

        this.computeLineLength();
    }

    public void display() {
        this.displayBorder();
        this.displayRowHeader();
        this.displayBorder();
        this.displayRows();
        this.displayBorder();
    }

    public void displayRowHeader() {
        this.displayRow(this.getHeaders());
    }

    public void displayRows() {
        for (Object[] row : this.getRows()) {
            this.displayRow(row);
        }
    }

    public void displayRow(Object[] row) {
        String output = "";

        for (int i = 0; i < row.length; i++) {
            int thisLength = this.getColLength(i) - this.extraSpaces; 
            
            String thisContent = row[i].toString();

            if (thisContent.length() > thisLength) {
                if (thisLength > 3) {
                    thisContent = thisContent.substring(0, thisLength - 3) + "...";
                } else {
                    thisContent = thisContent.substring(0, thisLength);
                }
            }

            if (thisContent.endsWith("₫") || this.getColAlign(i).equalsIgnoreCase("right")) {
                output += String.format("| %" + (thisLength) + "s ", thisContent); // align right with price
            } else {
                output += String.format("| %-" + (thisLength) + "s ", thisContent);

            }
        }

        output += "|";

        System.out.println(output);

    }

    public void displayBorder() {
        HelperUI.displayBorder(this.getLineLength());
    }

    private void computeLineLength() {
        for (int colLength : this.columnsLength) {
            this.lineLength += colLength;
        }
        this.lineLength++; // plus 1 char for the column border closure
    }

    public final void maybeSetColMaxLength(int thisLength, int i) {
        thisLength += this.extraSpaces; // plus chars to add spaces and border

        if (thisLength > this.getColLength(i) && thisLength <= this.getColMaxLength()) {
            this.columnsLength.set(i, thisLength);
        } else if (thisLength > this.getColMaxLength()) { // in case it's longer than limit, then we must use the maximum value accepted
            this.columnsLength.set(i, this.getColMaxLength());
        }
    }

    public int getColLength(int i) {
        return this.columnsLength.get(i);
    }

    public String getColAlign(int i) {
        return this.columnsAlign.get(i);
    }

    /**
     *
     * @param i index
     * @return String header at specified column index
     */
    public final String getHeader(int i) {
        return this.headers[i];
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int length) {
        this.lineLength = length;
    }

    public int getColMaxLength() {
        return colMaxLength;
    }

    public void setColMaxLength(int length) {
        this.colMaxLength = length;
    }

    public final String[] getHeaders() {
        return headers;
    }

    private void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public List<Integer> getColumnsLength() {
        return columnsLength;
    }

    public void setColumnsAlign(List<String> columns) {
        this.columnsAlign = columns;
    }

    public void setColumnsLength(List<Integer> columns) {
        this.columnsLength = columns;
    }

    public List<Object[]> getRows() {
        return rows;
    }

    private void setRows(List<Object[]> rows) {
        this.rows = rows;
    }

}
