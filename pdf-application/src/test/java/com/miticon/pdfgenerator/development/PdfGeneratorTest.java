package com.miticon.pdfgenerator.development;

import com.miticon.pdfgenerator.dto.data.TemplateDto;
import com.miticon.pdfgenerator.pdf.Resolver;
import com.miticon.pdfgenerator.service.PdfService;
import com.miticon.pdfgenerator.service.StorageService;
import com.miticon.pdfgenerator.utils.Helper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class PdfGeneratorTest {

    @Autowired
    private StorageService storageService;

    @Autowired
    private PdfService pdfService;

    @Value("${path.templates}")
    private String templatePath;

    @Value("${path.images}")
    private String imagePath;

    //@Ignore
    //@Test
    void generatePdfTest() {
        TemplateDto template = DevTemplateBuilder.build();
        try {
            storageService.saveTemplate("Test", template);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Resolver.recalculateAdditionalFields(template, imagePath, "Test");
        Map<String, String> mapPlaceholders = new HashMap<>();

        mapPlaceholders.put("<%TITLE_1%>", "Absenderzeile – Schriftart Arial, Schriftgröße 6 pt, Breite beliebig");
        mapPlaceholders.put("<%TITLE_2%>", "12 0,55 Deutsche Post");
        mapPlaceholders.put("<%ADDRESS_1%>", "Miticon Club & Miticon Club Avenue Gmbh");
        mapPlaceholders.put("<%ADDRESS_2%>", "Stojanovic Strasse 19 99482 Miticonville");
        mapPlaceholders.put("<%ADDRESS_3%>", "Person");
        mapPlaceholders.put("<%ADDRESS_4%>", "Ajioia Adebioie");
        mapPlaceholders.put("<%ADDRESS_5%>", "Headeppfeld");
        mapPlaceholders.put("<%ADDRESS_6%>", "89555 Rubibi");

        mapPlaceholders.put("<%INVOICE_NUMBER%>", "16700-002004");
        mapPlaceholders.put("<%INVOICE_DATE%>", "06.02.2020");
        mapPlaceholders.put("<%SERVICE_PERIOD%>", "03.02.2020 - 05.02.2020");

        for (int i = 0; i < 4; i++) {
            mapPlaceholders.put("<%QUANTITY[" + i + "]%>", "" + Helper.randomInt(1000));
            mapPlaceholders.put("<%NAME[" + i + "]%>", "Name " + i);
            mapPlaceholders.put("<%VAT[" + i + "]%>", "" + Helper.randomInt(100) + " %");
            mapPlaceholders.put("<%UNIT_PRICE[" + i + "]%>", "" + Helper.randomDoubleInRange(0.01, 1000.0) + " EUR");
            mapPlaceholders.put("<%DISCOUNT[" + i + "]%>", "" + Helper.randomDoubleInRange(-100.0, 0.0) + " %");
            mapPlaceholders.put("<%TOTAL_PRICE[" + i + "]%>", "" + Helper.randomDoubleInRange(-200, 300.0) + " EUR");
        }
        for (int i = 0; i < 3; i++) {
            mapPlaceholders.put("<%TABLE2COLUMN1[" + i + "]%>", "Table 2 column 1 " + i);
            mapPlaceholders.put("<%TABLE2COLUMN2[" + i + "]%>",  "" + Helper.randomDoubleInRange(-100, 300.0) + " EUR");
        }

        mapPlaceholders.put("<%PAYMENT_DATE%>", "14.02.2020");
        mapPlaceholders.put("<%ACCOUNT_NUMBER%>", "DE2134010-1030 BLZ: DEGLEO00D");
        mapPlaceholders.put("<%BLZ%>", "DEGLEO00D");
        mapPlaceholders.put("<%BIC%>", "234879283");
        mapPlaceholders.put("<%IBAN%>", "DE12345000001234500000");
        mapPlaceholders.put("<%COMPANY_NAME%>", "Miticon");
        mapPlaceholders.put("<%COMPANY_ADDRESS%>", "Neka Tamo Adresa");
        try {
            PDDocument document = pdfService.buildDocument(template, mapPlaceholders);
            document.save("/Users/milos.stojanovic/Downloads/Test.pdf");
            document.close();
            Assert.assertTrue(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(false);
        }
    }

}
