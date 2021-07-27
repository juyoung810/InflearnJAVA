package prime;

import java.util.Scanner;

public class boj1978 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int count = 0;
        for(int i = 0; i<N;i++) {
            if (isPrime(in.nextInt())) count += 1;
        }
        System.out.println(count);
    }

    private static boolean isPrime(int p) {
        if(p == 1) return false;
        if(p == 2) return true;
        for(int i = 2; i <= Math.sqrt(p);i++){
            if(p%i == 0) return false;
        }
        return true;
    }
}
