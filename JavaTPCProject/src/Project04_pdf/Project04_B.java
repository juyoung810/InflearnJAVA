package Project04_pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class Project04_B {
    public static void main(String[] args) {
        Document doc = new Document();
        try {
            //file 만들기 위해
            FileOutputStream fos = new FileOutputStream("paragraphDemo.pdf");
            //doc과 io 연결해서 적어준다.
            PdfWriter.getInstance(doc,fos);
            doc.open();

            String content = "If you don’t get out of the box you’ve been raised in, you won’t understand how much bigger the world is.";
            //문단 만들기
            Paragraph par1 = new Paragraph(32); // 줄간격 설정(문단 내부)
            // 문단과 문단 여백 주기
            par1.setSpacingBefore(50);
            par1.setSpacingAfter(50);

            for(int i = 0; i< 20;i++){
                Chunk chunk = new Chunk(content); // 큰 문자열
                par1.add(chunk);
            }
            doc.add(par1); // 문서에 생성한 문단 적기

            Paragraph par2 = new Paragraph(); // 간격없이 생성
            for(int i = 0; i< 10;i++){
                par2.add(new Chunk(content));
            }
            doc.add(par2);
            // close 해준다.
            doc.close();

            System.out.println("paragraphDemo 생성");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
