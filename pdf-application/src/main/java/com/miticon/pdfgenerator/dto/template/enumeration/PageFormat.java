package com.miticon.pdfgenerator.dto.template.enumeration;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

public enum PageFormat {
    NONE,
    LETTER,
    LEGAL,
    A0,
    A1,
    A2,
    A3,
    A4,
    A5,
    A6;

    public PDRectangle getPDRectangle() {
        switch (this) {
            case LETTER: return PDRectangle.LETTER;
            case LEGAL: return PDRectangle.LEGAL;
            case A0: return PDRectangle.A0;
            case A1: return PDRectangle.A1;
            case A2: return PDRectangle.A2;
            case A3: return PDRectangle.A3;
            case A4: return PDRectangle.A4;
            case A5: return PDRectangle.A5;
            case A6: return PDRectangle.A6;
        }
        return PDRectangle.A4;
    }

}
