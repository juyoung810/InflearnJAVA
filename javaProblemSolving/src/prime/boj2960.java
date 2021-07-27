package prime;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj2960 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();

        // 2 - N 까지 숫자 리스트 만들기
        Queue<Integer> num_list = new LinkedList<>();
        for(int i = 2 ; i <= N;i++)
        {
            num_list.add(i);
        }
        System.out.println(find_prime(num_list,N,K));
    }

    private static int find_prime(Queue<Integer> num_list,int N, int k) {
        int count = 0;
        int key;
        int p;
        while(true){
            p = num_list.poll();
            key = p;
            count+=1;
            if(count == k) return key;
            int temp = N/p;
            if(temp != 0){
                for(int i = p; i <= temp;i++){
                    if(num_list.remove(p*i)){
                        key = p*i;
                        count += 1;
                        if(count == k)return key;
                    }
                }
            }
        }
    }
}
