package com.miticon.pdfgenerator.development;

import com.miticon.pdfgenerator.utils.ElementBuilder;
import com.miticon.pdfgenerator.utils.Helper;
import com.miticon.pdfgenerator.utils.StyleBuilder;
import com.miticon.pdfgenerator.dto.data.TemplateDto;
import com.miticon.pdfgenerator.dto.template.elements.ElementTableDto;
import com.miticon.pdfgenerator.dto.template.enumeration.*;
import com.miticon.pdfgenerator.dto.template.subelements.*;

import java.util.ArrayList;
import java.util.List;

public class DevTemplateBuilder {

    public static TemplateDto build() {
        TemplateDto template = new TemplateDto();
        template.setName("TemplateExample");
        PageFormatDto pageFormat  = new PageFormatDto(PageFormat.A4, 0);
        pageFormat.setMarginTop(Helper.mmToPointConvertor(25));
        template.setPageFormat(pageFormat);
        template.setDefaultText(StyleBuilder.build(0));
        template.setDefaultRect(StyleBuilder.build(1));
        template.setPreciseAddress(PreciseAddress.BRIDGE_TEC);

        //Block 0
        template.getBlocks().add(new BlockDto());
        template.getBlocks().get(0).setOffset(Helper.mmToPointConvertor(80) - template.getStartPosition());
        template.getBlocks().get(0).getElements().add(ElementBuilder.buildImageElement(20, 160, 5, 5, "logo.png", true));

        //Block 1
        template.getBlocks().add(new BlockDto());
        template.getBlocks().get(1).setOffset(0);
        StyleDto titleStyle = StyleBuilder.build(12, true, 0, AlignmentHorizontal.CENTER, 5, AlignmentVertical.CENTER, 2);
        StyleDto textStyle = StyleBuilder.build(8, true, 0);
        template.getBlocks().get(1).getElements().add(ElementBuilder.buildTextElement(10, 10, 10, 0, 0, "INVOICE", true));
        template.getBlocks().get(1).getElements().get(0).getStyleObject().getStyles().add(titleStyle);
        template.getBlocks().get(1).getElements().add(ElementBuilder.buildTextElement(20, 20, 0, 0, 10, "Please specify when answering", true));
        template.getBlocks().get(1).getElements().get(1).getStyleObject().getStyles().add(textStyle);
        template.getBlocks().get(1).getElements().add(ElementBuilder.buildTextElement(25, 20, 0, 0, 10, "Invoice number: <%INVOICE_NUMBER%>", true));
        template.getBlocks().get(1).getElements().get(2).getStyleObject().getStyles().add(textStyle);
        template.getBlocks().get(1).getElements().add(ElementBuilder.buildTextElement(30, 20, 0, 0, 10, "Date of Invoice: <%INVOICE_DATE%>", true));
        template.getBlocks().get(1).getElements().get(3).getStyleObject().getStyles().add(textStyle);
        template.getBlocks().get(1).getElements().add(ElementBuilder.buildTextElement(35, 20, 0, 0, 10, "Service period: <%SERVICE_PERIOD%>", true));
        template.getBlocks().get(1).getElements().get(4).getStyleObject().getStyles().add(textStyle);

        //Block 2 Table 1
        template.setDefaultTableHeader(StyleBuilder.build(8, true, 1));
        template.setDefaultTableCell(StyleBuilder.build(1));
        template.setDefaultImage(StyleBuilder.build(1));
        template.getBlocks().add(new BlockDto());
        List<ColumnTableDto> columns = new ArrayList<>();
        columns.add(new ColumnTableDto("Quantity", Helper.mmToPointConvertor(20), "<%QUANTITY[i]%>"));
        columns.add(new ColumnTableDto("Name", Helper.mmToPointConvertor(71), "<%NAME[i]%>"));
        columns.add(new ColumnTableDto("VAT.", Helper.mmToPointConvertor(20), "<%VAT[i]%>"));
        columns.add(new ColumnTableDto("Unit price", Helper.mmToPointConvertor(20), "<%UNIT_PRICE[i]%>"));
        columns.add(new ColumnTableDto("Discount", Helper.mmToPointConvertor(20), "<%DISCOUNT[i]%>"));
        columns.add(new ColumnTableDto("Total Price", Helper.mmToPointConvertor(20), "<%TOTAL_PRICE[i]%>"));
        template.getBlocks().get(2).getElements().add(ElementBuilder.buildTableElement(0, 20, 170, 0, columns, true));
        StyleDto headerLineStyle = StyleBuilder.build(ConditionType.ALWAYS_APPLY, -1, 0, 3,true, false, AlignmentHorizontal.NONE, 5, AlignmentVertical.CENTER, -2);
        template.getBlocks().get(2).getElements().get(0).getStyleObject().getStyles().add(headerLineStyle);
        StyleDto vatStyle = StyleBuilder.build(ConditionType.ALWAYS_APPLY, 2, -1, false, false, AlignmentHorizontal.CENTER, 0, AlignmentVertical.CENTER, -2);
        template.getBlocks().get(2).getElements().get(0).getStyleObject().getStyles().add(vatStyle);
        template.getBlocks().get(2).getElements().get(0).getStyleObject().getStyles().add(StyleBuilder.build(ConditionType.ALWAYS_APPLY, 3, -1, false, false, AlignmentHorizontal.RIGHT, 5, AlignmentVertical.CENTER, -2));
        template.getBlocks().get(2).getElements().get(0).getStyleObject().getStyles().add(StyleBuilder.build(ConditionType.ALWAYS_APPLY, 4, -1, false, false, AlignmentHorizontal.RIGHT, 5, AlignmentVertical.CENTER, -2));
        template.getBlocks().get(2).getElements().get(0).getStyleObject().getStyles().add(StyleBuilder.build(ConditionType.ALWAYS_APPLY, 5, -1, false, false, AlignmentHorizontal.RIGHT, 5, AlignmentVertical.CENTER, -2));
        template.getBlocks().get(2).getElements().get(0).getStyleObject().getStyles().add(StyleBuilder.build(ConditionType.NUMBER_OVER, 5, -1, "0","#66BB6A"));
        template.getBlocks().get(2).getElements().get(0).getStyleObject().getStyles().add(StyleBuilder.build(ConditionType.NUMBER_BELOW, 5, -1, "0","#EF5350"));
        template.getBlocks().get(2).setOffset(0);
        ElementTableDto elementTable = (ElementTableDto) template.getBlocks().get(2).getElements().get(0);
        elementTable.setHeaderVisibility(HeaderVisibility.ALWAYS);

        //Block 3 Table 2
        template.getBlocks().add(new BlockDto());
        template.getBlocks().get(3).setOffset(25);
        List<ColumnTableDto> columnsTable2 = new ArrayList<>();
        columnsTable2.add(new ColumnTableDto("Table 2 Column 1", Helper.mmToPointConvertor(100), "<%TABLE2COLUMN1[i]%>"));
        columnsTable2.add(new ColumnTableDto("Table 2 Column 2", Helper.mmToPointConvertor(70), "<%TABLE2COLUMN2[i]%>"));
        template.getBlocks().get(3).getElements().add(ElementBuilder.buildTableElement(0, 20, 170, 0, columnsTable2, true));
        template.getBlocks().get(3).getElements().get(0).getStyleObject().getStyles().add(StyleBuilder.build(ConditionType.ALWAYS_APPLY, 0, -1, false, false, AlignmentHorizontal.RIGHT,  5, AlignmentVertical.CENTER, 0));
        template.getBlocks().get(3).getElements().get(0).getStyleObject().getStyles().add(StyleBuilder.build(ConditionType.ALWAYS_APPLY, 1, -1, false, false, AlignmentHorizontal.RIGHT,  5, AlignmentVertical.CENTER, 0));
        template.getBlocks().get(3).getElements().get(0).getStyleObject().getStyles().add(StyleBuilder.build(ConditionType.LAST_ROW, -1, -1, true, false, AlignmentHorizontal.RIGHT,  5, AlignmentVertical.CENTER, 0));
        elementTable = (ElementTableDto) template.getBlocks().get(3).getElements().get(0);
        elementTable.setHeaderVisibility(HeaderVisibility.NEVER);

        //Block 4
        StyleDto paymentStyle = StyleBuilder.build(8, true, 0, AlignmentHorizontal.CENTER, 0, AlignmentVertical.CENTER, 0);
        template.getBlocks().add(new BlockDto());
        template.getBlocks().get(4).getElements().add(ElementBuilder.buildTextElement(0, 30, 0, 0, 10, "We ask for payment with use <%INVOICE_NUMBER%> until <%PAYMENT_DATE%> at the latest.", true));
        template.getBlocks().get(4).getElements().get(0).getStyleObject().getStyles().add(StyleBuilder.build(6, false, 0));
        template.getBlocks().get(4).getElements().add(ElementBuilder.buildTextElement(15, 30, 30, 0, 10, "Account with Bank of Money, Berlinnerville, Germania", true));
        template.getBlocks().get(4).getElements().get(1).getStyleObject().getStyles().add(paymentStyle);
        template.getBlocks().get(4).getElements().add(ElementBuilder.buildTextElement(20, 30, 30, 0, 10, "Account number: <%ACCOUNT_NUMBER%> BLZ: <%BLZ%>", true));
        template.getBlocks().get(4).getElements().get(2).getStyleObject().getStyles().add(paymentStyle);
        template.getBlocks().get(4).getElements().add(ElementBuilder.buildTextElement(25, 30, 30, 0, 10, "BIC: <%BIC%>, IBAN_#: <%IBAN%>", true));
        template.getBlocks().get(4).getElements().get(3).getStyleObject().getStyles().add(paymentStyle);
        template.getBlocks().get(4).getElements().add(ElementBuilder.buildTextElement(30, 30, 30, 0, 10, "Payable to: <%COMPANY_NAME%>, <%COMPANY_ADDRESS%>", true));
        template.getBlocks().get(4).getElements().get(4).getStyleObject().getStyles().add(paymentStyle);

        //Footer
        template.setFooter(new HeaderFooterDto());
        template.getFooter().setHeight(65);
        template.getFooter().getElements().add(ElementBuilder.buildTextElement(7, 20, 20, 0, 0,  "<%COMPANY_NAME%> <%COMPANY_ADDRESS%> Mali Djole, Veliki Dzodzo HRB 21938120 Amtsgericht Djordjeville", true));
        template.getFooter().getElements().get(0).getStyleObject().getStyles().add(StyleBuilder.build(6, false, "#808080", AlignmentHorizontal.LEFT, 5));
        template.getFooter().getElements().add(ElementBuilder.buildTextElement(7, 20, 20, 0, 0,  "<%PAGE_NUMBER%>", true));
        template.getFooter().getElements().get(1).getStyleObject().getStyles().add(StyleBuilder.build(6, false, "#808080", AlignmentHorizontal.RIGHT, 5));

        return template;
    }

}
