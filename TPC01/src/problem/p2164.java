package problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p2164 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i< N+1;i++)
        {
            q.add(i);
        }
        while(q.size() > 1)
        {
            q.remove();
            q.add(q.remove());
        }
        System.out.println(q.remove());

    }
}
