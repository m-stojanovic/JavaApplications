package com.miticon.pdfgenerator.pdf;

import com.miticon.pdfgenerator.dto.template.elements.ElementImageDto;
import com.miticon.pdfgenerator.dto.template.elements.ElementRectDto;
import com.miticon.pdfgenerator.dto.template.elements.ElementTableDto;
import com.miticon.pdfgenerator.dto.template.elements.ElementTextDto;
import com.miticon.pdfgenerator.dto.template.subelements.PageFormatDto;
import com.miticon.pdfgenerator.dto.template.subelements.StyleDto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;
import java.util.Map;

public interface PdfUtilsService {

    void drawAddress(PDDocument doc, PDPageContentStream content, PageFormatDto pageFormatDto, Map<String, String> placeHolders) throws IOException;

    void drawRect(PDDocument doc, PDPageContentStream content, PageFormatDto pageFormatDto, StyleDto defaultStyle, ElementRectDto rectElement) throws IOException;
    void drawText(PDDocument doc, PDPageContentStream content, int currentPosition, PageFormatDto pageFormatDto, StyleDto defaultStyle, ElementTextDto textElement, Map<String, String> placeHolders) throws IOException;
    void drawImage(PDDocument doc, PDPageContentStream content, int currentPosition, PageFormatDto pageFormatDto, ElementImageDto imageElement) throws IOException;

    ResizableDrawStatus drawTable(PDDocument doc, PDPageContentStream content, int currentPosition, PageFormatDto pageFormatDto, StyleDto headerStyle, StyleDto cellStyle, ElementTableDto tableElement, Map<String, String> placeHolders, int startRow, int footerHeight) throws IOException;
    //ResizableDrawStatus drawParagraph(PDDocument doc, PDPageContentStream content, int currentPosition, PageFormatDto pageFormatDto, StyleDto defaultStyle, ElementTextDto textElement, Map<String, String> placeHolders) throws IOException;

}
