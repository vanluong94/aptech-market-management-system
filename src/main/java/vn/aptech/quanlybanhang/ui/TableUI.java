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

    private final int colMaxLength = 80;

    private int lineLength = 0;
    private String[] headers;
    private List<Integer> columns = new ArrayList<>();
    private List<Object[]> rows = new ArrayList<>();

    public TableUI(String[] headers, List<Object[]> rows) {

        this.setHeaders(headers);
        this.setRows(rows);

        // find max line length for column
        for (int i = 0; i < this.getHeaders().length; i++) {
            // set column width to be 0 for temporary
            this.getColumns().add(i, 0);

            this.maybeSetColMaxLength(this.getHeader(i).length(), i);
        }

        for (Object[] row : this.getRows()) {
            // check if there is any new max length found in these data
            for (int i = 0; i < row.length; i++) {
                this.maybeSetColMaxLength(row[i].toString().length(), i);
            }
        }

        // compute the line length for table
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
            output += String.format("| %-" + (this.getColLength(i) - 2) + "s", row[i]);
        }

        output += "|";

        System.out.println(output);

    }

    public void displayBorder() {
        int dotCount = this.getLineLength() - 2;
        String borderFormat = "+%" + dotCount + "s+";
        System.out.println(String.format(borderFormat, " ").replace(" ", "-"));
    }

    public void computeLineLength() {
        for (int colLength : this.getColumns()) {
            this.lineLength += colLength;
        }
        this.lineLength++; // plus 1 char for the column border closure
    }

    public final void maybeSetColMaxLength(int thisLength, int i) {
        // System.out.println(String.format("Check column length, column [%d] = %d", i, thisLength));
        thisLength += 4; // plus 4 chars to add spaces and border

        if (thisLength > this.getColLength(i) && thisLength <= this.getColMaxLength()) {
            this.getColumns().set(i, thisLength);
        }
    }

    public int getColLength(int i) {
        return this.getColumns().get(i);
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

    public int getColMaxLength() {
        return colMaxLength;
    }

    public final String[] getHeaders() {
        return headers;
    }

    private void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public List<Integer> getColumns() {
        return columns;
    }

    public List<Object[]> getRows() {
        return rows;
    }

    private void setRows(List<Object[]> rows) {
        this.rows = rows;
    }

}