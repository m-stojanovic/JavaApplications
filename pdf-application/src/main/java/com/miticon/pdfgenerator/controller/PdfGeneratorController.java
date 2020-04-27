package com.miticon.pdfgenerator.controller;

import com.miticon.pdfgenerator.dto.data.PdfDataDto;
import com.miticon.pdfgenerator.dto.data.TemplateDto;
import com.miticon.pdfgenerator.facade.PdfFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Controller
public class PdfGeneratorController implements PdfGeneratorAPI {

    @Autowired
    private PdfFacade facade;

    @Override
    public ResponseEntity<String> uploadTemplate(String userId, TemplateDto template) throws Exception {
        return new ResponseEntity<>(facade.saveTemplate(userId, template), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<String>> getUserTemplates(String userId) throws Exception {
        return new ResponseEntity<>(facade.getUserTemplates(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> uploadImage(@RequestParam String userId, MultipartFile file) throws Exception {
        return new ResponseEntity<>(facade.saveImage(userId, file), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<String>> getUserImages(String userId) throws Exception {
        return new ResponseEntity<>(facade.getUserImages(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ByteArrayResource> generatePdf(PdfDataDto data) throws Exception {
        ByteArrayOutputStream pdfStream = facade.generatePdf(data);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + data.getTemplateId() + ".pdf");
        headers.setContentLength(pdfStream.size());
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity<>(new ByteArrayResource(pdfStream.toByteArray(), "document"), headers, HttpStatus.OK);
    }

}
