package kr.tpc;

public class Overload {
    // 동작으로만(method) 이루어진 객체
    // 사용자는 method  이름 하나만 알고 있으면 된다.
    public void hap(int a, int b)
    {
        System.out.println(a+b);
    }
    public void hap(float a, int b)
    {
        System.out.println(a+b);
    }
    public void hap(float a, float b)
    {
        System.out.println(a+b);
    }
}
