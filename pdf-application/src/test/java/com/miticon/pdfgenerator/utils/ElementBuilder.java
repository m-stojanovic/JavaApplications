package com.miticon.pdfgenerator.utils;

import com.miticon.pdfgenerator.dto.template.elements.ElementImageDto;
import com.miticon.pdfgenerator.dto.template.elements.ElementRectDto;
import com.miticon.pdfgenerator.dto.template.elements.ElementTableDto;
import com.miticon.pdfgenerator.dto.template.elements.ElementTextDto;
import com.miticon.pdfgenerator.dto.template.subelements.ColumnTableDto;

import java.util.List;

public class ElementBuilder {

    public static ElementTextDto buildTextElement(int top, int left, int right, int width, int height, String textValue, boolean convert) {
        if (convert) {
            top = Helper.mmToPointConvertor(top);
            left = Helper.mmToPointConvertor(left);
            right = Helper.mmToPointConvertor(right);
            width = Helper.mmToPointConvertor(width);
            height = Helper.mmToPointConvertor(height);
        }
        ElementTextDto elementTextDto = new ElementTextDto();
        elementTextDto.setTop(top);
        elementTextDto.setLeft(left);
        elementTextDto.setRight(right);
        elementTextDto.setWidth(width);
        elementTextDto.setHeight(height);
        elementTextDto.setTextValue(textValue);
        return elementTextDto;
    }

    public static ElementImageDto buildImageElement(int top, int left, int width, int height, String fileName, boolean convert) {
        if (convert) {
            top = Helper.mmToPointConvertor(top);
            left = Helper.mmToPointConvertor(left);
            width = Helper.mmToPointConvertor(width);
            height = Helper.mmToPointConvertor(height);
        }
        ElementImageDto elementImageDto = new ElementImageDto();
        elementImageDto.setTop(top);
        elementImageDto.setLeft(left);
        elementImageDto.setWidth(width);
        elementImageDto.setHeight(height);
        elementImageDto.setFileName(fileName);
        return elementImageDto;
    }

    public static ElementTableDto buildTableElement(int top, int left, int width, int height, List<ColumnTableDto> columns, boolean convert) {
        if (convert) {
            top = Helper.mmToPointConvertor(top);
            left = Helper.mmToPointConvertor(left);
            width = Helper.mmToPointConvertor(width);
            height = Helper.mmToPointConvertor(height);
        }
        ElementTableDto elementTableDto = new ElementTableDto();
        elementTableDto.setTop(top);
        elementTableDto.setLeft(left);
        elementTableDto.setWidth(width);
        elementTableDto.setHeight(height);
        elementTableDto.setColumns(columns);
        return elementTableDto;
    }

    public static ElementRectDto buildRectElement(int top, int left, int width, int height, boolean convert) {
        if (convert) {
            top = Helper.mmToPointConvertor(top);
            left = Helper.mmToPointConvertor(left);
            width = Helper.mmToPointConvertor(width);
            height = Helper.mmToPointConvertor(height);
        }
        ElementRectDto elementRectDto = new ElementRectDto();
        elementRectDto.setTop(top);
        elementRectDto.setLeft(left);
        elementRectDto.setWidth(width);
        elementRectDto.setHeight(height);
        return elementRectDto;
    }

}
