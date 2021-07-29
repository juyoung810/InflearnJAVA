package Project04_pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class Project04_A {
    public static void main(String[] args) {
        //iText API
        // table  만들기
        String[] title = new String[]{"제목","저자","출판사","이미지 URL"};
        String[][] rows =  new String[][]{
                {"물리법칙의 이해","리처드 파인먼","해나무","https://i.pinimg.com/originals/d9/4a/49/d94a495eca526d82ebbe0640aea413a9.jpg"},
                {"Java의 정석","남궁성","도우출판","https://i.pinimg.com/originals/d9/4a/49/d94a495eca526d82ebbe0640aea413a9.jpg"},
                {"리눅스 프로그래밍","창병모","생능출판","https://i.pinimg.com/originals/d9/4a/49/d94a495eca526d82ebbe0640aea413a9.jpg"}

        };

        // 메모리에 pdf 파일 임시로 만들어서 그 안에 data 만든 다음, 실제 pdf 에 쓴다.
        // 임시 파일: document라고 부른다. (A4 사이즈로)
        Document doc = new Document(PageSize.A4);
        try {
            // 문서를 Pdf 로 바꾼다.
            PdfWriter.getInstance(doc,new FileOutputStream(new File("book.pdf"))); // 이 document를 해당 출력 스트림에 연결해서 pdf 파일로 만든다. (연결만 한것)
            // document에 내용 입력 위해 연다.
            doc.open();
            // 한글 폰트
            BaseFont bf = BaseFont.createFont("NGULIM.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED) ; // 폰트이름, 수평 작성, 폰트는 외부 폰트이다.
            // title 폰트, 실제 데이터에 사용할 폰트 다르게 설정한다.
            Font fontTitle = new Font(bf,12); // 크기 지정
            Font fontrows = new Font(bf,10);

            // table를 만든다.
            PdfPTable table = new PdfPTable(title.length); // column 개수 지정 -> title 길이 만큼
            table.setWidthPercentage(100); // table 의 폭 지정
            float[] colwidth = new float[]{20f,15f,15f,30f};// cell의 폭 지정 (column의 폭)
            table.setWidths(colwidth);

            for(String header : title){
                PdfPCell cell = new PdfPCell(); // cell 만든다.
                cell.setHorizontalAlignment(Element.ALIGN_CENTER); // 수평 정렬 지정
                //cell의 상하좌우 공백 설정
                cell.setPadding(10);
                // cell의 배경색 회색 음영으로 지정
                cell.setGrayFill(0.9f);
                // cell안의 실제 글자는 문장으로 만들어 줘야한다.
                cell.setPhrase(new Phrase(header,fontTitle)); // 내용, 폰트 지정
                //cell를 table에 부착시킨다.
                table.addCell(cell);
            } // title 완성
            table.completeRow(); // row 하나 완성되면 명시한다.



            // pdf 닫은 다음 수정해야 내용 들어가진다.
            for(String[] row: rows){
                for(String data: row){
                    // 글자를 먼저 만든다.
                    Phrase phrase = new Phrase(data,fontrows);
                    //cell를 만든다.
                    PdfPCell cell = new PdfPCell(phrase);
                    // 정렬을 설정한다.
                    // 수직 정렬
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    // 상하좌우 각각 padding(여백) 설정한다.
                    cell.setPaddingTop(20);
                    cell.setPaddingRight(30);
                    cell.setPaddingBottom(20);
                    cell.setPaddingLeft(30);

                    // table에 만들어진 cell 붙인다.
                    table.addCell(cell);
                }
                //row 완성될때 마다.
                table.completeRow();
            }

            // 하단에 두 셀 합치기
            PdfPCell cell4 = new PdfPCell(new Phrase("Cell 5"));
            cell4.setColspan(2); // 두개의 column 합친다.

            PdfPCell cell5 = new PdfPCell(new Phrase("Cell 6"));
            cell5.setColspan(2); // 두개의 column 합친다.

            // table에 붙인다.
            table.addCell(cell4);
            table.addCell(cell5);
            doc.addTitle("PDF Table Demo");
            doc.add(table); // 문서에 table 부착한다.
            System.out.println("table 생성 완료");

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            doc.close(); // 실패하던 성공하면, 열려있던 doc 닫아준다.
        }

    }

}
