package kr.poly;

public class Dog extends Animal { // 추상 클래스를 상속 받으면 해당 추상 클래스의 추상 메서드를 반드시 재정의 해야한다.
    // 이름 , 나이, 종 : 상태 정보
    // 재정의 override
    @Override
    public void eat() // 반드시 재정의 -> 다형성 보장된다.
    {
        System.out.println("개처럼 먹다."); // 포괄적, 추상적
    }
    public Dog()
    {
        // 부모의 생성자 먼저 호출하는 것이 호출되어 있다.
        super(); // new Animal();
    }
}
