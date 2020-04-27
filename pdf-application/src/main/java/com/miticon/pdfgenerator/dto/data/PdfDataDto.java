package com.miticon.pdfgenerator.dto.data;

import java.util.Map;

public class PdfDataDto {

    private String userId;
    private String templateId;
    private Map<String, String> placeHolders;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Map<String, String> getPlaceHolders() {
        return placeHolders;
    }

    public void setPlaceHolders(Map<String, String> placeHolders) {
        this.placeHolders = placeHolders;
    }

}
