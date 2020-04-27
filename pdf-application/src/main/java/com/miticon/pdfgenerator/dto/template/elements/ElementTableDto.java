package com.miticon.pdfgenerator.dto.template.elements;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.miticon.pdfgenerator.dto.template.enumeration.Element;
import com.miticon.pdfgenerator.dto.template.enumeration.HeaderVisibility;
import com.miticon.pdfgenerator.dto.template.subelements.ColumnTableDto;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName("table")
public class ElementTableDto extends ElementDto {

    private List<ColumnTableDto> columns = new ArrayList<>();

    private HeaderVisibility headerVisibility = HeaderVisibility.FIRST_PAGE;

    private int headerHeight = 30;
    private int rowHeight = 20;

    public ElementTableDto() {
        setElementType(Element.TABLE);
    }

    public List<ColumnTableDto> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnTableDto> columns) {
        this.columns = columns;
    }

    public int getHeaderHeight() {
        return headerHeight;
    }

    public void setHeaderHeight(int headerHeight) {
        this.headerHeight = headerHeight;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    public HeaderVisibility getHeaderVisibility() {
        return headerVisibility;
    }

    public void setHeaderVisibility(HeaderVisibility headerVisibility) {
        this.headerVisibility = headerVisibility;
    }

}
