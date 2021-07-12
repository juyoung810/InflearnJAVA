import java.util.Locale;

//import java.lang.* 가 default 로 생략되어 있다.
public class TPC34 {
    public static void main(String[] args) {
        //String
        String str = new String("APPLE");
        String v = str.toLowerCase(Locale.ROOT);
        System.out.println(v);
        System.out.println(str.charAt(3));
        System.out.println(str.length());
        System.out.println(str.indexOf("PL")); //start index return
        System.out.println(str.indexOf("PX")); // 없으면 -1 return
        System.out.println(str.replaceAll("P","X")); // AXXLE

    }
}
