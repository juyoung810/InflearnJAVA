package Project04_pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class Project04_D {
    public static void main(String[] args) {
        //https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("ImageScaling.pdf"));
            doc.open();

            String fileName= "googleLogo.png";
            Image image = Image.getInstance(fileName);
            doc.add(image);

            //크기 지정
            fileName= "googleLogo.png";
            image = Image.getInstance(fileName);
            image.scaleAbsolute(200f,200f); // w,h
            doc.add(image);

            // %로 크기 지정
            String url= "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png";
            image = Image.getInstance(url);
            image.scalePercent(200f); // 원래 100f -> 200f 두배크기 되게
            doc.add(image);


            image = Image.getInstance(url);
            image.scaleToFit(100f,200f); // 100f,200f 짜리 크기 박스 만들고 그 안에 이미지 맞춰서 넣는다.
            doc.add(image);

            System.out.println("크기 조절 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            doc.close();
        }

    }

}
