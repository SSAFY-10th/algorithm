import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Boj1167 {

    static int V;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static boolean[] visit;
    static int max = Integer.MIN_VALUE;
    static int endPoint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());
        for(int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        visit = new boolean[V + 1];

        int u, v, cost;
        for(int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            while(true) {
                v = Integer.parseInt(st.nextToken());
                if(v == -1) break;

                cost = Integer.parseInt(st.nextToken());

                graph.get(u).add(new int[] {v, cost});
            }
        }

        dfs(1, 0);

        max = Integer.MIN_VALUE;
        visit = new boolean[V + 1];
        dfs(endPoint, 0);

        System.out.println(max);
    }

    static void dfs(int now, int dist) {
        if(visit[now]) return;

        visit[now] = true;

        for(int i = 0; i < graph.get(now).size(); i++) {
            if(!visit[graph.get(now).get(i)[0]]) {
                dfs(graph.get(now).get(i)[0], dist + graph.get(now).get(i)[1]);
            }
        }

        if(max < dist) {
            max = dist;
            endPoint = now;
        }
    }
}