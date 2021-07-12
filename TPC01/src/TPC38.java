import kr.tpc.BookDTO;
import kr.tpc.MovieVO;

import java.util.ArrayList;
import java.util.List;

public class TPC38 {
    public static void main(String[] args) {

        // ArrayList의 부모는 List로 받을 수 있다.
        // <BookDTO> type 만 쓰겠다고 선언 -> 내부적으로 Object[] -> BookDTO[]로 바뀐다.
        List<BookDTO> list = new ArrayList<BookDTO>(); //Object[], 배열의 길이에 제한이 없다.
        list.add(new BookDTO("자바",12000,"이지스",600));
        list.add(new BookDTO("C언어",17000,"에이콘",670));
        list.add(new BookDTO("Python",15000,"제이펍",690));
        // 복합 타입 넣을 수 있지만 한가지 타입만 넣는게 좋다. -> generic type

        for(int i =0 ;i <list.size(); i++)
        {
            //Object o = list.get(i);
            BookDTO vo = list.get(i); // 제네릭 타입을 선언했기 때문에 downCasting 할 필요 없다.
            System.out.println(vo.title+"\t"+vo.price+"\t"+vo.company+"\t"+vo.page);
        }
    }
}
