package kr.tpc;

public class JavaMysql implements DBConnect{
    @Override
    public void getConnection(String url, String user, String password) {
        System.out.println("MySQL DB가 접속이 됩니다.");
    }
}
