package problem;

import java.util.Scanner;

public class p10818 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int[] num = new int[t];
        for(int i = 0; i<t;i++)
        {
            num[i] = in.nextInt();
        }
        int max = num[0];
        int min = num[0];
        for(int i = 0; i < t;i++)
        {
            if(max < num[i]) max = num[i];
            if(min > num[i]) min = num[i];
        }
        System.out.println(min + " " + max);
    }
}
