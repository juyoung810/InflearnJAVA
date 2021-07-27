package prime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class boj1929 {
    public static void main(String[] args) throws IOException {
        // M 이상 N 이하의 소수를 모두 출력
        // 범위 -> 에라토스테네스의 체 사용
        // 2~16 까지 뿌린다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = (br.readLine().split(" "));
        int M = Integer.parseInt(input[0]);
        int N = Integer.parseInt(input[1]);

        // 2 ~ 16 까지의 정보 확인 위해 16+1 크기의 배열 만든다.
        Boolean[] primecheck = new Boolean[N+1];
        // 초기화
        for(int i = 0; i < N+1;i++){
            // 0과 1은 소수가 아니므로 자동 false
            if(i == 0 || i == 1) primecheck[i] = false;
            else primecheck[i] = true;
        }

        // 소수가 아닌 수 제거
        for(int i = 2;i < N+1;i++){
            if(primecheck[i] == true){
                for(int j = 2; j <= (N+1)/i;j++){
                    primecheck[i*j] = false;
                }
            }
        }

        // true인 것 count
        int count = 0;
        for(int i = M; i < N+1;i++){
            // 0과 1은 소수가 아니므로 자동 false
            if(primecheck[i]) System.out.println(i);

        }

    }
}
