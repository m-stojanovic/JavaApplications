package com.miticon.pdfgenerator.service;

import com.miticon.pdfgenerator.dto.data.TemplateDto;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.util.Map;

public interface PdfService {

    PDDocument buildDocument(TemplateDto template, Map<String, String> placeholders) throws Exception;

}
