package com.tony.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.ElementList;
import com.tony.pdf.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CreatePdfWithHeadAndFooter {

    public static final String DEST = "results/events/page_footer_head_"+ (int)(Math.random() *100) +".pdf";

    public static final String FONT = "resources/fonts/STSONG.ttf";
    public static final String BOLD = "resources/fonts/STSONG.ttf";
    public static final  Rectangle rectangle = new Rectangle(32, 70, 563, 720);

    public static Font titleFont;
    public static Font headFont;
    public static Font footerFont;
    public static Font textfont;
    static {
        try {
            Font footerFont = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12);
            Font titleFont  = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 14);
            Font headFont   = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
            Font textfont   = new Font(BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    class CustomHeadFooter extends PdfPageEventHelper {

        PdfTemplate t;
        PdfContentByte canvas;
        Image total;

        protected Phrase title;

        protected PdfPTable table;

        protected float tableHeight;

        protected List<Phrase> headerList;


        public float getTableHeight() {
            return tableHeight;
        }

        public void setTitle(Phrase title) {
            this.title = title;
        }

        public void setHeaderList(List<Phrase> headerList) {
            this.headerList = headerList;
        }

        protected ElementList footer;

        public CustomHeadFooter() throws IOException, DocumentException {



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
            canvas = writer.getDirectContentUnder();


            Rectangle rectPage2 = new Rectangle(32, 70, 563, 720);
            rectPage2.setBorder(Rectangle.BOX);
            rectPage2.setBorderWidth(1);
            rectPage2.setBorderColor(BaseColor.RED);

            canvas.rectangle(rectPage2);


            table = new PdfPTable(5);
            table.setTotalWidth(530);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table.setLockedWidth(true);
            table.addCell(new Phrase("对账单发送单位: ", headFont));
            table.addCell("Header row 2sdffsd");
            table.addCell("");
            table.addCell(new Phrase("对账单接收单位：", headFont));
            table.addCell("Header");
            table.addCell(new Phrase("对账期间：", headFont));
            table.addCell("Header");
            table.addCell("");
            table.addCell(new Phrase("发送日期", headFont));
            table.addCell("Header");
            tableHeight = table.getTotalHeight();
            System.out.println("tableHeight :  " + tableHeight);

            table.writeSelectedRows(0, -1,
                    document.left(),
                    document.top()- 20,
                    writer.getDirectContent());



            Phrase make = new Phrase("制单人： _____", footerFont);
            Phrase review = new Phrase(String.format("审核人: _____"), footerFont);

            Phrase footer = new Phrase("打印时间: _____", footerFont);
            Phrase pageInfo =new Phrase(String.format("第 %d 页/共 ", writer.getPageNumber()), footerFont);

            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER,
                    this.title,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.top() - 10, 0);


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
                    new Phrase(String.valueOf(writer.getPageNumber() +" 页"), footerFont),
                    2, 2, 0);
        }



        public void buildFooter(Phrase footerRight, Phrase footerLeft,
                                final int rowStart, final int rowEnd, final float xPos, final float yPos, PdfWriter writer ){


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


    /**
     * 创建一个表格对象
     *
     * @param colNumber 表格的列数
     * @return 生成的表格对象
     */
    public static  PdfPTable createTable(int colNumber) {
        int maxWidth = 530;
        PdfPTable table = new PdfPTable(colNumber);
        try {
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }


    /**
     * 为表格添加一个内容
     *
     * @param value 值
     * @param font  字体
     * @param horizontalAlign 对齐方式
     * @return 添加的文本框
     */
    public static PdfPCell createCell(String value, Font font,int verticalAlignment, int horizontalAlign) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(verticalAlignment);
        cell.setHorizontalAlignment(horizontalAlign);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }




    /**
     *
     * @param document
     * @param event
     * @param pdfDocument
     * @param head
     * @param list
     * @param colNum
     * @param <T>
     */
    public static  <T>  PdfPTable generatePDF(Document document, CustomHeadFooter event, PdfDocument pdfDocument,
                                              String[] head, List<T> list, int colNum, PdfWriter writer ) {
        Class classType = list.get(0).getClass();

        // 创建一个 n 列的表格
        PdfPTable table = createTable(colNum);

        //     System.out.println("event.getTableHeight():  " + event.getTableHeight());

        //设置表头
        for (int i = 0; i < colNum; i++) {
            table.addCell(createCell(head[i], footerFont, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER));
        }


        table.setHeaderRows(1);

        String[] heads = {"namdde", "sex21", "adress23", "height12", "age23", "jj32"};
        for (int j=0; j < 100; j++){
            for (int i = 0; i < colNum; i++) {
                table.addCell(createCell(heads[i], footerFont, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER));
            }
        }




        if (null != list && list.size() > 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                T t = list.get(i);
                for (int j = 0; j < colNum; j++) {
                    //获得首字母
                    String firstLetter = head[j].substring(0, 1).toUpperCase();

                    //获得get方法,getName,getAge等
                    String getMethodName = "get" + firstLetter + head[j].substring(1);

                    Method method;
                    try {
                        //通过反射获得相应的get方法，用于获得相应的属性值
                        method = classType.getMethod(getMethodName, new Class[]{});
                        event.setTitle(new Phrase(pdfDocument.getTitle()+i, titleFont));
                        //添加数据
                        //table.addCell(createCell(method.invoke(t, new Class[]{}).toString(), textfont));

                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
//                    catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }

        return  table;
    }





    /**
     *
     * @param filename
     * @param pdfDocument
     * @param head
     * @param list
     * @param <T>
     * @throws IOException
     * @throws DocumentException
     */
    public <T> void createPdf(String filename, PdfDocument pdfDocument, String[] head, List<T> list) throws IOException, DocumentException {

        CustomHeadFooter event = new CustomHeadFooter();



        // step 1
        Document document = new Document(PageSize.A4, 36, 36, 20 , 36);

        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));


        writer.setPageEvent(event);
        // step 3
        document.open();

        PdfPTable pdfPTable = generatePDF(document, event, pdfDocument, head, list, head.length, writer);
        pdfPTable.writeSelectedRows(0,-1,700,100 ,writer.getDirectContent());


//        PdfContentByte cb = writer.getDirectContent();
//        ColumnText ct = new ColumnText(cb);
//        ct.setSimpleColumn(new Phrase("ss"), 60, 300, 100, 500, 15, Element.ALIGN_CENTER);
//        ct.go();

        document.add(pdfPTable);
        event.setTitle(new Phrase(pdfDocument.getTitle(), titleFont));

        // step 4
//        for (int i = 0; i < 3; i++) {
//            document.add(new Phrase("ss"));
//            event.setTitle(new Phrase(pdfDocument.getTitle(), titleFont));
//            document.newPage();
//        }

        // step 5
        document.close();
    }




}
