import kr.poly.Animal;
import kr.poly.Cat;
import kr.poly.Dog;

public class TCP21 {
    public static void main(String[] args) {
        // 간접 사용 방식을 더 많이 사용한다.

        //직접 이용
        Dog d = new Dog();
        d.eat();

        Cat c = new Cat();
        c.eat();
        c.night();

        //Dog.class , Cat.class 파일만 가지고 있다.

        // 상속 관계가 전제 되어야 한다. (upcasting)
        Animal ani = new Dog();
        // 재정의가 되어있으면 동적 바인딩이 일어나서 자식 함수 호출 가능
        ani.eat();
        // eat는 같은 method 이름 가지는데 다른 동작 한다.
        // 상위 클래스의 하나의 메세지에 자식 마다 서로 다른 반응 하는 것을 다형성이라고 부른다.


        ani = new Cat();
        ani.eat();
        // 재정의 되어 있지 않기 때문에 호출 불가
        //ani.night();
        // ani를 Cat type으로 down casting해서 사용 가능

        //Cat cc = (Cat)ani;
        // cc.night();
        ((Cat)ani).night();



    }

}
