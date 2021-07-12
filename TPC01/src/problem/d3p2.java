package problem;

import java.util.Scanner;

//10950
public class d3p2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        for(int i  = 0; i<num;i++)
        {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            System.out.println(x+y);
        }
    }
}
