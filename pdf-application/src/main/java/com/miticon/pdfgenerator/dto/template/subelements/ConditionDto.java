package com.miticon.pdfgenerator.dto.template.subelements;

import com.miticon.pdfgenerator.dto.template.enumeration.ConditionType;
import com.miticon.pdfgenerator.pdf.Resolver;

public class ConditionDto {

    private ConditionType conditionType = ConditionType.ALWAYS_APPLY;
    private int x = -1;
    private int y = -1;
    private String param_1 = "";
    private String param_2 = "";
    private String param_3 = "";

    public ConditionDto() {}

    public ConditionDto(ConditionType conditionType, int x, int y, String param_1, String param_2, String param_3) {
        this.conditionType = conditionType;
        this.x = x;
        this.y = y;
        this.param_1 = param_1;
        this.param_2 = param_2;
        this.param_3 = param_3;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getParam_1() {
        return param_1;
    }

    public void setParam_1(String param_1) {
        this.param_1 = param_1;
    }

    public String getParam_2() {
        return param_2;
    }

    public void setParam_2(String param_2) {
        this.param_2 = param_2;
    }

    public String getParam_3() {
        return param_3;
    }

    public void setParam_3(String param_3) {
        this.param_3 = param_3;
    }

    public boolean isSuccessful(int pX, int pY, String text, boolean lastRow) {
        if ((x != -1 && pX != x) || (y != -1 && pY != y))
            return false;
        switch (conditionType) {
            case ALWAYS_APPLY:  return true;
            case NUMBER_OVER:   return Resolver.extractNumber(text) > Resolver.extractNumber(param_1);
            case NUMBER_BELOW:  return Resolver.extractNumber(text) < Resolver.extractNumber(param_1);
            case LAST_ROW:      return lastRow;
            default: return false;
        }
    }

}
