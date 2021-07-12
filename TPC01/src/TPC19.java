import kr.poly.Cat;
import kr.poly.Dog;

public class TPC19 {
    public static void main(String[] args) {
        // Dog, Cat --> Animal

        Dog d = new Dog();
        d.eat();

        Cat c = new Cat();
        c.eat();

        //Dog.java(X), Dog.class(0) -> 소스 코드는 볼 수 없다. 기능만 안다.
        // 소스 파일는 주면 안된다. class file를 어떻게 동작 시킬 수 있을까? --> 리모컨 같은 것 설계해줘야 한다.
        // 소스 파일 제공하지 않고 리모콘 이용해서 file 이용할 수 있도록

        // Animal, <---------------- [Dog.class, Cat.class]
        // Animal 로 Dog 와 Cat 구동 시키는 것이 중요 (리모콘 처럼)

      /*  // 이렇게 사용해야한다.
        Animal ani = new Dog();
        ani.eat();

        ani = new Cat();
        ani.eat();
        */


    }
}
