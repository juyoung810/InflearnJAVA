import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Project02_A {
    public static void main(String[] args) {
        // Jsoup API
        String url = "https://entertain.naver.com/home";
        Document doc = null;
        try{
            doc = Jsoup.connect(url).get();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        // 뉴스의 전체 목록 가져올 수 있는 element
        Elements element=doc.select("div.home_hit_grid").eq(0);
        String title = element.select("h3").text().substring(0,8); // 0~8 8개의 글자 타이틀로 받는다.
        title += "-" + element.select("ul.heading_tab_lst").select("li").eq(0).text();
        System.out.println("==============================================");
        System.out.println(title);
        System.out.println("==============================================");
        for(Element ee: element.select("ul.hit_news_lst").select("li")){
                System.out.println(ee.text());
        }

        System.out.println("==============================================");

    }
}
