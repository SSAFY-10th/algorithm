# [[Platinum IV] 세금 - 13907](https://www.acmicpc.net/problem/13907) 

### 분류

데이크스트라, 다이나믹 프로그래밍, 그래프 이론

## 시간복잡도

`O((V + E) log V) + O(n + m + k) + O(m) + O(k * (n log n + n))`

## 문제 설명

주언이는 경제학을 배워 행상인이 되었다. 두 도시를 오가며 장사를 하는데, 통행료의 합이 가장 적은 경로로 이동하려 한다. 도시들은 양방향 도로로 연결되어있으며, 도로마다 통행료가 존재한다.

그런데 정부는 세금 인상안을 발표하였다. 세금을 한 번에 올리면 문제가 발생할 수 있으므로 여러 단계에 걸쳐서 올린다고 한다. 세금이 `A`만큼 오르면 모든 도로의 통행료가 각각 `A`만큼 오르게 된다. 세금이 오르게 되면 주언이가 내야 하는 통행료가 변할 수 있다.

주언이를 도와 초기의 최소 통행료와 세금이 오를 때마다의 최소 통행료를 구하시오.

## 입력 

첫 번째 줄에 세 정수 `N (2 ≤ N ≤ 1,000)`, `M (1 ≤ M ≤ 30,000)`, `K (0 ≤ K ≤ 30,000)`가 주어진다. 각각 도시의 수, 도로의 수, 세금 인상 횟수를 의미한다.

두 번째 줄에는 두 정수 `S와 D (1 ≤ S, D ≤ N, S ≠ D)`가 주어진다. 각각 `출발 도시`와 `도착 도시` 번호를 의미한다. 도시 번호는 `1`부터 시작한다.

다음 `M개의 줄`에는 각각 도로 정보를 나타내는 세 정수 `a, b (1 ≤ a < b ≤ N)`, `w (1 ≤ w ≤ 1,000)`가 주어진다. 도시 `a`와 도시 `b`가 통행료 `w`인 도로로 연결되어 있다는 것을 의미한다.

다음 총 `K개의 줄`에는 각각 정수 `p (1 ≤ p ≤ 10)`가 주어진다. 각각 `첫 번째, 두 번째, …, K 번째`에 인상되는 세금을 의미한다.

`S`에서 `D`로 이동할 수 없는 경우는 주어지지 않는다.

## 출력 

`첫 번째 줄`에 `세금이 오르기 전`의 `최소 통행료`를 출력한다.

`두 번째 줄`부터 `K개의 줄`에 각각 `첫 번째, 두 번째, …, K 번째` 세금이 올랐을 때의 `최소 통행료`를 출력한다.

## 해설

간선 구조체
- to : 도착지
- cost : 비용

```cpp
struct Edge {
	int to;
	int cost;
	Edge(int to, int cost) : to(to), cost(cost) {}
};
```

다익스트라 알고리즘에 사용하는 노드 구조체
- id : 현재 노드 번호
- dist : 현재 노드까지의 최소 비용
- edge_count : 현재 노드까지의 최소 비용으로 도착한 간선의 개수

```cpp
struct Node {
	int id;
	int dist;
	int edge_count;
	Node(int id, int dist, int edge_count) : id(id), dist(dist), edge_count(edge_count) {}
};
```

- graph : 간선 정보를 저장하는 벡터
- dist : dist[i][j] : i번 노드까지 j개의 간선을 사용하여 도착하는 최소 비용 (DP 메모이제이션 배열)

```cpp
vector<Edge> graph[1000];
int dist[1001][1001];
```

다익스트라 알고리즘을 사용하여 각 노드의 소요 간선 별 최소 비용을 구한다.

다만 같은 노드라도 다른 간선의 개수로 도착하는 최소 비용이 다를 수 있으므로 전체 탐색을 해야 하기 때문에 정렬에 시간이 추가로 소요되는 priority queue가 아닌 일반 queue를 사용했다.

```cpp
void dijkstra(int s, int e) {
	queue<Node> pq;
	pq.push(Node(s, 0, 0));
	dist[s][0] = 0;
	while (!pq.empty()) {
		Node node = pq.front();
		pq.pop();
		if (node.dist > dist[node.id][node.edge_count]) continue;
		for (Edge edge : graph[node.id]) {
			int next_dist = node.dist + edge.cost;
			if (next_dist < dist[edge.to][node.edge_count + 1]) {
				dist[edge.to][node.edge_count + 1] = next_dist;
				pq.push({ edge.to, next_dist, node.edge_count + 1 });
			}
		}
	}
}
```

main 함수에서는 입력을 받고 다익스트라 알고리즘을 사용하여 최소 비용을 구한다.

```cpp
int n, m, k, a, b;
cin >> n >> m >> k >> a >> b;
for (int i = 1; i <= n; i++)
	for (int j = 0; j < n; j++)
		dist[i][j] = numeric_limits<int>::max();
for (int i = 0; i < m; ++i)
{
	int f, t, c;
	cin >> f >> t >> c;
	graph[f].push_back(Edge(t, c));
	graph[t].push_back(Edge(f, c));
}

dijkstra(a, b);
```

그리디 알고리즘을 사용하여 세금이 오르기 전의 최소 비용을 구한다.

이 과정에서 세금이 올라도 최소 비용이 될 수 없는 경우는 제외한다.
최소 비용과 간선의 개수가 둘 다 다른 경우보다 크다면 최소 비용이 될 수 없다.

기존의 배열은 간선의 개수가 작은 순으로 정렬되어 있으므로 최소 비용을 기준으로 선별한다.

```cpp
vector<pair<int, int>> cand;
int min_dist = dist[b][0];
for (int i = 1; i < n; ++i) {
	if (dist[b][i] < min_dist) {
		min_dist = dist[b][i];
		cand.emplace_back(dist[b][i], i);
	}
}
sort(cand.begin(), cand.end());
cout << cand[0].first << '\n';
```

세금을 입력받아 최소 비용을 업데이트하고, 최소 비용을 기준으로 정렬 후, 간선의 개수를 기준으로 선별한다.

```cpp
for (int i = 0; i < k; ++i) {
	int p;
	cin >> p;
	for (int j = 0; j < cand.size(); ++j)
		cand[j].first += p * cand[j].second;
	sort(cand.begin(), cand.end());
	vector<pair<int, int>> new_cand;
	new_cand.push_back(cand[0]);
	int min_edge_count = cand[0].second;
	for (int j = 1; j < cand.size(); ++j)
		if (cand[j].second < min_edge_count)
			new_cand.push_back(cand[j]);
	cand = new_cand;
	cout << cand[0].first << '\n';
}
```

## 성능 요약

메모리: 6892 KB, 시간: 252 ms

결과 : [맞았습니다!!](http://boj.kr/fa8291c7c7cb405aa982550262e37db4)