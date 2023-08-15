#include <algorithm>
#include <iostream>
#include <limits>
#include <queue>
#include <vector>

using namespace std;

struct Edge {
	int to;
	int cost;
	Edge(int to, int cost) : to(to), cost(cost) {}
};

struct Node {
	int id;
	int dist;
	int edge_count;
	Node(int id, int dist, int edge_count) : id(id), dist(dist), edge_count(edge_count) {}
};

vector<Edge> graph[1000];
int dist[1001][1001];

void dijkstra(int s, int e) {
	queue<Node> que;
	que.push(Node(s, 0, 0));
	dist[s][0] = 0;
	while (!que.empty()) {
		Node node = que.front();
		que.pop();
		if (node.dist > dist[node.id][node.edge_count]) continue;
		for (Edge edge : graph[node.id]) {
			int next_dist = node.dist + edge.cost;
			if (next_dist < dist[edge.to][node.edge_count + 1]) {
				dist[edge.to][node.edge_count + 1] = next_dist;
				que.push({ edge.to, next_dist, node.edge_count + 1 });
			}
		}
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

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

	return 0;
}
