public class TPC35 {
    public static void main(String[] args) {

        String str1 = new String("APPLE");
        String str2 = new String("APPLE");

        if(str1 == str2) System.out.println("YES");
        else System.out.println("NO");
        // heap 영역에 서로 다른 메모리를 생서하여 번지를 가리키므로 번지가 달라 NO -> 가리키는 문자열만 비교하는 방법은?
        if(str1.equals(str2))System.out.println("YES"); // .equals 사용하면 가리키는 문자열을 비교할 수 있다. ->YES
        else System.out.println("NO");

        //literal --------------------------------------------------------------------------------
        String str3 = "APPLE";
        String str4 = "APPLE";
        if(str3 == str4) System.out.println("YES"); // literal pool 메모리 영역에 문자열 상수가 저장된다.재활용 가능한 메모리 이므로 같은 번지를 가리킨다. ->YES
        else System.out.println("NO");
        if(str3.equals(str4))System.out.println("YES"); // .equals 사용하면 가리키는 문자열을 비교할 수 있다. ->YES
        else System.out.println("NO");
        // equals 함수 사용하는 것이 좋다.
    }
}
