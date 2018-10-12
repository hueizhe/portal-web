package com.tony.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.ElementList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreatePdfWithHeadAndFooter {

    public static final String FONT = "resources/fonts/STSONG.ttf";
    public static final String BOLD = "resources/fonts/STSONG.ttf";

    public static Font titleFont;
    public static Font headFont;
    public static Font footerFont;
    static {
        try {
            Font footerFont = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12);
            Font titleFont = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 14);
            Font headFont =  new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    class MyFooter extends PdfPageEventHelper {

        PdfTemplate t;
        PdfContentByte cb;
        Image total;




//        protected Phrase title;

        protected List<Phrase> headerList;

//        public void setTitle(Phrase title) {
//            this.title = title;
//        }


        public void setHeaderList(List<Phrase> headerList) {
            this.headerList = headerList;
        }

        protected ElementList footer;

        MyFooter() throws IOException, DocumentException {

        }




        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            t = writer.getDirectContent().createTemplate(30, 16);
            try {
                total = Image.getInstance(t);
                total.setRole(PdfName.ARTIFACT);
                titleFont = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 14);
                headFont =  new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
                footerFont = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12);

            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            } catch (IOException ioe) {
                throw new ExceptionConverter(ioe);
            }
        }

        public void onEndPage(PdfWriter writer, Document document) {
            cb = writer.getDirectContent();

            Phrase title = new Phrase("往来业务对账单", footerFont);

            Phrase make = new Phrase("制单人： _____", footerFont);
            Phrase review = new Phrase(String.format("审核人: _____"), footerFont);

            Phrase footer = new Phrase("打印时间: _____", footerFont);
            Phrase pageInfo =new Phrase(String.format("第 %d 页/共 ", writer.getPageNumber()), footerFont);

            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    title,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.top() + 10, 0);



            //head
//            for(int i = 0; i < headerList.size(); i = i+2){
//
//                System.out.println(i);
//                buildFooter(headerList.get(i), headerList.get(i+1),0, -1, document.left(), document.top()- (i*20), writer);
//
//            }
            buildFooter(make, review,0, -1, document.left(), document.top(), writer);
            buildFooter(make, review,0, -1, document.left(), document.top()-20, writer);
            //footer
            buildFooter(make, review,0, -1, 36,60, writer);
            //footer
            PdfPTable table = new PdfPTable(3);
            Phrase blank = new Phrase("___", footerFont);
            try {
                table.setWidths(new int[]{24, 24, 2});
                table.setTotalWidth(530);
                table.getDefaultCell().setFixedHeight(20);
                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                table.addCell(footer);

                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(pageInfo);

                PdfPCell cell = new PdfPCell(total);
                cell.setBorder(Rectangle.NO_BORDER);
                table.addCell(cell);


                PdfContentByte canvas = writer.getDirectContent();
                canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
                table.writeSelectedRows(0, -1, 36, 30, canvas);
                canvas.endMarkedContentSequence();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }



        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(t, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1 +" 页"), footerFont),
                    2, 2, 0);
        }


        public void buildFooter(Phrase footerRight, Phrase footerLeft,final int rowStart, final int rowEnd, final float xPos, final float yPos, PdfWriter writer ){


            PdfPTable table = new PdfPTable(2);
            Phrase blank = new Phrase("___", footerFont);
            try {
                table.setWidths(new int[]{30, 30});
                table.setTotalWidth(530);
                table.getDefaultCell().setFixedHeight(20);
                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                table.addCell(footerRight);

                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(footerLeft);

                PdfContentByte canvas = writer.getDirectContent();
                canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
                table.writeSelectedRows(rowStart, rowEnd, xPos, yPos, canvas);
                canvas.endMarkedContentSequence();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }



    }

    public static final String DEST = "results/events/page_footer_head_"+ (int)(Math.random() *100) +".pdf";

    //TODO main
    public static void main(String[] args) throws IOException, DocumentException {



        File file = new File(DEST);
        System.out.println(DEST);
        file.getParentFile().mkdirs();


        PdfDocument pdfDocument = new PdfDocument();

        List<Phrase> headerList = new ArrayList<Phrase>();
        headerList.add(new Phrase(String.format("对账单发送单位： %s", "wangpengwe1"), footerFont));
        headerList.add(new Phrase(String.format("对账单接收单位： %s", "wangpengwe2"), footerFont));
        headerList.add(new Phrase(String.format("对账期间： %s", "wangpengwe3"), footerFont));
        headerList.add(new Phrase(String.format("发送单位： %s", "wangpengwe4"), footerFont));
        pdfDocument.setHeaderList(headerList);
        pdfDocument.setTitle("往来业务对账单");

        new CreatePdfWithHeadAndFooter().createPdf(DEST,pdfDocument);
    }

    public void createPdf(String filename, PdfDocument pdfDocument) throws IOException, DocumentException {


        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        Phrase getTitle1 = new Phrase(pdfDocument.getTitle(), titleFont);
        MyFooter event = new MyFooter();
        event.setHeaderList(pdfDocument.getHeaderList());
     //   event.setTitle(getTitle1);
        writer.setPageEvent(event);
        // step 3
        document.open();
        // step 4
        for (int i = 0; i < 3; ) {
            i++;
            document.add(new Paragraph("" + i));
            document.newPage();
        }
        // step 5
        document.close();
    }




}
