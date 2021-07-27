import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader io = new BufferedReader((new InputStreamReader(System.in)));
        String[] n;
        n = io.readLine().split(" ");
        int a = Integer.parseInt(n[0]);
        int b = Integer.parseInt(n[1]);

        int gcd_num = gcd(a,b);
        int lcm_num = (a/gcd_num) * (b/gcd_num)*gcd_num;
        System.out.println(gcd_num);
        System.out.println(lcm_num);
    }

    private static int gcd(int a, int b) {
        if(b != 0) return gcd(b , a%b);
        else return a;
    }
}
