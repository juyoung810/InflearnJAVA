package Project03_Excel;

import io.github.cdimascio.dotenv.Dotenv;
import kr.inflearn.DownloadBroker;
import kr.inflearn.ExcelVO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.management.remote.JMXServerErrorException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDAO {
    private List<ExcelVO> list;
    private HSSFWorkbook wb;

    public ExcelDAO() {
        list = new ArrayList<ExcelVO>();
        wb = new HSSFWorkbook();
    }

    public void excel_input() {
        // 책의 정보를 입력 받아 wb 에 저장한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            HSSFSheet firstSheet = wb.createSheet("BOOK SHEET");
            // title를 저장하기 위한 row를 만든다.
            HSSFRow rowA = firstSheet.createRow(0);
            // 0 번째 로우에 6개의 title을 입력 받아 저장한다.
            HSSFCell cellA = rowA.createCell(0);
            cellA.setCellValue(new HSSFRichTextString("책제목"));
            HSSFCell cellB = rowA.createCell(1);
            cellB.setCellValue(new HSSFRichTextString("저자"));
            HSSFCell cellC = rowA.createCell(2);
            cellC.setCellValue(new HSSFRichTextString("출판사"));
            HSSFCell cellD = rowA.createCell(3);
            cellD.setCellValue(new HSSFRichTextString("isbn"));
            HSSFCell cellE = rowA.createCell(4);
            cellE.setCellValue(new HSSFRichTextString("이미지이름"));
            HSSFCell cellF = rowA.createCell(5);
            cellF.setCellValue(new HSSFRichTextString("이미지"));
            // 책제목, 저자, 출판사를 입력 받아 wb 에 저장한다.
            int i = 1; // row1 부터 실제 데이터를 저장한다.
            while (true) {
                System.out.print("책제목:");
                String title = br.readLine();
                System.out.print("책저자:");
                String author = br.readLine();
                System.out.print("출판사:");
                String company = br.readLine();

                // 입력 받은 정보를 wb에 저장한다.
                HSSFRow rowRal = firstSheet.createRow(i);
                HSSFCell cellTitle = rowRal.createCell(0);
                cellTitle.setCellValue(new HSSFRichTextString(title));
                HSSFCell cellAuthor = rowRal.createCell(1);
                cellAuthor.setCellValue(new HSSFRichTextString(author));
                HSSFCell cellCompany = rowRal.createCell(2);
                cellCompany.setCellValue(new HSSFRichTextString(company));

                // 다음 정보를 입력 받아 저장하기 위해 index + 1
                i++;
                // 입력 받은 정보를 VO로 만들어, 서버에 연결해서 추가 정보를 받기 위해 넘긴다.
                ExcelVO vo = new ExcelVO(title, author, company);
                ExcelVO data = naverSearch(vo); // 넘기는 vo: 3가지 정보 들어있다. // return 되는 vo : 5가지 정보 모두 들어있다.
                // 5가진 정보를 가진 vo를 모두 list에 저장해 한번에 excel에 쓸 수 있도록 한다.
                list.add(data);
                // 또 다른 책의 입력 여부
                System.out.println("계속입력 하시면 Y / 입력종료: N: ");
                String key = br.readLine();
                if (key.equals("N")) break;
            }
            System.out.println("데이터 추출중...........");
            // excel에 wb 내용 실제로 쓴다.
            excel_save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ExcelVO naverSearch(ExcelVO vo) {
        Dotenv dotenv = Dotenv.load();
        // 받아온 정보를 서버 open API 에 넘겨 추가정보(isbn, 이미지 정보)를 저장한다.
        try {
            String URL_STATICMAP = "https://openapi.naver.com/v1/search/book_adv.xml?d_titl"
                    + URLEncoder.encode(vo.getTitle(), "UTF-8")
                    + "&d_auth=" + URLEncoder.encode(vo.getAuthor(), "UTF-8")
                    + "&d_publ=" + URLEncoder.encode(vo.getCompany(), "UTF-8");
            URL url = new URL(URL_STATICMAP); // url이 유효한지 검사한다.
            // 서버와 연결한다.
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("X-Naver-Client-Id", dotenv.get("book_id"));
            con.setRequestProperty("X-Naver-Client-Secret", dotenv.get("book_key"));
            int responseCode = con.getResponseCode();
            // 서버로 부터 정보 읽어오기 위해
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer(); // 문자열 추가 변경시 사용한다.
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            // 읽은 xml 정보를 JSOUP 을 활용하여 파싱해서 원하는 정보를 가져온다.
            Document doc = Jsoup.parse(response.toString());
            Element isbn = doc.select("isbn").first();
            vo.setIsbn(isbn.text().split(" ")[1]);

            String imgDoc = doc.toString();
            String imgTag = imgDoc.substring(imgDoc.indexOf("<img>") + 5); // img> 이후 전부
            String imgURL = imgTag.substring(0, imgTag.indexOf("?"));
            System.out.println(imgURL);
            // imgURL 에서 파일 이름만 가져오기
            String fileName = imgURL.substring(imgURL.lastIndexOf("/") + 1);
            System.out.println(fileName);
            vo.setImageUrl(fileName);

            // 이미지 하드디스크에 저장위한 Thread 생성
            Runnable r = new DownloadBroker(imgURL, fileName);
            //Thread 에서 해당 Runnable를 실행시킨다.
            Thread dLoad = new Thread(r);
            dLoad.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    public void excel_save() {
        //ibsn, img 정보 wb에 생성, 저장한 후 실제 excel file에 wb 내용을 쓴다.
        try {
            HSSFSheet sheet = wb.getSheetAt(0); // 이전에 생성한 sheet에 계속 쓰는것
            if (wb != null && sheet != null) {
                // 모든 row를 가져와 각각 정보를 입력하기 위해
                Iterator rows = sheet.rowIterator();
                rows.next(); // 0번째 row는 title이므로 추가 정보를 입력하지 않는다.
                int i = 0; // ExcelVO가 저장된 list의 index
                while (rows.hasNext()) {

                    HSSFRow row = (HSSFRow) rows.next();
                    HSSFCell cell = row.createCell(3); // isbn 정보를 저장할 cell 생성

                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(list.get(i).getIsbn());

                    cell = row.createCell(4); // 이미지 이름 저장할 cell
                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(list.get(i).getImageUrl());

                    // 이미지 6번째 cell에 쓰기 위해
                    InputStream inputStream = new FileInputStream(list.get(i).getImageUrl());
                    byte[] bytes = IOUtils.toByteArray(inputStream);
                    int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
                    inputStream.close();

                    // 이미지를 그리기 위해 anchor를 잡는다.
                    CreationHelper helper = wb.getCreationHelper();
                    // sheet에 이미지를 그리기 위해
                    Drawing drawing = sheet.createDrawingPatriarch();
                    ClientAnchor anchor = helper.createClientAnchor();

                    // 이미지 시작 위치
                    anchor.setCol1(5);
                    anchor.setRow1(i + 1); // 1번째 row 부터 데이터 쓰므로
                    // 이미지 끝나는 위치
                    anchor.setCol2(6);
                    anchor.setRow2(i + 2);

                    Picture picture = drawing.createPicture(anchor, pictureIdx);

                    // excel의 cell의 크기를 조정해서 cell을 만든다.
                    Cell cellImg = row.createCell(5);
                    int w = 20 * 256; // 이미지 width 는 1이 1/256 크기 이기 때문에
                    sheet.setColumnWidth(5, w);
                    short h = 120 * 20; // 이미지 높이는 1이 1/20 크기이기 때문에
                    cellImg.getRow().setHeight(h);
                    i++; // list의 다음 vo 정보 저장하기 위해
                }

                    // 실제 Excel 파일로 저장한다.
                    FileOutputStream fileOut = new FileOutputStream("isbn.xls");
                    wb.write(fileOut); // wb안의 내용을 FileoutputStream에 쓴다.
                    fileOut.close();
                    System.out.println("ISBN,ImageURL 저장성공");


            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}


