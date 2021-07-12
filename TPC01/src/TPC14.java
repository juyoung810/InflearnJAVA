import kr.tpc.MemberVO;

public class TPC14 {
    public static void main(String[] args) {
        MemberVO m = new MemberVO();
        // method  통해 값이 들어가면 제한 조건을 걸어서 값을 set 할 수 있다.
        m.setName("홍길동");
        m.setAge(50);
        m.setTel("010-1111-1111");
        m.setAddr("서울");

        System.out.print(m.getName() +"\t");
        System.out.print(m.getAge() +"\t");
        System.out.print(m.getTel() +"\t");
        System.out.println(m.getAddr());
     /*
        // 접근 가능하게 하면 정보 보호가 안된다.
        m.name = "홍길동";
        m.age = 50;
        m.tel = "010-1111-1111";
        m.addr = "서울" ;

        System.out.print(m.name +"\t");
        System.out.print(m.age +"\t");
        System.out.print(m.tel +"\t");
        System.out.println(m.addr);
        */

    }
}
