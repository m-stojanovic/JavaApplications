package com.miticon.pdfgenerator.dto.template.elements;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.miticon.pdfgenerator.dto.template.enumeration.Element;

@JsonTypeName("text")
public class ElementTextDto extends ElementDto {

    private String textValue;

    public ElementTextDto() {
        setElementType(Element.TEXT);
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

}
