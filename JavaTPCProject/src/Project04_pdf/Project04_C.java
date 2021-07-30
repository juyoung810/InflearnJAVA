package Project04_pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class Project04_C {
    public static void main(String[] args) {

        Document doc = new Document();
        try {
            //파일과 doc 연결해준다
            PdfWriter.getInstance(doc,new FileOutputStream("ImageDemo.pdf"));
            doc.open();

            // 파일 이용
            String fileName = "baekjoon.png";
            Image image = Image.getInstance(fileName);
            doc.add(image);

            //url 이용 이미지 쓰기
            String url = "https://ssl.gstatic.com/ui/v1/icons/mail/rfr/logo_gmail_lockup_default_1x_r2.png";
            //이미지 객체 생성
            image = Image.getInstance(url);
            doc.add(image);

            System.out.println("생성완료");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            doc.close();
        }
    }
}
