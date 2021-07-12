import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken; // reflection 역추적해서 뭔가를 만들어내는 기법
import kr.inflearn.BookDTO;

import java.util.ArrayList;
import java.util.List;

public class Project01_A {
    public static void main(String[] args) {
        // GSON 사용할 때는 객체가 반드시 만들어져있어야 JSON 만들 수 있다.(-)
        // Object(BookDTO) ->JSON(String)
        BookDTO dto = new BookDTO("자바",21000,"에이콘",670);
        Gson g = new Gson();
        String json = g.toJson(dto);
        System.out.println(json); //{"title":"자바","price":21000,"company":"에이콘","page":670}

        //JSON(String) -> Object(BookDTO)
       BookDTO dto1 =  g.fromJson(json,BookDTO.class);
       System.out.println(dto1); // BookDTO{title='자바', price=21000, company='에이콘', page=670}

        //Object(List<BookDTO>) ->  JSON(String) : [{    }, {     }.....]
        List<BookDTO> lst = new ArrayList<>();
        lst.add(new BookDTO("자바1",21000,"에이콘1",570));
        lst.add(new BookDTO("자바2",31000,"에이콘2",670));
        lst.add(new BookDTO("자바3",11000,"에이콘3",370));


        String lstJson = g.toJson(lst);
        System.out.println(lstJson);

        // JSON(String) -> Object(List<BookDTO>)
        // g.fromJson(lstJson,List<BookDTO>.class); 내부에 각각의 type을 알아야하기 때문에 불가
        List<BookDTO> lst1 = g.fromJson(lstJson, new TypeToken<List<BookDTO>>(){}.getType());
        for(BookDTO vo : lst)
        {
            System.out.println(vo);
        }
    }
}
