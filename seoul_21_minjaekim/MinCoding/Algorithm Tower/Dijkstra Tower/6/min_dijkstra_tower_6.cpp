#include <algorithm>
#include <iostream>
#include <queue>

#define MAX_SIZE 1000

using namespace std;

int map[MAX_SIZE][MAX_SIZE];
bool visited[MAX_SIZE][MAX_SIZE];
pair<int, int> directions[4] = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

int dijkstra(pair<int, int> start = { 0, 0 }, int map_size = MAX_SIZE) {
    priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<>> pq;
    pq.emplace(map[start.second][start.first], start);
    visited[start.second][start.first] = true;
    int max_dist = 0;

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
        }
    }

    cout << dijkstra({ x, y }, n) << '\n';

    return 0;
}