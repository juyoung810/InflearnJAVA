public class TPC08 {
    // main 에 static 키워드 붙어 있어야 반드시 메모리에 로딩된다.
    // static 메소드 안에서는 static 메소드만 호출 할 수 있다.
    // static 키워드는 프로그램 시작되기 전에 메모리의 고정된 위치에 자동으로 로딩하기 위한 키워드이다.
    public static void main(String[] args) {

        int a = 30;
        int b = 50;
        int v =  add(a,b); // static method call (살짝 기울어짐)
        System.out.println(v);
    }
    // 자동으로 메모리의 고정된 위치에 올라간다.
    public static int add(int a, int b)
    {
        int sum = a+b;
        return sum;
    }
}
