package com.miticon.pdfgenerator.dto.template.subelements;

import com.miticon.pdfgenerator.dto.template.enumeration.PageFormat;

public class PageFormatDto {

    private PageFormat format = PageFormat.A4; //A4 piece of paper measures 210 × 297 mm or 8.3 × 11.7 inches

    private int marginTop;
    private int marginBottom;
    private int marginLeft;
    private int marginRight;

    public PageFormatDto() {}

    public PageFormatDto(PageFormat format, int margin) {
        this.format = format;
        setMargins(margin);
    }

    public PageFormat getFormat() {
        return format;
    }

    public void setFormat(PageFormat format) {
        this.format = format;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public void setMargins(int margin) {
        this.marginTop = margin;
        this.marginBottom = margin;
        this.marginLeft = margin;
        this.marginRight = margin;
    }

}
