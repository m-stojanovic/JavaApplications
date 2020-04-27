package com.miticon.pdfgenerator.dto.template.subelements;

public class ColumnTableDto {

    private String titleText;
    private int columnWidth;
    private String cellText;

    public ColumnTableDto() {}

    public ColumnTableDto(String titleText, int columnWidth, String cellText) {
        this.titleText = titleText;
        this.columnWidth = columnWidth;
        this.cellText = cellText;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
    }

    public String getCellText() {
        return cellText;
    }

    public void setCellText(String cellText) {
        this.cellText = cellText;
    }

}
