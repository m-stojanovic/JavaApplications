package com.miticon.pdfgenerator.dto.template.subelements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miticon.pdfgenerator.dto.template.elements.ElementDto;

import java.util.ArrayList;
import java.util.List;

public class BlockDto {

    private int offset = 10;
    private List<ElementDto> elements = new ArrayList<>();

    @JsonIgnore
    private int fixedHeight;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<ElementDto> getElements() {
        return elements;
    }

    public void setElements(List<ElementDto> elements) {
        this.elements = elements;
    }

    public int getFixedHeight() {
        return fixedHeight;
    }

    public void setFixedHeight(int fixedHeight) {
        this.fixedHeight = fixedHeight;
    }

}
