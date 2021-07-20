import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Project03_B {
    public static void main(String[] args) {
        try{
            //  가상의 excel를 만든다.
            Workbook wb = new HSSFWorkbook();
            // 가상의 excel sheet를 만든다.
            Sheet sheet = wb.createSheet("My Sample Excel");
            // 이미지를 is로 읽어온다.
            InputStream is = new FileInputStream("pic.jpg");
            // 이미지를 바이트 단위로 읽어서 메모리에 바이트 배열로 저장한다.
            byte[] bytes = IOUtils.toByteArray(is);
            // 바이트 단위로 저장된 이미지 정보를 메모리에 올리기 위해서
            // 생성된 이미지 주소를 저장한다.
            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);


            // 이미지를 그리기 위해 anchor를 잡는다.
            CreationHelper helper = wb.getCreationHelper();
            // sheet에 이미지를 그리기 위해
            Drawing drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();

            // 이미지 시작 위치
            anchor.setCol1(1);
            anchor.setRow1(2);
            // 이미지 끝나는 위치
            anchor.setCol2(2);
            anchor.setRow2(3);

           Picture picture = drawing.createPicture(anchor,pictureIdx);

            // excel의 cell의 크기를 조정해서 cell을 만든다.
            Cell cell = sheet.createRow(2).createCell(1);
            int w = 20 * 256; // 이미지 width 는 1이 1/256 크기 이기 때문에
            sheet.setColumnWidth(1,w);
            short h = 120 * 20; // 이미지 높이는 1이 1/20 크기이기 때문에
            cell.getRow().setHeight(h);

            // 실제 Excel 파일로 저장한다.
            FileOutputStream fileOut = new FileOutputStream("myfile.xls");
            wb.write(fileOut); // wb안의 내용을 FileoutputStream에 쓴다.
            fileOut.close();
            System.out.println("이미지 생성 성공");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
