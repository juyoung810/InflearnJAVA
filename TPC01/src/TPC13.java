import kr.tpc.BookDTO;

public class TPC13 {
    public static void main(String[] args) {
        // 책 -> class(BookDTO)-> 객체 -> 인스턴스

        // 4개의 변수에 각각 책에 대한 정보가 들어 있다.
        // 연속적으로 하나의 구조를 이루고 있지 않기 때문에 책이라고는 할 수 없다.
        // 이동할 떄 4개의 공간 이어서 만든 공간 만들어야 편리
        // 다른 데이터 타입이므로 배열에 담기 불가 -> 직접 설계 필요 -> class 로 하나의 바구니를 설계한 것
        String title = "스프링";
        int price  = 25000;
        String company = "제이펍";
        int page = 890;

        BookDTO dto; // dto(Object) 변수
        dto  = new BookDTO(title, price, company,page); //dto(Instance: 인스턴스) ->  실제로 메모리에 생성된 실체를 가리킨다.

        // 하나로 묶어서 이동한다.
        // DTO 의 용도에 맞게 이동하기 편해진다.
        bookPrint(dto);


    }

    public static void bookPrint(BookDTO dto)
    {
        System.out.print(dto.title + "\t");
        System.out.print(dto.price + "\t");
        System.out.print(dto.company + "\t");
        System.out.println(dto.page);
    }
}
