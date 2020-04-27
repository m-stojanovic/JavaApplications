package com.miticon.pdfgenerator.service;

import com.miticon.pdfgenerator.dto.data.TemplateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {

    void saveTemplate(String user_id, TemplateDto template) throws Exception;
    List<String> getUserTemplates(String user_id) throws Exception;
    TemplateDto getTemplateById(String user_id, String template_id) throws Exception;

    void saveImage(String user_id, MultipartFile file) throws Exception;
    List<String> getUserImages(String user_id) throws Exception;

}
