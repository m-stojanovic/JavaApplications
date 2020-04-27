package com.miticon.pdfgenerator.service;

import com.miticon.pdfgenerator.dto.data.TemplateDto;

public interface ValidationService {

    void validateTemplate(TemplateDto template) throws Exception;

}
