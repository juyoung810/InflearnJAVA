public class TPC09 {
    public static void main(String[] args) {

        int a = 56;
        int b = 40;
        //a+b = ?
        TPC09 tpc = new TPC09(); //Heap Area에 new 통해서 객체 생성된다.
        int v = tpc.sum(a,b); // main에서 Heap Area 에 생성된 객체의 method를 호출
        // 실제 add 매서드는 method Area 에 저장되어 있고, Heap Area 는 method Area 에 저장된 add 메서드의
        // 주소를 가리킨다.
        System.out.println(v);
    }

    public int sum(int a, int b)
    {
        int v = a+b;
        return v;
    }
}
