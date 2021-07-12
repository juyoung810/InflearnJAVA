package problem;

import java.util.Scanner;

public class p3460 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i =0; i< t; i++)
        {
            int n = in.nextInt();
            int count = 0;
            while(n != 0)
            {
                if(n % 2 == 1) System.out.print(count + " ");
                count++;
                n = n /2;
            }
            System.out.println();
        }
    }
}
