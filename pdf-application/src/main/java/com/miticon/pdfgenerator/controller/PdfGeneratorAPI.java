package com.miticon.pdfgenerator.controller;

import com.miticon.pdfgenerator.dto.data.PdfDataDto;
import com.miticon.pdfgenerator.dto.data.TemplateDto;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;

public interface PdfGeneratorAPI {

    String PDF_GENERATOR_ENDPOINT = "/pdf-generator";
    String PDF_TEMPLATE = "/template";
    String PDF_IMAGE = "/image";

    @PostMapping(value = PDF_GENERATOR_ENDPOINT + PDF_TEMPLATE, consumes = MediaType.APPLICATION_JSON_VALUE)
    default ResponseEntity<String> uploadTemplate(@RequestParam String userId, @RequestBody TemplateDto template) throws Exception {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = PDF_GENERATOR_ENDPOINT + PDF_TEMPLATE + "/{userId}")
    default ResponseEntity<List<String>> getUserTemplates(@PathVariable String userId) throws Exception {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = PDF_GENERATOR_ENDPOINT + PDF_IMAGE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    default ResponseEntity<String> uploadImage(@RequestParam String userId, @RequestParam("file") MultipartFile file) throws Exception {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = PDF_GENERATOR_ENDPOINT + PDF_IMAGE + "/{userId}")
    default ResponseEntity<List<String>> getUserImages(@PathVariable String userId) throws Exception {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = PDF_GENERATOR_ENDPOINT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = APPLICATION_PDF_VALUE)
    default ResponseEntity<ByteArrayResource> generatePdf(@RequestBody PdfDataDto data) throws Exception {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
