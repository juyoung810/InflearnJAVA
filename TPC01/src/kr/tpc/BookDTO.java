package kr.tpc;

public class BookDTO {
    // 이 4개의 저장공간을 연결해서 만든 기억 공간의 이름이 BookDTO 이다.
    // 서로 다른 type 의 기억 공간을 연결했으므로 배열로는 만들 수는 없다 -> class 로 설계
    // 메모리에 class 이용해서 객체를 생성해야 정보가 올라간다.
    public String title;
    public int price;
    public String company;
    public int page;

    // 디폴트 생성자 메서드는 생략되어 있다.
    public BookDTO()
    {
        // 객체를 생성하는 작업을 한다,(기계어 코드)
        // 메모리에 해당 공간을 만드는 작업
        // 결과 자기 자신을 가리키는 변수와 객체 만들어진다.
    }

    public BookDTO(String title, int price, String company, int page) {
        this.title = title;
        this.price = price;
        this.company = company;
        this.page = page;
    }
}
