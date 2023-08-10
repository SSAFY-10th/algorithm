# [알고리즘 탑] Dijkstra의 탑 6번 - 무서운 시어머니

## 카테고리

`Dijkstra`

## 시간복잡도

`O(n^2 * log n)`

## 풀이

### 처음 풀었던 풀이

전형적인 다익스트라 알고리즘을 이용해 전체 좌표에 대한 최단 거리를 전부 구한 상태에서, 최대 거리를 찾는다.

```cpp
#include <algorithm>
#include <iostream>
#include <queue>
#include <limits>

#define MAX_SIZE 1000

using namespace std;

int map[MAX_SIZE][MAX_SIZE];
int dist[MAX_SIZE][MAX_SIZE];
pair<int, int> directions[4] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

int dijkstra(pair<int, int> start = {0, 0}, int map_size = MAX_SIZE) {
    priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<>> pq;
    pq.emplace(map[start.second][start.first], start);
    dist[start.second][start.first] = map[start.second][start.first];

    while(!pq.empty()) {
        auto [cost, pos] = pq.top();
        auto [cur_x, cur_y] = pos;
        pq.pop();

        if (dist[cur_y][cur_x] < cost) continue;

        for (auto [dx, dy] : directions) {
            int next_x = cur_x + dx;
            int next_y = cur_y + dy;

            if (next_x < 0 || next_x >= map_size || next_y < 0 || next_y >= map_size || map[next_y][next_x] == -1) continue;

            int next_cost = cost + map[next_y][next_x];
            if (dist[next_y][next_x] > next_cost) {
                dist[next_y][next_x] = next_cost;
                pq.emplace(next_cost, make_pair(next_x, next_y));
            }
        }
    }

    int max_dist = 0;
    for (int i = 0; i < map_size; ++i)
        for (int j = 0; j < map_size; ++j)
            max_dist = max(max_dist, dist[i][j]);

    return max_dist;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int y, x, n;
    cin >> y >> x >> n;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            cin >> map[i][j];
            if (map[i][j] != -1)
                dist[i][j] = numeric_limits<int>::max();
        }
    }

    cout << dijkstra({x, y}, n) << '\n';

    return 0;
}
```

### 개선한 풀이

> 일반적인 그래프 상황에서 다익스트라 알고리즘을 적용하는 상황과는 다르게, 이 문제에서는 간선이 아닌 노드가 스스로 가중치를 가지고 있다.
>
> 때문에 득정 노드로 들어가는 모든 간선은 같은 가중치를 가지고 있으므로, 특정 노드에 대한 최단 경로는 해당 노드 직전 노드들의 누적 가중치 중 가장 작은 값을 가진 노드가 된다.
>
> 특정 노드에 인접한 노드들은 `priority queue`에 의해 정렬되므로, 특정 노드에 가장 처음 도달한 노드를 통하는 경로가 최단 경로임을 보장할 수 있다.
>
> 이 부분은 특정 노드에 도달하기 직전 노드들에도 똑같이 적용이 가능하며, 이를 이용해 귀납적으로 모든 노드에 대하여 가장 먼저 방문하는 경로가 최단 경로임을 보장할 수 있다.

일반적으로 간선에 가중치가 있는 상황을 가정해보자.
```
A-1-B
|   |
2   100
|   |
C-3-D
```
위와 같은 상황에서 `A`노드에서 `B`노드로의 최소거리를 `다익스트라 알고리즘`으로 구하게 된다면, 첫 번째 단계에서의 `pq`는 `B, C` 의 우선순위를 가지게 된다.
이로 인해 D 노드에 가장 먼저 방문하는 경로는 `A-B-C`의 `101`거리의 경로가 되고 이는 `A-C-D`의 `5`거리의 경로보다 긴 거리다.

이와 같은 상황때문에 `다익스트라 알고리즘`은 `BFS`와는 다르게 가장 먼저 방문한 경로의 거리가 최소 거리라고 보장하지 못하고 목표 노드로 도착할 수 있는 모든 경로를 탐색한 뒤에 더이상 목표 노드로 가는 더 작은 경로가 없을때에서야 최소 경로를 보장할 수 있디.

그러나 해당 문제와 같이 노드 자체에 가중치가 있다면 각 노드로 들어가는 모든 간선은 같은 가중치를 가지게 된다.

```
A--1--B
|     |
2     100
|     |
C-100-D
```

위와 같은 조건 하에는 `D`노드로 들어가는 최소 경로는 `B`노드의 최소경로와 `C`노드의 최소 경로의 우선순위와 동일함을 보장할 수 있게 되고, 이를 귀납적으로 축소해 보면 각 노드에 도착하는 첫번째 경로가 해당 노드로의 최소 경로임이 보장되게 되어서 첫번째 경로 이후로 추가적인 탐색이 필요없다는 결론에 도달하게 된다.

최단 경로를 구하기 위한 순서 정렬을 위해 priority queue를 오름차순으로 정렬하도록 한 뒤, 시작 노드를 넣어준다.

```cpp
priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<>> pq;
pq.emplace(map[start.second][start.first], start);
visited[start.second][start.first] = true;
```

조건에 맞는 탐색할 노드가 없어질때까지, 다음과 같은 과정을 반복한다.

1. 우선순위 큐에서 가장 작은 경로를 가진 노드를 꺼낸다.
2. 해당 노드에 인접한(위, 아래, 좌, 우) 노드들 중 방문하지 않은 노드를 해당 노드로 이동하는 가중치를 더해서 우선순위 큐에 넣어주고, 해당 노드를 방문한 것으로 체크한다.
3. 방문하지 않은 노드라는 의미는 현재 경로가 해당 노드에 방문이 가능한 최단 경로라는 의미이므로, 이전에 저장해뒀던 최대 거리와 현재 경로로 해당 노드에 방문이 가능한 거리를 비교해 최대 거리를 갱신해준다.

```cpp
while (!pq.empty()) {
    auto [cost, pos] = pq.top();
    auto [cur_x, cur_y] = pos;
    pq.pop();

    for (auto [dx, dy] : directions) {
        int next_x = cur_x + dx;
        int next_y = cur_y + dy;

        if (next_x < 0 || next_x >= map_size || next_y < 0 || next_y >= map_size || map[next_y][next_x] == -1) continue;

        if (!visited[next_y][next_x]) {
            int next_cost = cost + map[next_y][next_x];
            max_dist = max(max_dist, next_cost);
            visited[next_y][next_x] = true;
            pq.emplace(next_cost, make_pair(next_x, next_y));
        }
    }
}
```

## 성능 요약

메모리: 2 MB, 시간: 0 ms
