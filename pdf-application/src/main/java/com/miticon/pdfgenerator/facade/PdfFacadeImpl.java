package com.miticon.pdfgenerator.facade;
import com.miticon.pdfgenerator.dto.data.PdfDataDto;
import com.miticon.pdfgenerator.dto.data.TemplateDto;
import com.miticon.pdfgenerator.pdf.Resolver;
import com.miticon.pdfgenerator.service.PdfService;
import com.miticon.pdfgenerator.service.StorageService;
import com.miticon.pdfgenerator.service.ValidationService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class PdfFacadeImpl implements PdfFacade {

    @Value("${path.images}")
    private String imagePath;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private PdfService pdfService;

    @Override
    public String saveTemplate(String userId, TemplateDto template) throws Exception {
        Resolver.recalculateAdditionalFields(template, null, null);
        validationService.validateTemplate(template);
        storageService.saveTemplate(userId, template);
        return template.getName();
    }

    @Override
    public List<String> getUserTemplates(String userId) throws Exception {
        return storageService.getUserTemplates(userId);
    }

    @Override
    public String saveImage(String userId, MultipartFile file) throws Exception {
        storageService.saveImage(userId, file);
        return file.getOriginalFilename();
    }

    @Override
    public List<String> getUserImages(String userId) throws Exception {
        return storageService.getUserImages(userId);
    }

    @Override
    public ByteArrayOutputStream generatePdf(PdfDataDto data) throws Exception {
        TemplateDto template = storageService.getTemplateById(data.getUserId(), data.getTemplateId());
        Resolver.recalculateAdditionalFields(template, imagePath, data.getUserId());
        Map<String, String> mapPlaceholders = data.getPlaceHolders();
        PDDocument document = pdfService.buildDocument(template, mapPlaceholders);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();
        return byteArrayOutputStream;
    }

}
