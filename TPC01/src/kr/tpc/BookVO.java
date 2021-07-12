package kr.tpc;
// 현실 세계 책(object) -> 설계
public class BookVO {
    public String title;
    public int price;
    public String company;
    public int page;

    // 디폴트 생성자메서드 (생략)
    // 생성자가 하나 만들어져 있으면 디폴트 생성자 자동으로 만들어지지 않는다.
    public BookVO() {
        // 초기화 작업 (  생성자도 메서드이기 때문에 초기화하는 동작을 할 수 있다)
        // this. 해야 자기 자신의 멤버 변수에 접근할 수 있다.
        // this는 자기 자신 가리키는 객체이기 때문에 . 찍어서 참조한다.
        this.title = "자바";
        this.price = 14000;
        this.company = "이지스";
        this.page = 780;

    }

    // 생성자 메서드의 중복정의(overloading)
    // overloading 메서드 이름이 같고 매개변수 갯수, type 다르다
    public BookVO(String title, int price, String company, int page) {
        this.title = title;
        this.price = price;
        this.company = company;
        this.page = page;
    }
}
