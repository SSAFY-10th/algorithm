import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Boj_10986 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] arr = new long[n + 1];
        st = new StringTokenizer(br.readLine());

        int[] mods = new int[m];

        for (int i = 1; i <= n; i++) {
            arr[i] = (arr[i - 1] %m + Long.parseLong(st.nextToken())% m)% m;
            int num = (int)(arr[i] % m);
            mods[num]++;
        }
        long result = 0;
        for (int mod : mods)
            result += nCTwo(mod);
        result += mods[0];
        System.out.println(result);
    }

    private static long nCTwo(long n) {
        return (n * (n-1))/2;
    }



}