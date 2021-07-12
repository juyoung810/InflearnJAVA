import kr.poly.Animal;
import kr.poly.Cat;
import kr.poly.Dog;

public class TPC20 {
    public static void main(String[] args) {
        //Animal d = new Dog(); // 상속 관계 이기 때문에 부모 = 자식 가능 -> 부모가 자식을 가리킨다. : 자동 형변환
        // object casting ( upcasting 큰 것에 작은 것 들어간다)

        // Anilmal 부모 클래스를 사용하지 않음. -> 상속의 이점을 활용하지 않은 것
        Dog d = new Dog();
        d.eat(); // ? 나온다

        Cat c  = new Cat();
        c.eat();
        c.night();


        //Dog.class, Cat.class 밖에 없는 경우
        // Dog, Cat 구동 방식 알지 못하기 때문에 부모로 사용 가능

        // 부모 타입으로 자식 클래스를 구동할 수 있다는 강력한 상속 관계 프로그램 짤 수 있다.
        // 재정의 하냐 안하냐에 따라 자식 클래스 구동 시킬수 있냐 아니냐의 중요한 문제가 된다.
        Animal ani = new Dog(); // ? -> 개처럼 먹다.
        ani.eat();

        ani = new Cat();
        ani.eat();
        ((Cat)ani).night(); //  --> downCasting (강제 형변환)
    }
}
