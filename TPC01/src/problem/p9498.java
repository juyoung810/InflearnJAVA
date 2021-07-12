package problem;

import java.util.Scanner;

public class p9498 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        if(num >= 90) System.out.println("A");
        else if(num >= 80) System.out.println("B");
        else if(num >= 70) System.out.println("C");
        else if(num >= 60) System.out.println("D");
        else System.out.println("F");
    }
}
