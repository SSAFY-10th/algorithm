import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * 40분 걸렸음.
 * 정규표현식 좀 확실하게 알아야할 것 같은데..
 * 로직 구현하고 정규표현식을 사용해야하는 예외 상황까지 20분만에 떠올라서 다 구현했는데
 * 정작 정규표현식 쓰는데 너무 헤매서 결국 서칭하고 20분이 걸렸음.
 * 문자열 푸는데 정규표현식 제대로 모르면 너무 힘들다..
 * 따로 공부좀 하자.
 */
public class Baekjoon_11140 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int result = 3;

            String str = br.readLine();
            String REGEXP_PATTERN_STRING = "[a-z]*l[a-z]l[a-z]*";
            for (int i = 1; i <= 3; i++) {
                for (int j = 0; j < str.length() - i + 1; j++) {
                    String temp = str.substring(j, j + i);
                    /*System.out.println(temp);*/

                    if(temp.equals("o") || (temp.equals("l"))) result = 2;
                    if(temp.equals("lo") || temp.equals("ol") || temp.equals("ll")) result = 1;
                    if(Pattern.matches(REGEXP_PATTERN_STRING, temp)) result = 1;
                    if(temp.equals("lol")) result = 0;
                }
            }

            System.out.println(result);
        }
    }
}