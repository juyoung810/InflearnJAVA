public class TPC06 {
    public static void main(String[] args) {
        // .5 메서드 -> 동작(method), 기능(function)
        // static 함수에서 다른 함수 호출할려면 , 같은 static 이여야 한다.
        int a = 67;
        int b = 98;
        // a+b?
        // 함수가 호출될 때 매개 변수 전달 ( 값 전달 했으므로 call by value)
        int result = sum(a,b);
        System.out.println(result);

       int[] arr =  makeArr();
       int hap = 0;
       for(int i = 0 ; i<arr.length;i++)
       {
           hap+= arr[i];
       }
        System.out.println(hap);
    }
    // 정수 2개를 매개 변수로 받아서 총합을 구하여 리턴하는 메서드를 정의하시오
    // void 는 return 하지 않겠다는 말
    // static: sum 이라는 함수 main 함수에서 호출해야하기 때문에-> sum 함수도 메모리에 로딩 되어야 한다.
    public static int sum(int a, int b)
    {
       int v =  a+b;
       return v;
    }

    // 10,20,30 3개의 정수형 데이터를 return 하고 싶다.
    public static int[] makeArr()
    {
        int x = 10;
        int y = 20;
        int z = 30;

        // 여러개 정수 하나로 모아서 return 할 수 있다.
        int[] arr = new int[3];
        arr[0] = x;
        arr[1] = y;
        arr[2] = z;

        return  arr;
    }
}
