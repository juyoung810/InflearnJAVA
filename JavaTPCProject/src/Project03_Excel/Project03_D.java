package Project03_Excel;

import io.github.cdimascio.dotenv.Dotenv;
import kr.inflearn.ExcelVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Project03_D {
    public static void main(String[] args) {
        // 키보드로부터 책의 정보 입력 받아서, open API 이용해서 정보 가져온다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("책제목:");
            String title = br.readLine();
            System.out.print("저자:");
            String author = br.readLine();
            System.out.print("출판사:");
            String company = br.readLine();

            ExcelVO vo = new ExcelVO(title,author,company);
            getIsbnImg(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getIsbnImg(ExcelVO vo) {
        //https://developers.naver.com/docs/serviceapi/search/book/book.md#%EC%B1%85
        // naver book openAPI 설명 참고
        Dotenv dotenv = Dotenv.load();
        try{
            String openApi = "https://openapi.naver.com/v1/search/book_adv.xml?d_titl"
                    +URLEncoder.encode(vo.getTitle(),"UTF-8")
                    +"&d_auth="+URLEncoder.encode(vo.getAuthor(),"UTF-8")
                    +"&d_publ="+ URLEncoder.encode(vo.getCompany(),"UTF-8");
            URL url = new URL(openApi);

            // 서버와 연결결
           HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id",dotenv.get("book_id"));
            con.setRequestProperty("X-Naver-Client-Secret",dotenv.get("book_key"));
            // 연결 성공했는지 확인
            int responseCode = con.getResponseCode();

            // 연결 성공했으면 정보 읽어서 와야하니까
            BufferedReader brl;
            if(responseCode == 200){
                brl = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            }else{
                brl = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            // XML 정보 한줄 한줄 읽어서 저장하기 위한 buffer
            StringBuffer response = new StringBuffer();
            while((inputLine = brl.readLine())!= null){
                // response 에 xml 정보 전부 들어온다.
                response.append(inputLine);
            }
            brl.close();
            //System.out.println(response.toString());
            // isbn,img 정보 뽑아오기
            // Jsoup api 이용해 xml data로 바꿔주기
            Document doc = Jsoup.parse(response.toString());
            //System.out.println(doc.toString());
            // 전체 total 정보 찾아서 검색이 잘 됐는지 판별 -> 가장 첫번째 item만 사용
            Element total = doc.select("total").first();
            System.out.println(total.text());
            if(!total.text().equals("0")){
                //isbn 정보 찾는다.
                Element isbn = doc.select("isbn").first();
                String isbnStr = isbn.text();
                System.out.println(isbnStr);
                String isbn_find = isbnStr.split(" ")[1];
                vo.setIsbn(isbn_find);
                //-------------------------
                // img 주소 찾는다.

                //Element img = doc.select("img").first();
                //System.out.println(img.toString()); // -> 이렇게 하면 맞는 태그가 없다.
                String imgDoc = doc.toString();
                String imgTag = imgDoc.substring(imgDoc.indexOf("<img>")+5); // img> 이후 전부
                // 처음 나온 ?를 찾아서 앞에거 하면 img 전체 주소 가져올 수 있다.
                String imgURL = imgTag.substring(0,imgTag.indexOf("?"));
                System.out.println(imgURL);
                // imgURL 에서 파일 이름만 가져오기
                String fileName = imgURL.substring(imgURL.lastIndexOf("/") +1);
                System.out.println(fileName);
                vo.setImageUrl(fileName);

                System.out.println(vo);
            }else{
                System.out.println("검색데이터가 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
