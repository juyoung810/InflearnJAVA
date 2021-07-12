package kr.inflearn;

//접근 제한자 생략하면 default 접근 제한자가 된다. -> 클래스 외부에서 접근 불가
// public 해야지 외부 패키지 에서 접근가능
public class MyClass {
    public int sum(int a, int b)
    {
        int hap = 0;
        for(int i = a; i<= b;i++)
        {
            hap+= i;
        }
        return hap;
    }
}
