package com.miticon.pdfgenerator.dto.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miticon.pdfgenerator.dto.template.enumeration.PreciseAddress;
import com.miticon.pdfgenerator.dto.template.subelements.BlockDto;
import com.miticon.pdfgenerator.dto.template.subelements.HeaderFooterDto;
import com.miticon.pdfgenerator.dto.template.subelements.PageFormatDto;
import com.miticon.pdfgenerator.dto.template.subelements.StyleDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TemplateDto implements Serializable {

    private String name;
    private PageFormatDto pageFormat;

    private PreciseAddress preciseAddress = PreciseAddress.NONE;

    private StyleDto defaultText;
    private StyleDto defaultTableHeader;
    private StyleDto defaultTableCell;
    private StyleDto defaultImage;
    private StyleDto defaultRect;

    private HeaderFooterDto header;
    private List<BlockDto> blocks = new ArrayList<>();
    private HeaderFooterDto footer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PageFormatDto getPageFormat() {
        return pageFormat;
    }

    public void setPageFormat(PageFormatDto pageFormat) {
        this.pageFormat = pageFormat;
    }

    public StyleDto getDefaultText() {
        return defaultText;
    }

    public void setDefaultText(StyleDto defaultText) {
        this.defaultText = defaultText;
    }

    public StyleDto getDefaultTableHeader() {
        return defaultTableHeader;
    }

    public void setDefaultTableHeader(StyleDto defaultTableHeader) {
        this.defaultTableHeader = defaultTableHeader;
    }

    public StyleDto getDefaultTableCell() {
        return defaultTableCell;
    }

    public void setDefaultTableCell(StyleDto defaultTableCell) {
        this.defaultTableCell = defaultTableCell;
    }

    public StyleDto getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(StyleDto defaultImage) {
        this.defaultImage = defaultImage;
    }

    public StyleDto getDefaultRect() {
        return defaultRect;
    }

    public void setDefaultRect(StyleDto defaultRect) {
        this.defaultRect = defaultRect;
    }

    public HeaderFooterDto getHeader() {
        return header;
    }

    public void setHeader(HeaderFooterDto header) {
        this.header = header;
    }

    public List<BlockDto> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<BlockDto> blocks) {
        this.blocks = blocks;
    }

    public HeaderFooterDto getFooter() {
        return footer;
    }

    public void setFooter(HeaderFooterDto footer) {
        this.footer = footer;
    }

    public PreciseAddress getPreciseAddress() {
        return preciseAddress;
    }

    public void setPreciseAddress(PreciseAddress preciseAddress) {
        this.preciseAddress = preciseAddress;
    }

    @JsonIgnore
    public int getStartPosition() {
        return (getHeader() != null ? getHeader().getHeight() : 0) + getPageFormat().getMarginTop();
    }

    @JsonIgnore
    public int getEndPosition() {
        return ((int) pageFormat.getFormat().getPDRectangle().getHeight()) - (getFooter() != null ? getFooter().getHeight() : 0) -  pageFormat.getMarginBottom();
    }

}
