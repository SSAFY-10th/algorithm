# [백준 15591 MooTube(Silver)](https://www.acmicpc.net/problem/15591)

solved.ac Gold V

## 카테고리

그래프 이론, 그래프 탐색, 너비 우선 탐색(bfs)

## 전체 코드

```java
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

		StringBuilder sb = new StringBuilder();
		boolean[] visit;
		int k, v, result;

		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());

			// 다음 영상과의 유사도(USADO)가 K보다 작은 경우 다음 영상은 추천되지 않는다고 생각
			result = 0;
			visit = new boolean[N + 1];

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
```

## 시간복잡도

농부는 Q번 질문하고 한번의 질문에서 BFS를 수행한다. BFS의 시간복잡도는 익히 알려진 대로 O(N + E)이다. 따라서 위 코드의 시간복잡도는 O(Q X N)이다.

## 풀이

**⏱️소요시간 60분**<br>

그래프 문제를 너무 오랜만에 풀어서 그런가 정답과 방향이 먼 곳으로 밖에 생각이 안갔다. 답은 생각보다 간단한 곳에 있었다. 처음에는 `임의의 두 쌍 사이의 동영상의 USADO를 그 경로의 모든 연결들의 USADO 중 최솟값으로 하기로 했다.`라는 문장 때문에 동영상들의 유사도를 모두 그래프 탐색하여 map에 저장하는 방법을 고민했지만 그럴 필요 없었다.

Queue로 넣는 탐색 조건에 유사도가 K보다 크거나 같은 노드들만 넣는다면 USADO에 대해서 특별하게 계산하지 않아도 된다. USADO가 K보다 작은 노드들은 더이상 탐색을 해봐야 USADO가 K보다 작아진다. 따라서 문제는 그냥 BFS 문제이다.

```java
while(Q-- > 0) {
    st = new StringTokenizer(br.readLine());
    k = Integer.parseInt(st.nextToken());
    v = Integer.parseInt(st.nextToken());

    // 다음 영상과의 유사도(USADO)가 K보다 작은 경우 다음 영상은 추천되지 않는다고 생각
    result = 0;
    visit = new boolean[N + 1];

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
```

출력이 최대 5000번 일어난다. 이런 경우 꼭 StringBuilder를 사용해주자.

## 결과

결과 : [맞았습니다!!](https://www.acmicpc.net/source/64448510)<br>
메모리 : 311668 KB<br>
시간 : 1844 ms<br>
언어 : JAVA<br>
코드길이 : 1746 B<br>
