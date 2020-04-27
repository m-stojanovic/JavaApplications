package com.miticon.pdfgenerator.service;

import com.miticon.pdfgenerator.dto.data.TemplateDto;
import com.miticon.pdfgenerator.dto.template.elements.ElementDto;
import com.miticon.pdfgenerator.dto.template.enumeration.AlignmentHorizontal;
import com.miticon.pdfgenerator.dto.template.enumeration.Element;
import com.miticon.pdfgenerator.dto.template.enumeration.PageFormat;
import com.miticon.pdfgenerator.dto.template.subelements.BlockDto;
import com.miticon.pdfgenerator.dto.template.subelements.StyleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private FontLoaderServiceImpl fontLoaderService;

    public static String missingFont;

    public static String EMPTY_PAGE_FORMAT = "Page format is not set";
    public static String INVALID_FONT_SIZE = "Font size is negative";
    public static String HEADER_CAN_NOT_CONTAIN_TABLE = "Header can't contain table element";
    public static String FOOTER_CAN_NOT_CONTAIN_TABLE = "Footer can't contain table element";
    public static String TABLE_IS_NOT_LAST_ELEMENT_IN_BLOCK = "If block contains table element, that table must be the last element in block";
    public static String MORE_THAN_ONE_TABLE_IN_BLOCK = "One block can contain only one table";
    public static String FONT_NOT_AVAILABLE = "Font not available";
    public static String IMAGE_CAN_NOT_CONTAIN_STYLE = "Image can not contain styles";
    public static String HORIZONTAL_POSITION_NOT_FOUND = "Horizontal position not found, please add it";
    public static String HEADER_HEIGHT_NOT_FOUND = "If Header have at least one element, must have height";
    public static String FOOTER_HEIGHT_NOT_FOUND = "If footer have at least one element, must have height";

    @Override
    public void validateTemplate(TemplateDto template) throws Exception {
        if (template.getPageFormat().getFormat() == PageFormat.NONE)
            throw new Exception(EMPTY_PAGE_FORMAT);

        if (template.getDefaultText().getFontSize() <= 0)
            throw new Exception(INVALID_FONT_SIZE);

        if (template.getHeader() != null) {
            List<ElementDto> headerElements = template.getHeader().getElements();
            if (!headerElements.isEmpty() && template.getHeader().getHeight() == 0)
                throw new Exception(HEADER_HEIGHT_NOT_FOUND);

            for (ElementDto element : headerElements)
                if (element.getElementType().equals(Element.TABLE))
                    throw new Exception(HEADER_CAN_NOT_CONTAIN_TABLE);
            for (ElementDto element : headerElements)
                validateAlignment(element);
        }

        if (template.getFooter() != null) {
            List<ElementDto> footerElements = template.getFooter().getElements();
            if (!footerElements.isEmpty() && template.getFooter().getHeight() == 0)
                throw new Exception(FOOTER_HEIGHT_NOT_FOUND);
            for (ElementDto element : footerElements)
                if (element.getElementType().equals(Element.TABLE))
                    throw new Exception(FOOTER_CAN_NOT_CONTAIN_TABLE);
            for (ElementDto element : footerElements)
                validateAlignment(element);
        }

        List<BlockDto> blocks = template.getBlocks();
        Set<String> listOfFontsInTemplate = new HashSet<>();
        List<String> listOfAvailableFonts = fontLoaderService.getFontList();

        for (BlockDto block : blocks) {
            int numberOfTablesInBlock = 0;
            List<ElementDto> allElementsInBlock = block.getElements();
            for (ElementDto element : allElementsInBlock) {
                List<StyleDto> listOfStyles = new ArrayList<>();
                listOfStyles.addAll(element.getStyleObject().getStyles());
                for (StyleDto style : listOfStyles)
                    listOfFontsInTemplate.add(style.getFontName());
                if (element.getElementType().equals(Element.TABLE))
                    numberOfTablesInBlock ++;
                if (numberOfTablesInBlock == 1 && allElementsInBlock.indexOf(element) != (allElementsInBlock.size() - 1))
                    throw new Exception(TABLE_IS_NOT_LAST_ELEMENT_IN_BLOCK);
                if (numberOfTablesInBlock > 1)
                    throw new Exception(MORE_THAN_ONE_TABLE_IN_BLOCK);
                if (element.getElementType().equals(Element.IMAGE) && (!element.getStyleObject().getStyles().isEmpty()))
                    throw new Exception(IMAGE_CAN_NOT_CONTAIN_STYLE);
            }
        }

        for (String fontName : listOfFontsInTemplate) {
            if (!listOfAvailableFonts.contains(fontLoaderService.getFontPath() + fontName + ".ttf")) {
                missingFont = fontName;
                throw new Exception(FONT_NOT_AVAILABLE);
            }
        }

        for (BlockDto block : blocks) {
            List<ElementDto> allElementsInBlock = block.getElements();
            for (ElementDto element : allElementsInBlock)
                validateAlignment(element);
        }
    }

    private void validateAlignment(ElementDto element) throws Exception {
        List<StyleDto> listOfStyles = new ArrayList<>();
        listOfStyles.addAll(element.getStyleObject().getStyles());
        for (StyleDto style : listOfStyles) {
            if (style.getHorizontalAlignment().equals(AlignmentHorizontal.LEFT) && element.getLeft() == 0)
                throw new Exception(HORIZONTAL_POSITION_NOT_FOUND);
            if (style.getHorizontalAlignment().equals(AlignmentHorizontal.CENTER) && (element.getLeft() == 0 || element.getRight() == 0))
                throw new Exception(HORIZONTAL_POSITION_NOT_FOUND);
            if (style.getHorizontalAlignment().equals(AlignmentHorizontal.RIGHT) && element.getRight() == 0)
                throw new Exception(HORIZONTAL_POSITION_NOT_FOUND);
        }
    }

}
