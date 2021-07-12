package problem;

import java.util.Scanner;

public class p10773 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int l = 0;
        int k = in.nextInt();
        int[] s = new int[k];
        for(int i = 0; i < k;i++)
        {
            int n = in.nextInt();
            if(n == 0) l--;
            else
            {
                s[l] = n;
                l++;
            }
        }
        int sum = 0;
        for(int i = 0; i < l;i++)
        {
            sum += s[i];
        }
        System.out.println(sum);
    }
}
