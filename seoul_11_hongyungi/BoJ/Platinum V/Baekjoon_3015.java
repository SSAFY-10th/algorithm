import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baekjoon_3015 {

    static class Man {
        int duplicate;
        int height;

        Man(int duplicate, int height) {
            this.duplicate = duplicate;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Man> stack = new Stack<>();
        long result = 0;
        for(int i = 0;i < N; i++) {

            int height = Integer.parseInt(br.readLine());
            int duplicate = 1;
            while(!stack.empty() && stack.peek().height <= height) {
                Man man = stack.pop();
                result += man.duplicate;

                if(man.height == height) {
                    duplicate += man.duplicate;
                }
            }

            if(!stack.isEmpty())
                result++;

            stack.push(new Man(duplicate, height));
        }

        System.out.println(result);
    }
}
