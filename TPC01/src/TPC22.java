import kr.poly.Animal;
import kr.poly.Cat;
import kr.poly.Dog;

public class TPC22 {
    public static void main(String[] args) {

        // Cat ani = new Cat();
        // Object ani = new Cat();
        Animal ani = new Cat();// upcasting
        ani.eat(); //컴파일 시점 -> Animal ,실행 시점 -> 재정의 해놨으므로 Cat 것

       // ani.night() // Animal은 night 가지고 있지 않으므로 컴파일 시점에서 오류 발생
        //Cat c = (Cat) ani; // downCasting
        // c.night();
        // (Cat)ani. night() -> 오류 : . 연산자가 casting 연산자 보다 우선순위가 높기 때문
        ((Cat)ani).night();

        ani = new Dog();
        ani.eat();
        // 다형성
        // 상위 클래스가 하위클래스에게 동일한 메세지로 서로 다르게 동작시키는 객체지향 원리

        // 다형성 어디에 활용되는지 알면 유용
        Object o = new Dog();
        // o.eat() 안된다. Object는 eat 메서드 가지고 있지 않기 때문.
        ((Dog) o).eat();


    }
}
