package com.miticon.pdfgenerator.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import java.io.IOException;
import java.util.List;

public interface FontLoaderService {

    List<String> getFontList();
    PDType0Font loadFontToFontDocument(PDDocument document, String fontName, boolean isBold, boolean isItalic) throws IOException;

}
