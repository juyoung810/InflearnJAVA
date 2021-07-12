package problem;

import java.util.Scanner;

public class p2475 {
    public static void main(String[] args) {
        Scanner in =  new Scanner(System.in);
        int sum = 0;
        int a = 0;
        for(int i = 0 ; i< 5;i++)
        {
           a =  in.nextInt();
           sum += a*a;
        }
        System.out.println(sum % 10);



    }
}
