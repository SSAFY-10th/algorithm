import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Q11478 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String s = br.readLine();

        Set<String> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                set.add(s.substring(i, j));
            }
        }

        System.out.println(set.size());
    }
}