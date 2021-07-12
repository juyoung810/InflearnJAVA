package kr.poly;

// 부모가 자식을 통제할 수 있다.
public interface RemoCon { // 100 % 장애 클래스 -> 객체 생성 불가 -> 반드시 하위 클래스가 implements 필요
    /// 추상 메서드
    public void chUp(); // abstract 키워드가 생략되어 있다.
    public void chDown();
    public void internet();

    // final static(상수)도 사용 가능 Remocon.MAXCH, Remocon.MINCH로 상수에 접근 가능
    int MAXCH = 100; //public static final 이 생략되어있다. -> 상수 (값 수정 변경)
    int MINCH = 1;
}
