import java.util.*;

public class boj1316 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int count = 0;

        for(int i = 0; i <N;i++)
        {
            String[] arr  = in.next().split("");
            if(checker(arr)) count++;
        }
        System.out.println(count);
    }
    public static Boolean checker(String[] arr)
    {
        Stack s = new Stack();
        for(int i = 0; i<arr.length;i++)
        {
            if(s.empty())
                s.push(arr[i]);
            else
            {
                if(!s.peek().equals(arr[i])) {
                    if (s.search(arr[i]) != -1)
                        return false;
                    else s.push(arr[i]);
                }
            }
        }
        return true;
    }
}
