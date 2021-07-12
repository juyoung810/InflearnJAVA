package problem;

import java.util.Scanner;

public class p2501 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int count = 0;
        for(int i  = 1; i <= n; i++)
        {
            if(n % i== 0)
            {
                count++;
                if(count == m)
                {
                    System.out.println(i);
                    break;
                }
            }
        }
        if(count < m) System.out.println(0);
    }
}
