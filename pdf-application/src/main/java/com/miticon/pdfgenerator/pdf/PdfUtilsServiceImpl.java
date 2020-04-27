package com.miticon.pdfgenerator.pdf;

import com.miticon.pdfgenerator.dto.template.elements.ElementImageDto;
import com.miticon.pdfgenerator.dto.template.elements.ElementRectDto;
import com.miticon.pdfgenerator.dto.template.elements.ElementTableDto;
import com.miticon.pdfgenerator.dto.template.elements.ElementTextDto;
import com.miticon.pdfgenerator.dto.template.enumeration.HeaderVisibility;
import com.miticon.pdfgenerator.dto.template.subelements.ColumnTableDto;
import com.miticon.pdfgenerator.dto.template.subelements.PageFormatDto;
import com.miticon.pdfgenerator.dto.template.subelements.StyleDto;
import com.miticon.pdfgenerator.service.FontLoaderService;
import com.miticon.pdfgenerator.service.StorageService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

@Service
public class PdfUtilsServiceImpl implements PdfUtilsService {

    @Value("${address.bridgetec.image}")
    private String qrCode;

    @Autowired
    private FontLoaderService fontLoaderService;

    @Autowired
    private StorageService storageService;

    @Override
    public void drawRect(PDDocument doc, PDPageContentStream content, PageFormatDto pageFormatDto, StyleDto defaultStyle, ElementRectDto rectElement) throws IOException {
        StyleDto style = Resolver.calculateStyle(defaultStyle, rectElement, -1, -1, "", false);
        placeRect(content, style, rectElement.getLeft(), (int)pageFormatDto.getFormat().getPDRectangle().getHeight() - rectElement.getTop() - rectElement.getHeight(), rectElement.getWidth(), rectElement.getHeight());
    }

    @Override
    public void drawText(PDDocument doc, PDPageContentStream content, int currentPosition, PageFormatDto pageFormatDto, StyleDto defaultStyle, ElementTextDto textElement, Map<String, String> placeHolders) throws IOException {
        String text = Resolver.applyPlaceholder(textElement.getTextValue(), placeHolders);
        StyleDto style = Resolver.calculateStyle(defaultStyle, textElement, -1, -1, text, false);
        textElement.setHeight(style.getFontSize());
        PDType0Font font = fontLoaderService.loadFontToFontDocument(doc, style.getFontName(), style.isFontBold(), style.isFontItalic());
        Rectangle rectangle = calculateTextRect(font, style, pageFormatDto, textElement.getLeft(), currentPosition + textElement.getTop() + textElement.getHeight(), textElement.getWidth(), textElement.getHeight(), text);
        placeRect(content, style, (int) rectangle.getX() - style.getHorizontalOffset(), (int) (pageFormatDto.getFormat().getPDRectangle().getHeight() - rectangle.getY()) - style.getVerticalOffset() - 2, (int) rectangle.getWidth() + 2 * style.getHorizontalOffset(), (int)rectangle.getHeight() + 2 * style.getVerticalOffset() + 3);
        placeText(font, content, style, (int) rectangle.getX(), (int) (pageFormatDto.getFormat().getPDRectangle().getHeight() - rectangle.getY()), (int) rectangle.getWidth(), (int) rectangle.getHeight(), text);
    }

    @Override
    public void drawImage(PDDocument doc, PDPageContentStream content, int currentPosition, PageFormatDto pageFormatDto, ElementImageDto imageElement) throws IOException {
        PDImageXObject pdImage = PDImageXObject.createFromFile(imageElement.getFileName(), doc);
        content.drawImage(pdImage, imageElement.getLeft(), pageFormatDto.getFormat().getPDRectangle().getHeight() - pdImage.getWidth() - imageElement.getTop() - currentPosition, pdImage.getWidth(), pdImage.getHeight());
    }

    public ResizableDrawStatus drawTable(PDDocument doc, PDPageContentStream content, int currentPosition, PageFormatDto pageFormatDto, StyleDto headerStyle, StyleDto cellStyle, ElementTableDto tableElement, Map<String, String> placeHolders, int startRow, int footerHeight) throws IOException {
        int xPos = pageFormatDto.getMarginLeft() + tableElement.getLeft();
        boolean drawHeader = (tableElement.getHeaderVisibility() == HeaderVisibility.ALWAYS) || (tableElement.getHeaderVisibility() == HeaderVisibility.FIRST_PAGE && startRow == 0);
        int yPos = currentPosition + tableElement.getTop();
        int colIndex = 0;
        if (drawHeader) {
            yPos = yPos + tableElement.getHeaderHeight();
            for (ColumnTableDto column : tableElement.getColumns()) {
                String colText = Resolver.applyPlaceholder(column.getTitleText(), placeHolders);
                StyleDto style = Resolver.calculateStyle(headerStyle, tableElement, colIndex, 0, colText, false);
                placeRect(content, style, xPos, (int) pageFormatDto.getFormat().getPDRectangle().getHeight() - yPos, column.getColumnWidth(), tableElement.getHeaderHeight());
                PDType0Font font = fontLoaderService.loadFontToFontDocument(doc, style.getFontName(), style.isFontBold(), style.isFontItalic());
                Rectangle rectangle = calculateTextRect(font, style, pageFormatDto, xPos, yPos, column.getColumnWidth(), tableElement.getHeaderHeight(), colText);
                placeText(font, content, style, (int) rectangle.getX(), (int) (pageFormatDto.getFormat().getPDRectangle().getHeight() - rectangle.getY()), (int) rectangle.getWidth(), (int) rectangle.getHeight(), colText);
                xPos = xPos + column.getColumnWidth();
                colIndex++;
            }
            yPos = yPos + tableElement.getRowHeight();
        }
        else {
            yPos = yPos + tableElement.getRowHeight();
            tableElement.setHeaderHeight(0);
        }
        int rowIndex = startRow;
        while (true) {
            if ((int) (pageFormatDto.getFormat().getPDRectangle().getHeight()) - yPos < pageFormatDto.getMarginBottom() + footerHeight)
                return new ResizableDrawStatus(false, rowIndex - 1,  tableElement.getHeaderHeight() + (rowIndex - startRow) * tableElement.getRowHeight());
            xPos = pageFormatDto.getMarginLeft() + tableElement.getLeft();
            String nextFirstColumnText = tableElement.getColumns().get(0).getCellText().replace("[i]", "[" + (rowIndex + 1) + "]");
            boolean lastRow = placeHolders.get(nextFirstColumnText) == null;
            colIndex = 0;
            for (ColumnTableDto column : tableElement.getColumns()) {
                String cellText = column.getCellText().replace("[i]", "[" + rowIndex + "]");
                cellText = cellText.replace(cellText, placeHolders.get(cellText));
                StyleDto style = Resolver.calculateStyle(cellStyle, tableElement, colIndex, rowIndex + 1, cellText, lastRow);
                placeRect(content, style, xPos, (int) pageFormatDto.getFormat().getPDRectangle().getHeight() - yPos + tableElement.getTop(), column.getColumnWidth(), tableElement.getRowHeight());
                PDType0Font font = fontLoaderService.loadFontToFontDocument(doc, style.getFontName(), style.isFontBold(), style.isFontItalic());
                Rectangle rectangle = calculateTextRect(font, style, pageFormatDto, xPos, yPos + tableElement.getTop(), column.getColumnWidth(), tableElement.getRowHeight(), cellText);
                placeText(font, content, style, (int) rectangle.getX(), (int) (pageFormatDto.getFormat().getPDRectangle().getHeight() - rectangle.getY()), (int)rectangle.getWidth(), (int) rectangle.getHeight(), cellText);
                xPos = xPos + column.getColumnWidth();
                colIndex++;
            }
            yPos = yPos + tableElement.getRowHeight();
            rowIndex++;
            if (lastRow)
                return new ResizableDrawStatus(true, rowIndex - 1, tableElement.getHeaderHeight() + (rowIndex - startRow) * tableElement.getRowHeight());
        }
    }

    @Override
    public void drawAddress(PDDocument doc, PDPageContentStream content, PageFormatDto pageFormatDto, Map<String, String> placeHolders) throws IOException {
        StyleDto addressStyle = new StyleDto();
        addressStyle.setFontSize(6);
        addressStyle.setFontBold(false);
        StyleDto boldAddressStyle = new StyleDto();
        boldAddressStyle.setFontSize(8);
        boldAddressStyle.setFontBold(true);
        StyleDto borderStyle = new StyleDto();
        borderStyle.setBorders(1, "#000000");
        borderStyle.setHorizontalOffset(0);
        borderStyle.setVerticalOffset(0);
        int startPos = (int) (pageFormatDto.getFormat().getPDRectangle().getHeight() - 128);
        placeRect(content, borderStyle, 57, startPos - 128, 241, 128);
        placeRect(content, borderStyle, 65, startPos - 119, 150, 60);
        PDType0Font font = fontLoaderService.loadFontToFontDocument(doc, boldAddressStyle.getFontName(), boldAddressStyle.isFontBold(), boldAddressStyle.isFontItalic());
        placeText(font, content, boldAddressStyle, 91, startPos - 35, 150, 17, Resolver.applyPlaceholder("DV", placeHolders));
        font = fontLoaderService.loadFontToFontDocument(doc, addressStyle.getFontName(), addressStyle.isFontBold(), addressStyle.isFontItalic());
        placeText(font, content, addressStyle, 57, startPos - 20, 241, 17, Resolver.applyPlaceholder("<%TITLE_1%>", placeHolders));
        placeText(font, content, addressStyle, 110, startPos - 35, 150, 17, Resolver.applyPlaceholder("<%TITLE_2%>", placeHolders));
        PDImageXObject pdImage = PDImageXObject.createFromFile(qrCode, doc);
        content.drawImage(pdImage, 220, startPos - 57, 27, 27);
        startPos = startPos - 69;
        placeText(font, content, addressStyle, 65, startPos, 150, 10, Resolver.applyPlaceholder("<%ADDRESS_1%>", placeHolders));
        placeText(font, content, addressStyle, 65, startPos - 9, 150, 10, Resolver.applyPlaceholder("<%ADDRESS_2%>", placeHolders));
        placeText(font, content, addressStyle, 65, startPos - 18, 150, 10, Resolver.applyPlaceholder("<%ADDRESS_3%>", placeHolders));
        placeText(font, content, addressStyle, 65, startPos - 27, 150, 10, Resolver.applyPlaceholder("<%ADDRESS_4%>", placeHolders));
        placeText(font, content, addressStyle, 65, startPos - 36, 150, 10, Resolver.applyPlaceholder("<%ADDRESS_5%>", placeHolders));
        placeText(font, content, addressStyle, 65, startPos - 45, 150, 10, Resolver.applyPlaceholder("<%ADDRESS_6%>", placeHolders));
    }

    public Rectangle calculateTextRect(PDType0Font font, StyleDto style, PageFormatDto pageFormatDto, int left, int top, int width, int height, String text) throws IOException {
        Rectangle textPosition = new Rectangle();
        float stringWidth = font.getStringWidth(text) / 1000 * style.getFontSize();
        float stringHeight = (font.getFontDescriptor().getCapHeight()) / 1000 * style.getFontSize();
        float textPositionX = 0;
        switch (style.getHorizontalAlignment()) {
            case LEFT:
                textPositionX = left + Math.abs(style.getHorizontalOffset());
                break;
            case CENTER:
                textPositionX = left + (width - stringWidth) / 2 + style.getHorizontalOffset();
                break;
            case RIGHT:
                textPositionX = left + width - stringWidth - Math.abs(style.getHorizontalOffset());
                break;
        }
        float textPositionY = 0;
        switch (style.getVerticalAlignment()) {
            case TOP:
                textPositionY = top - height + stringHeight + Math.abs(style.getVerticalOffset());
                break;
            case CENTER:
                textPositionY = top - (height - stringHeight) / 2 + style.getVerticalOffset();
                break;
            case BOTTOM:
                textPositionY = top - Math.abs(style.getVerticalOffset());
                break;
        }
        textPosition.setRect(Math.round(textPositionX), Math.round(textPositionY), Math.round(stringWidth), Math.round(stringHeight));
        return textPosition;
    }

    public void placeText(PDType0Font font, PDPageContentStream content, StyleDto style, int left, int top, int width, int height, String text) throws IOException {
        content.beginText();
        content.setFont(font, style.getFontSize());
        content.setNonStrokingColor(Color.decode(style.getFontColor()));
        content.newLineAtOffset(left, top);
        content.showText(text);
        content.endText();
        if (style.isFontUnderline()) {
            content.setNonStrokingColor(Color.decode(style.getFontColor()));
            content.addRect(left, top + height - 2, width, 1);
            content.fill();
        }
    }

    private void placeRect(PDPageContentStream content, StyleDto style, int left, int top, int width, int height) throws IOException {
        if (!style.getBackgroundColor().equals("#FFFFFF")) {
            content.setNonStrokingColor(Color.decode(style.getBackgroundColor()));
            content.addRect(left, top, width, height);
            content.fill();
        }
        if (style.getBorderBottomSize() > 0) {
            content.setNonStrokingColor(Color.decode(style.getBorderBottomColor()));
            content.addRect(left, top, width, style.getBorderBottomSize());
            content.fill();
        }
        if (style.getBorderTopSize() > 0) {
            content.setNonStrokingColor(Color.decode(style.getBorderTopColor()));
            content.addRect(left, top + height, width + style.getBorderTopSize(), style.getBorderTopSize());
            content.fill();
        }
        if (style.getBorderLeftSize() > 0) {
            content.setNonStrokingColor(Color.decode(style.getBorderLeftColor()));
            content.addRect(left, top, style.getBorderLeftSize(), height);
            content.fill();
        }
        if (style.getBorderRightSize() > 0) {
            content.setNonStrokingColor(Color.decode(style.getBorderRightColor()));
            content.addRect(left + width, top, style.getBorderRightSize(), height);
            content.fill();
        }
    }

}
