import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_6884 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= t;test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            int[] sum = new int[len + 1];

            for (int i = 1; i <= len; i++) {
                sum[i] += sum[i - 1] + Integer.parseInt(st.nextToken());
            }

            int curLen;
            int temp;       // 누적합
            boolean changeFlag  = false;

            a:
            for (curLen = 2; curLen <= len; curLen++) {
                for (int start = 1; start + curLen - 1 <= len; start++) {
                    temp = sum[start + curLen - 1] - sum[start - 1];
                    if (isPrime(temp) && temp >= 2) {
                        sb.append("Shortest primed subsequence is length ").append(curLen).append(": ");

                        while (curLen > 0) {
                            sb.append((sum[start] - sum[start - 1]) + " ");
                            start++;
                            curLen--;
                        }
                        sb.append('\n');
                        changeFlag = true;
                        break a;
                    }
                }
            }

            if (!changeFlag) {
                sb.append("This sequence is anti-primed.").append('\n');
            }
        }

        System.out.print(sb);
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if(n % i == 0)
                return false;
        }
        return true;
    }

}