package com.miticon.pdfgenerator.dto.template.subelements;

import com.miticon.pdfgenerator.dto.template.enumeration.AlignmentHorizontal;
import com.miticon.pdfgenerator.dto.template.enumeration.AlignmentVertical;

public class StyleDto {

    private ConditionDto condition;
    public static int EMPTY_VALUE = 999;

    private String fontName = "Arial";
    private int fontSize = 8;
    private String fontColor = "#000000";
    private boolean fontItalic;
    private boolean fontBold;
    private boolean fontUnderline;

    private AlignmentHorizontal horizontalAlignment = AlignmentHorizontal.NONE;
    private AlignmentVertical verticalAlignment = AlignmentVertical.NONE;
    private int horizontalOffset = EMPTY_VALUE;
    private int verticalOffset = EMPTY_VALUE;

    private String backgroundColor = "#FFFFFF";

    private int borderTopSize = EMPTY_VALUE;
    private String borderTopColor = "#000000";

    private int borderBottomSize = EMPTY_VALUE;
    private String borderBottomColor = "#000000";

    private int borderLeftSize = EMPTY_VALUE;
    private String borderLeftColor = "#000000";

    private int borderRightSize = EMPTY_VALUE;
    private String borderRightColor = "#000000";

    public StyleDto() { }

    public StyleDto(StyleDto styleDto) {
        this.condition = styleDto.condition;
        this.fontName = styleDto.fontName;
        this.fontSize = styleDto.fontSize;
        this.fontColor = styleDto.fontColor;
        this.fontItalic = styleDto.fontItalic;
        this.fontBold = styleDto.fontBold;
        this.fontUnderline = styleDto.fontUnderline;
        this.backgroundColor = styleDto.backgroundColor;
        this.horizontalAlignment = styleDto.horizontalAlignment;
        this.verticalAlignment = styleDto.verticalAlignment;
        this.horizontalOffset = styleDto.horizontalOffset;
        this.verticalOffset = styleDto.verticalOffset;
        this.borderTopSize = styleDto.borderTopSize;
        this.borderTopColor = styleDto.borderTopColor;
        this.borderBottomSize = styleDto.borderBottomSize;
        this.borderBottomColor = styleDto.borderBottomColor;
        this.borderLeftSize = styleDto.borderLeftSize;
        this.borderLeftColor = styleDto.borderLeftColor;
        this.borderRightSize = styleDto.borderRightSize;
        this.borderRightColor = styleDto.borderRightColor;
    }

    public ConditionDto getCondition() {
        return condition;
    }

    public void setCondition(ConditionDto condition) {
        this.condition = condition;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public boolean isFontItalic() {
        return fontItalic;
    }

    public void setFontItalic(boolean fontItalic) {
        this.fontItalic = fontItalic;
    }

    public boolean isFontBold() {
        return fontBold;
    }

    public void setFontBold(boolean fontBold) {
        this.fontBold = fontBold;
    }

    public boolean isFontUnderline() {
        return fontUnderline;
    }

    public void setFontUnderline(boolean fontUnderline) {
        this.fontUnderline = fontUnderline;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public AlignmentHorizontal getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(AlignmentHorizontal horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public AlignmentVertical getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(AlignmentVertical verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public int getHorizontalOffset() {
        return horizontalOffset;
    }

    public void setHorizontalOffset(int horizontalOffset) {
        this.horizontalOffset = horizontalOffset;
    }

    public int getVerticalOffset() {
        return verticalOffset;
    }

    public void setVerticalOffset(int verticalOffset) {
        this.verticalOffset = verticalOffset;
    }

    public int getBorderTopSize() {
        return borderTopSize;
    }

    public void setBorderTopSize(int borderTopSize) {
        this.borderTopSize = borderTopSize;
    }

    public String getBorderTopColor() {
        return borderTopColor;
    }

    public void setBorderTopColor(String borderTopColor) {
        this.borderTopColor = borderTopColor;
    }

    public int getBorderBottomSize() {
        return borderBottomSize;
    }

    public void setBorderBottomSize(int borderBottomSize) {
        this.borderBottomSize = borderBottomSize;
    }

    public String getBorderBottomColor() {
        return borderBottomColor;
    }

    public void setBorderBottomColor(String borderBottomColor) {
        this.borderBottomColor = borderBottomColor;
    }

    public int getBorderLeftSize() {
        return borderLeftSize;
    }

    public void setBorderLeftSize(int borderLeftSize) {
        this.borderLeftSize = borderLeftSize;
    }

    public String getBorderLeftColor() {
        return borderLeftColor;
    }

    public void setBorderLeftColor(String borderLeftColor) {
        this.borderLeftColor = borderLeftColor;
    }

    public int getBorderRightSize() {
        return borderRightSize;
    }

    public void setBorderRightSize(int borderRightSize) {
        this.borderRightSize = borderRightSize;
    }

    public String getBorderRightColor() {
        return borderRightColor;
    }

    public void setBorderRightColor(String borderRightColor) {
        this.borderRightColor = borderRightColor;
    }

    public void setBorders(int borderSize, String borderColor) {
        this.borderTopSize = borderSize;
        this.borderTopColor = borderColor;
        this.borderBottomSize = borderSize;
        this.borderBottomColor = borderColor;
        this.borderLeftSize = borderSize;
        this.borderLeftColor = borderColor;
        this.borderRightSize = borderSize;
        this.borderRightColor = borderColor;
    }

}
