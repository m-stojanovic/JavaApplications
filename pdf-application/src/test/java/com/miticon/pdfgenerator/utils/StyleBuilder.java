package com.miticon.pdfgenerator.utils;

import com.miticon.pdfgenerator.dto.template.enumeration.AlignmentHorizontal;
import com.miticon.pdfgenerator.dto.template.enumeration.AlignmentVertical;
import com.miticon.pdfgenerator.dto.template.enumeration.ConditionType;
import com.miticon.pdfgenerator.dto.template.subelements.ConditionDto;
import com.miticon.pdfgenerator.dto.template.subelements.StyleDto;

public class StyleBuilder {

    public static StyleDto build() {
        return new StyleDto();
    }

    public static StyleDto build(int borderSize) {
        StyleDto style = new StyleDto();
        style.setCondition(new ConditionDto(ConditionType.ALWAYS_APPLY, -1, -1, "", "", ""));
        style.setBorders(borderSize, "#000000");
        style.setHorizontalAlignment(AlignmentHorizontal.LEFT);
        style.setVerticalAlignment(AlignmentVertical.CENTER);
        style.setHorizontalOffset(5);
        style.setVerticalOffset(0);
        return style;
    }

    public static StyleDto build(int fontSize, boolean fontBold, int borderSize) {
        StyleDto style = new StyleDto();
        style.setCondition(new ConditionDto(ConditionType.ALWAYS_APPLY, -1, -1, "", "", ""));
        style.setFontSize(fontSize);
        style.setFontBold(fontBold);
        style.setBorders(borderSize, "#000000");
        style.setHorizontalAlignment(AlignmentHorizontal.LEFT);
        style.setVerticalAlignment(AlignmentVertical.CENTER);
        style.setHorizontalOffset(5);
        style.setVerticalOffset(2);
        return style;
    }

    public static StyleDto build(int fontSize, boolean fontBold, int borderSize, AlignmentHorizontal textAlignmentHorizontal, int offsetHorizontal, AlignmentVertical textAlignmentVertical, int offsetVertical) {
        StyleDto style = new StyleDto();
        style.setCondition(new ConditionDto(ConditionType.ALWAYS_APPLY, -1, -1, "", "", ""));
        style.setFontSize(fontSize);
        style.setFontBold(fontBold);
        style.setBorders(borderSize, "#000000");
        style.setHorizontalAlignment(textAlignmentHorizontal);
        style.setVerticalAlignment(textAlignmentVertical);
        style.setHorizontalOffset(offsetHorizontal);
        style.setVerticalOffset(offsetVertical);
        return style;
    }

    public static StyleDto build(ConditionType conditionType, int pX, int pY, int borderBottomSize, boolean fontBold, boolean fontItalic, AlignmentHorizontal textAlignmentHorizontal, int offsetHorizontal, AlignmentVertical textAlignmentVertical, int offsetVertical) {
        StyleDto style = new StyleDto();
        style.setCondition(new ConditionDto(conditionType, pX, pY, "", "", ""));
        style.setBorderBottomSize(borderBottomSize);
        style.setFontBold(fontBold);
        style.setFontItalic(fontItalic);
        style.setHorizontalAlignment(textAlignmentHorizontal);
        style.setHorizontalOffset(offsetHorizontal);
        style.setVerticalAlignment(textAlignmentVertical);
        style.setVerticalOffset(offsetVertical);
        return style;
    }

    public static StyleDto build(ConditionType conditionType, int pX, int pY, boolean fontBold, boolean fontItalic, AlignmentHorizontal textAlignmentHorizontal, int offsetHorizontal, AlignmentVertical textAlignmentVertical, int offsetVertical) {
        StyleDto style = new StyleDto();
        style.setCondition(new ConditionDto(conditionType, pX, pY, "", "", ""));
        style.setFontBold(fontBold);
        style.setFontItalic(fontItalic);
        style.setHorizontalAlignment(textAlignmentHorizontal);
        style.setHorizontalOffset(offsetHorizontal);
        style.setVerticalAlignment(textAlignmentVertical);
        style.setVerticalOffset(offsetVertical);
        return style;
    }

    public static StyleDto build(ConditionType conditionType, int pX, int pY, String param_1, String fontColor) {
        StyleDto style = new StyleDto();
        style.setCondition(new ConditionDto(conditionType, pX, pY, param_1, "", ""));
        style.setFontColor(fontColor);
        return style;
    }

    public static StyleDto build(int size, boolean fontBold, String fontColor, AlignmentHorizontal textAlignmentHorizontal, int offsetHorizontal) {
        StyleDto style = new StyleDto();
        style.setCondition(new ConditionDto(ConditionType.ALWAYS_APPLY, -1, -1, "", "", ""));
        style.setBorders(0, "#000000");
        style.setFontSize(size);
        style.setFontBold(fontBold);
        style.setFontColor(fontColor);
        style.setHorizontalAlignment(textAlignmentHorizontal);
        style.setHorizontalOffset(offsetHorizontal);
        return style;
    }

    public static StyleDto build(ConditionType conditionType, int pX, int pY, String param_1, String fontColor, AlignmentHorizontal textAlignmentHorizontal, int offsetHorizontal) {
        StyleDto style = new StyleDto();
        style.setCondition(new ConditionDto(conditionType, pX, pY, param_1, "", ""));
        style.setFontColor(fontColor);
        style.setHorizontalAlignment(textAlignmentHorizontal);
        style.setHorizontalOffset(offsetHorizontal);
        return style;
    }

}
