package problem;

import java.util.Scanner;

public class p2460 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = 0;
        int maxTotal = 0;
        for(int i =0; i<10;i++)
        {
            int down = in.nextInt();
            int up = in.nextInt();
            total = total - down + up;
            if(maxTotal < total) maxTotal = total;
        }
        System.out.println(maxTotal);
    }
}
