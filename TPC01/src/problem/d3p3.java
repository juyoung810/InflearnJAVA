package problem;

import java.util.Scanner;

// 10951
public class d3p3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt())
        {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            System.out.println(x+y);
        }
    }
}
