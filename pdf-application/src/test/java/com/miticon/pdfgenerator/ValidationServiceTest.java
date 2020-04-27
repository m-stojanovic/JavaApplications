package com.miticon.pdfgenerator;

import com.miticon.pdfgenerator.dto.data.TemplateDto;
import com.miticon.pdfgenerator.dto.template.enumeration.PageFormat;
import com.miticon.pdfgenerator.pdf.Resolver;
import com.miticon.pdfgenerator.service.ValidationService;
import com.miticon.pdfgenerator.service.ValidationServiceImpl;
import com.miticon.pdfgenerator.utils.TemplateBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidationServiceTest {

	@Autowired
	private ValidationService validationService;

	@Value("${path.images}")
	private String imagePath;

	@Test
	void saveTemplateSuccessTest() {
		TemplateDto template = TemplateBuilder.buildTemplateDto();
		try {
			Resolver.recalculateAdditionalFields(template, imagePath, "Test");
			validationService.validateTemplate(template);
			Assert.assertTrue(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.assertTrue(false);
		}
	}

	@Test
	void saveTemplateEmptyPageFormatTest() {
		TemplateDto template = TemplateBuilder.buildTemplateDto();
		template.getPageFormat().setFormat(PageFormat.NONE);
		try {
			Resolver.recalculateAdditionalFields(template, imagePath, "Test");
			validationService.validateTemplate(template);
			Assert.assertTrue(false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.assertTrue(e.getMessage().equals(ValidationServiceImpl.EMPTY_PAGE_FORMAT));
		}
	}

	@Test
	void saveTemplateDefaultFontNotSetTest() {
		TemplateDto template = TemplateBuilder.buildTemplateDto();
		template.getDefaultText().setFontSize(-3);
		template.getDefaultText().setFontColor(null);
		try {
			Resolver.recalculateAdditionalFields(template, imagePath, "Test");
			validationService.validateTemplate(template);
			Assert.assertTrue(false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.assertTrue(e.getMessage().equals(ValidationServiceImpl.INVALID_FONT_SIZE));
		}
	}

}
