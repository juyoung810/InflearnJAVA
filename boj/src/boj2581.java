import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class boj2581 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();

        Queue q = new LinkedList();
        for(int i = N ; i <= M;i++)
        {
            if(findNum(i) == -1) q.add(i);
        }

        if(q.size() == 0) System.out.println(-1);
        else{
            int first = (int)q.poll();
            int sum = first;
            while(!q.isEmpty())
                sum += (int)q.poll();
            System.out.println(sum);
            System.out.println(first);
        }
    }

    private static int findNum(int n) {
        if(n == 1) return n;
        else if(n == 2) return -1;
        else{
            for(int i = 2; i <= n/2;i++ )
            {
                if(n % i == 0) return i;
            }
            return -1;
        }
    }
}
