import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		int a, b, weight;
		for(int i = 0 ; i < N - 1;i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new int[] {b, weight});
			graph.get(b).add(new int[] {a, weight});
		}
		
		int[][] usadoMap = new int[N + 1][N + 1];
		for(int i = 0; i < Q; i++) {
			
		}
		
		StringBuilder sb = new StringBuilder();
		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			// 다음 영상과의 유사도(USADO)가 K보다 작은 경우 다음 영상은 추천되지 않는다고 생각
			int result = 0;
			
			boolean[] visit = new boolean[N + 1];
			Queue<Integer> q = new LinkedList<>();
			q.add(v);
			visit[v] = true;
			
			while(!q.isEmpty()) {
				int now = q.poll();
				
				for(int[] edge : graph.get(now)) {
					if(!visit[edge[0]] && edge[1] >= k) {
						q.add(edge[0]);
						visit[edge[0]] = true;
						result++;
					}
				}
			}
			
			sb.append(result).append("\n");
		}
		
		System.out.println(sb);
	}
}