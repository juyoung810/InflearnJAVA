package kr.tpc;

import org.w3c.dom.ls.LSOutput;

public class Inflearn {

    // 생성자 private 이면 클래스 안 모든 요소 static 붙어 있어야한다.
    // 하나라도 static 안 붙어 있으면 public 으로 만들어야
    // static 있 없 공존 가능
    // static 이름 붙은 것 그냥 클래스 이름으로 접근하면 된다.
    private Inflearn(){};
    // (static 없으면) 인스턴스 메서드
    public static void tpc()
    {
        System.out.println("TPC 강의 너무 재밌다");
    }
    // 클래스 메서드
    public static void java()
    {
        System.out.println("Java 강의 너무 재밌다.");
    }
}

