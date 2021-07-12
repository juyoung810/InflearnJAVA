import kr.inflearn.ObjectArray;
import kr.tpc.A;
import kr.tpc.B;

import java.util.ArrayList;

public class TPC37 {
    public static void main(String[] args) {
        //ObjectArray arr = new ObjectArray(5);
        ArrayList arr = new ArrayList(5); // Object 배열이면서 , 배열의 길이에 제약이 없다.
        arr.add(new A());
        arr.add(new B());
        arr.add(new A());
        arr.add(new A());
        arr.add(new B());
        arr.add(new B());
        arr.add(new B());
        arr.add(new B());
        arr.add(new B());
        // 서로 다른 type 배열에 넣지 못하도록 generic type 추후에 사용함
        // 5개 이상 넣는 것 불가하다 -> java 에서 제공하는 ArrayList 사용하면 가능

        for(int i = 0; i <arr.size();i++)
        {
            Object o = arr.get(i);
            if(o instanceof A) ((A)o).go();
            else ((B)o).go();
        }
    }
}
