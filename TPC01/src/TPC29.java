import kr.tpc.A;
import kr.tpc.B;

public class TPC29 {
    public static void main(String[] args) {

        A a = new A();
        display(a);
        B b = new B();
        display(b);
    }

    private static void display(Object o) { // 다형성 인수 받을 때 upcasting
        if(o instanceof A)
            ((A)o).go(); // 메서드 사용할 때 downCasting 해서 이용
        else ((B)o).go();
    }
}
