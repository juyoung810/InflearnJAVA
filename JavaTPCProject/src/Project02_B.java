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
            url = url+"/"+day;
            System.out.println("==================================");
            Document doc = Jsoup.connect(url).post();
            Element pan_text = doc.select(".depth3_tab_wrap").first();
            System.out.println(pan_text.select("li").eq(1).text());

            Element pan_date = doc.select(".date-sort2 strong").first();
            System.out.println(pan_date.text());

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
