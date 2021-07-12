package problem;

import java.util.Scanner;

public class p4153 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int[] num = new int[3];
            for (int i = 0; i < 3; i++) {
                num[i] = in.nextInt();
            }
            if(num[0] == 0 &&  num[1] == 0 && num[2] == 0) break;
            for (int i = 0; i < 3; i++) {
                for (int j = i+1; j < 3; j++) {
                    if (num[i] > num[j]) {
                        int temp = num[i];
                        num[i] = num[j];
                        num[j] = temp;
                    }
                }
            }
            if (num[0] * num[0] + num[1] * num[1] == num[2] * num[2]) System.out.println("right");
            else System.out.println("wrong");
        }
    }

}
