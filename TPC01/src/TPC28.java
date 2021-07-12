import kr.poly.A;

public class TPC28 {
    public static void main(String[] args) {

        A a = new A();
        a.display();
       // System.out.println(a.toString());
        System.out.println(a); // toString 생략 가능
       //  Object --> toString :kr.poly.A@1e643faf 재정의 안했을 경우 Object의 메서드가 실행되므로 번지가 출력된다.

        Object o = new A(); //upCasting
        ((A)o).display();
        System.out.println(o.toString());
        // Object 이용해서 다형성 이론 사용할 수 있다.

    }
}
