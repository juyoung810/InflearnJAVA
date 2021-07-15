import java.io.*;
import java.util.Scanner;

public class boj10989 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[10001];
        for(int i = 0; i < N;i++)
        {
            nums[Integer.parseInt(br.readLine())] += 1;
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < 10001; i++){
            for(int j = 0; j < nums[i];j++) {
                bw.write(Integer.toString(i) + '\n');
            }
        }
        br.close();
        bw.close();
    }
}
