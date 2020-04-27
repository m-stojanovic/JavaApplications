package com.miticon.pdfgenerator.dto.template.subelements;

import com.miticon.pdfgenerator.dto.template.elements.ElementDto;
import com.miticon.pdfgenerator.dto.template.enumeration.RepeatableMode;

import java.util.ArrayList;
import java.util.List;

public class HeaderFooterDto {

    private RepeatableMode repeatableMode = RepeatableMode.ALL_PAGES;
    private List<ElementDto> elements = new ArrayList<>();

    private int height;

    public RepeatableMode getRepeatableMode() {
        return repeatableMode;
    }

    public void setRepeatableMode(RepeatableMode repeatableMode) {
        this.repeatableMode = repeatableMode;
    }

    public List<ElementDto> getElements() {
        return elements;
    }

    public void setElements(List<ElementDto> elements) {
        this.elements = elements;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
