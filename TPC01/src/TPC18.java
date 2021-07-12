import com.google.gson.Gson;
import kr.tpc.BookVO;
import kr.tpc.MyUtil;

import java.util.Locale;
//import java.lang.*; //디폴트 패키지 (생략)
public class TPC18 {
    public static void main(String[] args) {
        //1. java 에서 제공해주는 class 들... (API)
        // 문자열(String)
        String str = new String("APPLE");
        System.out.println(str.toLowerCase(Locale.ROOT)); //apple

        // String 과 System은 java.lang 패키지 안에 들어 있는데,
        // 해당 패키지 import 로 java.lang.* 포함 시켜 준다.
        // 해당 패키지가 디폴트 패키지로 생략되어 있다.

        // 2. 직접 만들어서 사용하는 class들... DTO/VO, DAO, Utility ...API
        MyUtil my = new MyUtil();
        int sum = my.hap();
        System.out.println(sum);

        // 3. 다른 회사에서 만들어 놓은 class 들 ...API
        // GSON -> json
        Gson g = new Gson();
        BookVO vo = new BookVO("자바",13000, "영진",800);
        String json = g.toJson(vo);
        //{"title":"자바","price":13000,"company":"영진","page":800} : 문자열 형태로 바꿔줌 json 구조로
        System.out.println(json);

    }
}
