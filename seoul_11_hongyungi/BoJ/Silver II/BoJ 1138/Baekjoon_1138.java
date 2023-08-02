import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1138 {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 자료구조 선택
        int[] result = new int[N + 1];

        // 구현
        for (int height = 1; height <= N; height++) {
            int left = arr[height];
            int idx = 0, cnt = 0;
            while (cnt != left) {
                if(result[idx] == 0) cnt++;
                idx++;
            }
            while(result[idx] != 0) {
                idx++;
            }
            result[idx] = height;
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }
}
