package Project04_pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import kr.inflearn.ExcelVO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Project04_F {
    //1. excel 파일 불러와서 DATA 일기
    //2. pdf 파일에 table작성하기
    public static void main(String[] args) {
        // 이 폴더 내에 존재하기 때문에 이름만 넣어도 된다
        String fileName = "isbn.xls";
        List<ExcelVO> data = new ArrayList<ExcelVO>();
        try(FileInputStream fis = new FileInputStream(fileName)){
            // excel 파일을 읽어서 메모리에 올려준다 -> 이 메모리에 올라간 것(가상의 excel)을 workbook 이라고 한다.
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            // excel에서 첫번째 sheet를 빼온다
            HSSFSheet sheet = workbook.getSheetAt(0);
            // sheet에서 row를 다 가져온다. (열거형으로 가져온다)
            Iterator<Row> rows = sheet.rowIterator();
            // 첫번째는 column 이름이름이므로 하나 이동
            rows.next();
            // 각 row의 정보를 받을 임시 배열
            String[] imsi = new String[5];
            // row 정보를 다 가져온다.
            // row 있으면 다 가져온다,
            while(rows.hasNext()){
                HSSFRow row = (HSSFRow) rows.next(); // Row type으로 return 하기 때문에 downCasting 해준다.
                // row 내의 cell을 다 가져온다.
                Iterator<Cell> cells = row.cellIterator();
                int i = 0;
                while(cells.hasNext()){
                    HSSFCell cell = (HSSFCell) cells.next();
                    imsi[i] = cell.toString();
                    i++;
                    if(i == 5) break; // 마지막 이미지를 가져오지 않기 위해
                }
                // 한번에 데이터를 묶는다.
                ExcelVO vo = new ExcelVO(imsi[0],imsi[1],imsi[2], imsi[3],imsi[4]);
                // arrayList에 넣는다.
                data.add(vo);
            }
            pdf_maker(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void pdf_maker(List<ExcelVO> data) {
        String[] headers = new String[]{
                "제목","저자","출판사","이미지"
        };
        Document doc = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(doc,new FileOutputStream(new File("booklist.pdf")));
            doc.open();

            BaseFont bfont = BaseFont.createFont("NGULIM.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED); // 외부에 있는 폰트 사용
            Font fontHeader = new Font(bfont,12);
            Font fontRow = new Font(bfont,10);


            PdfPTable table = new PdfPTable(headers.length);

            //title 작성
            for(String header : headers){
                PdfPCell cell = new PdfPCell();
                cell.setGrayFill(0.9f);
                cell.setPhrase(new Phrase(header.toUpperCase(),fontHeader));
                table.addCell(cell);
            }
            table.completeRow();

            // 정보 입력
            for(ExcelVO vo: data){
                // 글자 만들기
                Phrase phrase = new Phrase(vo.getTitle(),fontRow);
                table.addCell(new PdfPCell(phrase));

                phrase = new Phrase(vo.getAuthor(),fontRow);
                table.addCell(new PdfPCell(phrase));

                phrase = new Phrase(vo.getCompany(),fontRow);
                table.addCell(new PdfPCell(phrase));

                Image image = Image.getInstance(vo.getImageUrl());
                table.addCell(image);

                table.completeRow();
            }
            doc.addTitle("PDF Table Demo");
            doc.add(table);
            System.out.println("bookList 생성 완료");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            doc.close();
        }
    }
}
