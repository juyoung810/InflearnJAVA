public class TPC39 {
    public static void main(String[] args) {

        int a = 1;
        // Java.lang.* 은 기본으로 import 되어있다.
        Integer b = new Integer(1); // 편리성을 따지기 때문에 자동 Boxing 방법으로 사용하는 것 권장하는 것
        //Integer b = 1; // int -> Integer 로 자동 boxing 해준다. (compiler가)
        System.out.println(b.intValue()); //1
        System.out.println(b.toString()); // "1"

        Object[] obj = new Object[3];
        // 1,2,3
       /* obj[0] = new Integer(1); // Object의 자식이 되어야 upcasting 해서 넣을 수 있다.
        obj[1] = new Integer(2);
        obj[2] = new Integer(3);*/

        obj[0] =1; // boxing 기법 -> wrapper class
        obj[1] =2;
        obj[2] =3;

        System.out.println(obj[0].toString()); // 재정의된 자식의 클래스 Integer의 toString메서드가 실행된다.
        System.out.println(obj[1].toString());
        System.out.println(obj[2].toString());

        // "100" + "100" = 200
        // 정수 100으로 변환 후 계산해야한다.
        String x = "100";
        String y = "100";
        System.out.println(x+y); //"100100"
        int v1 = Integer.parseInt(x);
        int v2 = Integer.parseInt(y);
        System.out.println(v1+v2); // 200;

        //100 + 100 = "100100"

        String s1 = String.valueOf(v1);
        String s2 = String.valueOf(v2);
        System.out.println(s1+s2);

    }
}
