package kr.poly;

public class TV implements RemoCon{
    int currentCH = 70;
    @Override
    public void chUp() {
        if(currentCH < RemoCon.MAXCH) {
            currentCH++;
            System.out.println("TV의 채널이 올라간다.: " + currentCH);
        }else
        {
            currentCH = 1;
            System.out.println("TV의 채널이 올라간다.: " + currentCH);
        }
    }

    @Override
    public void chDown() {
        if(currentCH > RemoCon.MINCH) {
            currentCH--;
            System.out.println("TV의 채널이 내려간다.: " + currentCH);
        }
        else
        {
            currentCH = 100;
            System.out.println("TV의 채널이 내려간다.: " + currentCH);
        }

    }

    @Override
    public void internet() {
        System.out.println("인터넷이 된다.");
    }
    // 추가적인 기능,,,
}
