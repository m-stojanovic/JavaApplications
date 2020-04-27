package com.miticon.pdfgenerator.dto.template.elements;

import com.miticon.pdfgenerator.dto.template.enumeration.Element;

public class ElementParagraphDto extends ElementDto {

    private String textValue;

    public ElementParagraphDto() {
        setElementType(Element.PARAGRAPH);
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

}
