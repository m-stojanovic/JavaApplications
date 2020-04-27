package com.miticon.pdfgenerator.facade;

import com.miticon.pdfgenerator.dto.data.PdfDataDto;
import com.miticon.pdfgenerator.dto.data.TemplateDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface PdfFacade {

    String saveTemplate(String userId, TemplateDto template) throws Exception;
    List<String> getUserTemplates(String userId) throws Exception;

    String saveImage(String userId, MultipartFile file) throws Exception;
    List<String> getUserImages(String userId) throws Exception;

    ByteArrayOutputStream generatePdf(PdfDataDto data) throws Exception;

}
