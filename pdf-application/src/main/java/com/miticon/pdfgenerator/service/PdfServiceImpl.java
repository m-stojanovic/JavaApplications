package com.miticon.pdfgenerator.service;

import com.miticon.pdfgenerator.dto.data.TemplateDto;
import com.miticon.pdfgenerator.dto.template.elements.*;
import com.miticon.pdfgenerator.dto.template.enumeration.PreciseAddress;
import com.miticon.pdfgenerator.dto.template.subelements.BlockDto;
import com.miticon.pdfgenerator.pdf.PdfUtilsService;
import com.miticon.pdfgenerator.pdf.ResizableDrawStatus;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class PdfServiceImpl implements PdfService {

    @Autowired
    private PdfUtilsService pdfUtilsService;

    private PDDocument doc;
    private PDPage page;
    private PDPageContentStream content;

    @Override
    public PDDocument buildDocument(TemplateDto template, Map<String, String> placeHolders) throws Exception {
        doc = new PDDocument();
        int currentPosition = createPage(template, placeHolders);
        if (template.getPreciseAddress() == PreciseAddress.BRIDGE_TEC)
            pdfUtilsService.drawAddress(doc, content,template.getPageFormat(), placeHolders);
        List<BlockDto> blocks = template.getBlocks();
        for (BlockDto block : blocks) {
            if (currentPosition + block.getOffset() + block.getFixedHeight() > template.getEndPosition()) {
                content.close();
                currentPosition = createPage(template, placeHolders);
            }
            List<ElementDto> allElementsInBlock = block.getElements();
            int sizeOnCurrentPage = drawElements(allElementsInBlock, currentPosition, template, placeHolders);
            if (sizeOnCurrentPage < 0)
                currentPosition = template.getStartPosition() + block.getOffset() - sizeOnCurrentPage;
            else
                currentPosition = currentPosition + block.getFixedHeight() + block.getOffset() + sizeOnCurrentPage;
        }
        content.close();
        return doc;
    }

    private void drawHeader(int currentPosition, TemplateDto template, Map<String, String> placeHolders) throws IOException {
        if (template.getHeader() != null) {
            List<ElementDto> elements = template.getHeader().getElements();
            drawElements(elements, currentPosition, template, placeHolders);
        }
    }

    private void drawFooter(int currentPosition, TemplateDto template, Map<String, String> placeHolders) throws IOException {
        if (template.getFooter() != null) {
            List<ElementDto> allElementsInFooter = template.getFooter().getElements();
            drawElements(allElementsInFooter, currentPosition - template.getFooter().getHeight(), template, placeHolders);
        }
    }

    private int drawElements(List<ElementDto> elements, int currentPosition, TemplateDto template, Map<String, String> placeHolders) throws IOException {
        int sizeOnCurrentPage = 0;
        for (ElementDto element : elements) {
            switch (element.getElementType()) {
                case TEXT:
                    pdfUtilsService.drawText(doc, content, currentPosition, template.getPageFormat(), template.getDefaultText(), (ElementTextDto) element, placeHolders);
                    break;
                case IMAGE:
                    pdfUtilsService.drawImage(doc, content, currentPosition, template.getPageFormat(), (ElementImageDto) element);
                    break;
                case TABLE:
                    int footerHeight = template.getFooter() != null ? template.getFooter().getHeight() : 0;
                    ResizableDrawStatus drawStatus = pdfUtilsService.drawTable(doc, content, currentPosition, template.getPageFormat(), template.getDefaultTableHeader(), template.getDefaultTableCell(), (ElementTableDto) element, placeHolders, 0, footerHeight);
                    sizeOnCurrentPage = drawStatus.getSizeOnCurrentPage();
                    currentPosition = currentPosition + drawStatus.getSizeOnCurrentPage();
                    while (!drawStatus.isFinished()) {
                        content.close();
                        currentPosition = createPage(template, placeHolders);
                        drawStatus = pdfUtilsService.drawTable(doc, content, currentPosition, template.getPageFormat(), template.getDefaultTableHeader(), template.getDefaultTableCell(), (ElementTableDto) element, placeHolders, drawStatus.getDrawnLines() + 1, footerHeight);
                        currentPosition = currentPosition + drawStatus.getSizeOnCurrentPage();
                        sizeOnCurrentPage = -drawStatus.getSizeOnCurrentPage();
                    }
                    break;
                case RECT:
                    pdfUtilsService.drawRect(doc, content, template.getPageFormat(), template.getDefaultRect(), (ElementRectDto) element);
            }
        }
        return sizeOnCurrentPage;
    }

    private int createPage(TemplateDto template, Map<String, String> placeHolders) throws IOException {
        page = new PDPage(template.getPageFormat().getFormat().getPDRectangle());
        doc.addPage(page);
        content = new PDPageContentStream(doc, page);
        int pageNumber = Integer.parseInt(placeHolders.getOrDefault("<%PAGE_NUMBER%>", "0")) + 1;
        placeHolders.put("<%PAGE_NUMBER%>", "" + pageNumber);
        drawHeader(template.getPageFormat().getMarginTop(), template, placeHolders);
        drawFooter((int) (template.getPageFormat().getFormat().getPDRectangle().getHeight() - template.getPageFormat().getMarginBottom()), template, placeHolders);
        return template.getStartPosition();
    }

}
