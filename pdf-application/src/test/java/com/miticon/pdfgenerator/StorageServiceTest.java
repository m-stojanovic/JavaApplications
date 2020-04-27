package com.miticon.pdfgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miticon.pdfgenerator.dto.data.TemplateDto;
import com.miticon.pdfgenerator.service.StorageService;
import com.miticon.pdfgenerator.utils.TemplateBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest
class StorageServiceTest {

	@Autowired
	private StorageService storageService;

	@Value("${path.templates}")
	private String path;

	@BeforeEach
	void deleteUserTemplateFolder() {
		String folderPath = path + "Test";
		File folder = new File(folderPath);
		if (folder.exists()) {
			File[]files = folder.listFiles();
			for (File file : files)
				file.delete();
			folder.delete();
		}
	}

	@Test
	void saveTemplateSuccessTest() {
		TemplateDto template = TemplateBuilder.buildTemplateDto();
		try {
			storageService.saveTemplate("Test", template);
			Assert.assertTrue(true);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	void getUserTemplatesTest() {
		TemplateDto template = TemplateBuilder.buildTemplateDto();
		try {
			template.setName("template_1");
			storageService.saveTemplate("Test", template);
			template.setName("template_2");
			storageService.saveTemplate("Test", template);
			template.setName("template_3");
			storageService.saveTemplate("Test", template);
			List<String> templates = storageService.getUserTemplates("Test");
			Assert.assertTrue(templates.size() == 3);
			Assert.assertTrue(templates.indexOf("template_1.json") != -1);
			Assert.assertTrue(templates.indexOf("template_2.json") != -1);
			Assert.assertTrue(templates.indexOf("template_3.json") != -1);
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	void getTemplateByIdTest() {
		TemplateDto template = TemplateBuilder.buildTemplateDto();
		try {
			template.setName("template");
			storageService.saveTemplate("Test", template);
			TemplateDto resultTemplate = storageService.getTemplateById("Test", "template");
			Assert.assertTrue(resultTemplate != null);
			ObjectMapper objectMapper = new ObjectMapper();
			String input = objectMapper.writeValueAsString(template);
			String output = objectMapper.writeValueAsString(resultTemplate);
			Assert.assertTrue(input.equals(output));
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

}
