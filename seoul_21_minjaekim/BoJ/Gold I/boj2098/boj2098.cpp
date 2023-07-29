#include <iostream>
#include <algorithm>
#include <limits.h>

using namespace std;

int n;
int graph[16][16];
int memo[16][1 << 16];
short finish;

int dfs(int current = 0, short visited = 1) {
    if (visited == finish)
        return graph[current][0];

    if (memo[current][visited])
        return memo[current][visited];

    memo[current][visited] = INT_MAX;
    for (int i = 0; i < n; ++i) {
        if (graph[current][i] and !(visited bitand (1 << i))) {
            int result = dfs(i, visited bitor (1 << i));
            if (result and result != INT_MAX)
                memo[current][visited] = min(result + graph[current][i], memo[current][visited]);
        }
    }
    return memo[current][visited];
}

void initial_graph() {
    finish = (1 << n) - 1;
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            cin >> graph[i][j];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> n;
    initial_graph();
    cout << dfs() << '\n';

    return 0;
}