package kr.poly;


public abstract class  Animal{ // 추상클래스(불완전한객체) ---> Animal ani = new Animal() 불가 -> 스스로가 불완전하기 때문에 객체 생성 불가
    // Dog, Cat --> eat()
    // eat 자녀가 재정의 안해도 문제가되지 않고 ?가 출력된다 -> 필요없다.
    // 반드시 재정의 할 수 있도록 해야한다. -> 추상 클래스 , 추상 메서드로 강제성 가질 수 있도록 한다.
    public abstract void eat(); // 추상 메서드=> 불완전한 메서드, 장애 메서드 -> 구현부가 없다.

    public void move() // 바디가 있는 구현 메서드 또한 추상 클래스를 구성할 수 있다.
    {
        // 추상 클래스는 구현된 메서드를 사용할 수 있기 때문에 서로 기능이 비슷한 클래스를 묶을 때 사용한다.
        System.out.println("무리를 지어서 이동한다.");
    }
}
