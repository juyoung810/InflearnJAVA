import kr.poly.Animal;
import kr.poly.Cat;
import kr.poly.Dog;

public class TPC23 {
    public static void main(String[] args) {
        // 1. 다형성 인수
        Dog d = new Dog();
        display(d);
        Cat c = new Cat();
        display(c);
    }
    // 자동 upcasting
    private static void display(Animal r) // 다형성 인수
    {
        r.eat();
        //r.night();
        if(r instanceof Cat ) // r이 Cat type으로 만들어진 경우
            ((Cat)r).night(); //downcasting (upcasting 했으므로 가능)
    }

}
