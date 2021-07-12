import kr.inflearn.IntArray;

public class TPC36 {
    public static void main(String[] args) {
        // 점수 3개를 배열에 저장하고 출력하시오.
        int[] a = new int[3];

        a[0] = 10;
        a[1] = 20;
        a[2] = 30;

        int v = a[1];
        int len = a.length;
        for(int i = 0; i<a.length;i++)
            System.out.println(a[i]);

        // ------------------------------------------
        // 배열처럼 동작하는 api 만들어서 사용 -> 객체지향적
        // 배열을 다 몰라도 동작 방식만 알면 기능사용가능 -> api
        // 클래스를 가져다가 쓰는 객체지향 방법
        // ObjectArray 를 만들면 모든 type 의 array 만들 수 있다. --> ArrayList 가 이미 만들어져있다.
        IntArray arr = new IntArray(5);
        arr.add(10);
        arr.add(20);
        arr.add(30);

        for(int i = 0; i< arr.size();i++)
        {
            System.out.println(arr.get(i));
        }
    }
}
