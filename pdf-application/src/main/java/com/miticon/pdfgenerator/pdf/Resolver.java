package com.miticon.pdfgenerator.pdf;

import com.miticon.pdfgenerator.dto.data.TemplateDto;
import com.miticon.pdfgenerator.dto.template.elements.ElementDto;
import com.miticon.pdfgenerator.dto.template.elements.ElementImageDto;
import com.miticon.pdfgenerator.dto.template.enumeration.AlignmentHorizontal;
import com.miticon.pdfgenerator.dto.template.enumeration.AlignmentVertical;
import com.miticon.pdfgenerator.dto.template.subelements.BlockDto;
import com.miticon.pdfgenerator.dto.template.subelements.StyleDto;
import org.springframework.boot.convert.Delimiter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Map;

public class Resolver {

    public static String applyPlaceholder(String rawValue, Map<String, String> placeholders) {
        for (String key : placeholders.keySet())
            if (rawValue.contains(key))
                rawValue = rawValue.replace(key, placeholders.get(key));
        return rawValue;
    }

    public static StyleDto calculateStyle(StyleDto styleDto, ElementDto element, int pX, int pY, String text, boolean lastRow) {
        StyleDto calculated = new StyleDto(styleDto);
        if (element.getStyleObject() != null) {
            for (StyleDto style : element.getStyleObject().getStyles()) {
                if (style.getCondition() == null || !style.getCondition().isSuccessful(pX, pY, text, lastRow))
                    continue;
                if (style.getFontName() != null)
                    calculated.setFontName(style.getFontName());
                if (style.getFontSize() > 0)
                    calculated.setFontSize(style.getFontSize());
                if (style.isFontItalic())
                    calculated.setFontItalic(style.isFontItalic());
                if (style.isFontBold())
                    calculated.setFontBold(style.isFontBold());
                if (style.getHorizontalAlignment() != AlignmentHorizontal.NONE)
                    calculated.setHorizontalAlignment(style.getHorizontalAlignment());
                if (style.getVerticalAlignment() != AlignmentVertical.NONE)
                    calculated.setVerticalAlignment(style.getVerticalAlignment());
                if (style.getHorizontalOffset() != StyleDto.EMPTY_VALUE)
                    calculated.setHorizontalOffset(style.getHorizontalOffset());
                if (style.getVerticalOffset() != StyleDto.EMPTY_VALUE)
                    calculated.setVerticalOffset(style.getVerticalOffset());
                if (!style.getFontColor().isEmpty())
                    calculated.setFontColor(style.getFontColor());
                if (!style.getBackgroundColor().isEmpty())
                    calculated.setBackgroundColor(style.getBackgroundColor());
                if (!style.getBorderLeftColor().isEmpty())
                    calculated.setBorderLeftColor(style.getBorderLeftColor());
                if (!style.getBorderTopColor().isEmpty())
                    calculated.setBorderTopColor(style.getBorderTopColor());
                if (!style.getBorderRightColor().isEmpty())
                    calculated.setBorderRightColor(style.getBorderRightColor());
                if (!style.getBorderBottomColor().isEmpty())
                    calculated.setBorderBottomColor(style.getBorderBottomColor());
                if (style.getBorderTopSize() != StyleDto.EMPTY_VALUE)
                    calculated.setBorderTopSize(style.getBorderTopSize());
                if (style.getBorderLeftSize() != StyleDto.EMPTY_VALUE)
                    calculated.setBorderLeftSize(style.getBorderLeftSize());
                if (style.getBorderBottomSize() != StyleDto.EMPTY_VALUE)
                    calculated.setBorderBottomSize(style.getBorderBottomSize());
                if (style.getBorderRightSize() != StyleDto.EMPTY_VALUE)
                    calculated.setBorderRightSize(style.getBorderRightSize());
            }
        }
        return calculated;
    }

    public static void recalculateAdditionalFields(TemplateDto templateDto, String imagePath, String userId) {
        if (templateDto.getHeader() != null) {
            List<ElementDto> elementsInHeader = templateDto.getHeader().getElements();
            for (ElementDto element : elementsInHeader)
                element.recalculatePosition(templateDto.getPageFormat());
        }
        List<BlockDto> blocks = templateDto.getBlocks();
        for (BlockDto block : blocks) {
            int maxBottomInBlock = 0;
            List<ElementDto> allElementsInBlock = block.getElements();
            for (ElementDto element : allElementsInBlock) {
                element.recalculatePosition(templateDto.getPageFormat());
                if (element.getTop() + element.getHeight() > maxBottomInBlock)
                    maxBottomInBlock = element.getTop() + element.getHeight();
                if (imagePath != null && userId != null) {
                    if (element instanceof ElementImageDto) {
                        ElementImageDto imageElement = (ElementImageDto) element;


                        imageElement.setFileName(imagePath + userId + "/" + imageElement.getFileName());
                    }
                }
            }
            block.setFixedHeight(maxBottomInBlock);
        }
        if (templateDto.getFooter() != null) {
            List<ElementDto> elementsInFooter = templateDto.getFooter().getElements();
            for (ElementDto element : elementsInFooter)
                element.recalculatePosition(templateDto.getPageFormat());
        }
    }

    private static String decSeparator = "";
    static {
        DecimalFormat format = (DecimalFormat)DecimalFormat.getInstance();
        DecimalFormatSymbols symbols=format.getDecimalFormatSymbols();
        decSeparator = "" + symbols.getDecimalSeparator();
    }

    public static double extractNumber(String text) {
        String digits = text.replaceAll("[^\\.,0123456789-]","").replaceAll("[\\.,]", decSeparator);
        if (digits.equals(""))
            return 0.0;
        double result = 0.0;
        try {
            result = Double.parseDouble(digits);
        }
        catch (Exception e) {}
        return result;
    }

}
