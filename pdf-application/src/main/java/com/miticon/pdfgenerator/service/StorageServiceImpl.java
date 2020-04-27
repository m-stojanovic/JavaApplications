package com.miticon.pdfgenerator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miticon.pdfgenerator.dto.data.TemplateDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${path.templates}")
    private String templatePath;

    @Value("${path.images}")
    private String imagePath;

    @Override
    public void saveTemplate(String user_id, TemplateDto template) throws Exception {
        File folder = new File(templatePath + user_id);
        folder.mkdir();
        File file = new File(folder + "/" + template.getName() + ".json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, template);
        } catch (IOException e) {
            throw new Exception("Unable to save report template, reason: " + e.getMessage());
        }
    }

    @Override
    public List<String> getUserTemplates(String user_id) throws Exception {
        File folder = new File(templatePath + user_id);
        if (!folder.exists())
            throw new Exception("For user with id " + user_id + " there is no saved templates!");
        File[] listOfFiles = folder.listFiles();
        List<String> listOfTemplates = new ArrayList<>();
        for (File file : listOfFiles)
            listOfTemplates.add(file.getName());
        return listOfTemplates;
    }

    @Override
    public TemplateDto getTemplateById(String user_id, String template_id) throws Exception {
        File file = new File(templatePath + user_id + "/" + template_id + ".json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TemplateDto template = objectMapper.readValue(file, TemplateDto.class);
            return template;
        } catch (IOException e) {
            throw new Exception("Unable to load report template for userID = " + user_id + ", and templateID = " + template_id + ", reason: " + e.getMessage());
        }
    }

    @Override
    public void saveImage(String user_id, MultipartFile file) throws Exception {
        File folder = new File(imagePath + user_id);
        if (!folder.exists())
            folder.mkdir();
        Path saveLocation = Paths.get(imagePath + user_id + "/" + file.getOriginalFilename());
        Files.copy(file.getInputStream(), saveLocation, StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public List<String> getUserImages(String user_id) throws Exception {
        File folder = new File(imagePath + user_id);
        if (!folder.exists())
            throw new Exception("For user with id " + user_id + " there is no saved images!");
        List<String> images = new ArrayList<>();
        for (File file : folder.listFiles())
            images.add(file.getName());
        return images;
    }

}
