import kr.poly.Animal;
import kr.poly.Cat;
import kr.poly.Dog;

public class TPC25 {
    public static void main(String[] args) {

        // Animal ani = new Animal(); 추상 클래스는 객체를 생성할 수 없다.
        // eat를 재정의 했다는 전제가 되어야 다형성이 (일부) 보장된다.
        // 추상 클래스는 upcasting으로 활용 (부모의 역할)
        Animal ani = new Dog();
        ani.eat(); // 부모가 추상 클래스이므로 다형성이 보장된다
        ani.move();
        ani = new Cat();
        ani.eat(); // 다형성 보장된다.
        ((Cat)ani).night();
        ani.move();
    }
}
