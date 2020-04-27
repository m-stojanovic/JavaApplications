package com.miticon.pdfgenerator.dto.template.elements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.miticon.pdfgenerator.dto.template.enumeration.Element;
import com.miticon.pdfgenerator.dto.template.subelements.PageFormatDto;
import com.miticon.pdfgenerator.dto.template.subelements.StylesDto;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ElementTextDto.class, name="text"),
        @JsonSubTypes.Type(value = ElementImageDto.class, name="image"),
        @JsonSubTypes.Type(value = ElementTableDto.class, name="table")
})
public abstract class ElementDto {

    @JsonIgnore
    private Element elementType = Element.UNDEFINED;

    private StylesDto styleObject = new StylesDto();

    private int top;
    private int left;
    private int right;
    private int height;
    private int width;

    public ElementDto() {}

    public ElementDto(Element elementType) {
        this.elementType = elementType;
    }

    public Element getElementType() {
        return elementType;
    }

    protected void setElementType(Element elementType) {
        this.elementType = elementType;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public StylesDto getStyleObject() {
        return styleObject;
    }

    public void setStyleObject(StylesDto styleObject) {
        this.styleObject = styleObject;
    }

    public void recalculatePosition(PageFormatDto pageFormatDto) {
        int pageWidth = (int) pageFormatDto.getFormat().getPDRectangle().getWidth() - pageFormatDto.getMarginLeft() - pageFormatDto.getMarginRight();
        if (left == 0 && right != 0 && width != 0)
            left = pageWidth - right - width;
        if (left != 0 && right == 0 && width != 0)
            right = pageWidth - left + width;
        if (left != 0 && right != 0 && width == 0)
            width = pageWidth - left - right;
    }

}
