import kr.tpc.DBConnect;
import kr.tpc.JavaMysql;
import kr.tpc.JavaOracle;

public class TPC32 {
    public static void main(String[] args) {

        // Oracle, MySQL -> Driver class  필요
        DBConnect conn = new JavaOracle();
        conn.getConnection("url","bit","12345");

        conn = new JavaMysql();
        conn.getConnection("url","root","ABCDE");
    }
}
