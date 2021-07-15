import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Project02_B {
    public static void main(String[] args) {
        String url = "https://m.pann.nate.com/talk/enter";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.print("[입력력 YYYYMMDD] : ");
            String day = br.readLine();
            // 날짜 입력 어떻게 넘기는지는 html 코드를 분석할 것
            url = url+"/"+day;
            System.out.println("==================================");
            // 날짜를 입력 받아 보낸다.
            Document doc = Jsoup.connect(url).post();
            Element pan_text = doc.select(".depth3_tab_wrap").first();
            // eq : 해당 태그 중 index를 선택한다.
            System.out.println(pan_text.select("li").eq(1).text());

            Element pan_date = doc.select(".date-sort2 strong").first();
            System.out.println(pan_date.text());


            // . :class name, # : id 로 구별하여 태그 들고 오기
            //  >  해당 태그의 자식 태그
            // .first : class 이름은 중복이 가능하므로, 해당 클래스의 이름에 해당하는 것 중 첫번째를 말한다.
            Elements liList = doc.select(".list.list_type2 > li");
            for(Element li : liList)
            {
                if(li != liList.select(".wrap_ad").first()) {
                    System.out.println(li.select(".tit").first().text());
                    System.out.println(li.select(".sub").first().text());
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
