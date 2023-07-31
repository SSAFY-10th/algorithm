import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 50분 소요
 * 데이터가 밀려 나가야하는 자료구조 특성을 이용하여 Queue를 구현한건 당연했는데
 * Queue를 채우고 구현하는 것과 Queue를 비우고 구현하는 것에서 차이점이 나타났다.
 * 전자로 시작했다면 훨씬 시간 단축하였겠지만 후자로 시작하다 문제점을 깨닫고 전자로 돌아가느라 시간이 많이 잡아 먹혔다.
 * 문제 특성을 잘 기억해두고 앞으로 같은 경우의 Queue 구현을 만난다면 참고하도록 하자.
 */

public class Baekjoon_13335 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // n은 다리를 건너는 트럭의 수,
        int w = Integer.parseInt(st.nextToken()); // w는 다리의 길이,
        int L = Integer.parseInt(st.nextToken()); // 그리고 L은 다리의 최대하중

        Queue<Integer> trucks = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks.add(Integer.parseInt(st.nextToken()));
        }

        Queue<Integer> bridge = new LinkedList<>();
        int time = 0, totalWeight = 0;
        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }

        //int round =0;
        while (!bridge.isEmpty()) {
            //System.out.println("#" + round++ + " : " + bridge);
            time++;
            totalWeight -= bridge.poll();
            if(!trucks.isEmpty()) {
                if (totalWeight + trucks.peek() > L) {
                    bridge.add(0);
                } else {
                    bridge.add(trucks.peek());
                    totalWeight += trucks.poll();
                }
            }
        }

        System.out.println(time);
    }
}