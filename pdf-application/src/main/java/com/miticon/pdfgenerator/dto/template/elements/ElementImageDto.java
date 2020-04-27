package com.miticon.pdfgenerator.dto.template.elements;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.miticon.pdfgenerator.dto.template.enumeration.Element;

@JsonTypeName("image")
public class ElementImageDto extends ElementDto {

    private String fileName;

    public ElementImageDto() {
        super(Element.IMAGE);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
