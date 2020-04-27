package com.miticon.pdfgenerator.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FontLoaderServiceImpl implements FontLoaderService {

    @Value("${path.fonts}")
    private String fontPath;

    @Override
    public List<String> getFontList()  {
        File folder = new File(fontPath);
        List<String> fonts = new ArrayList<>();
        for (File file : folder.listFiles())
            fonts.add(file.getAbsolutePath());
        return fonts;
    }

    @Override
    public PDType0Font loadFontToFontDocument(PDDocument document, String fontName, boolean isBold, boolean isItalic) throws IOException {
        if (isBold)
            fontName = fontName.concat("_bold");
        if (isItalic)
            fontName = fontName.concat("_italic");
        return PDType0Font.load(document, new File(fontPath + fontName + ".ttf"));
    }

    public String getFontPath() {
        return fontPath;
    }

}
