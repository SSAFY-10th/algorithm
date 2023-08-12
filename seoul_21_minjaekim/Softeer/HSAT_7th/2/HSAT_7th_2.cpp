#include<iostream>

using namespace std;

struct Pos {
    int row, col;
};

struct Directions {
    int drow, dcol;
};

Directions direc[4] = {
  {-1, 0},
  {0, -1},
  {1, 0},
  {0, 1}
};

bool visited[4][4];
int pos_map[4][4];

int dfs(int n, int end_idx, Pos cur_pos, int pos_idx = 2) {
    int cur_idx = pos_map[cur_pos.row][cur_pos.col];
    if (cur_idx == end_idx && cur_idx == pos_idx)
        return 1;
    else if (cur_idx == pos_idx)
        ++pos_idx;
    else if (cur_idx)
        return 0;

    int count = 0;
    for (auto d : direc) {
        Pos next_pos;
        next_pos.row = cur_pos.row + d.drow;
        next_pos.col = cur_pos.col + d.dcol;

        if (next_pos.row < 0 || next_pos.row >= n || next_pos.col < 0 || next_pos.col >= n || visited[next_pos.row][next_pos.col])
            continue;

        visited[next_pos.row][next_pos.col] = true;
        count += dfs(n, end_idx, next_pos, pos_idx);
        visited[next_pos.row][next_pos.col] = false;
    }
    return count;
}

int main(int argc, char** argv)
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, m;
    cin >> n >> m;

    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            cin >> visited[i][j];

    int row, col;
    Pos start_pos;
    cin >> row >> col;
    visited[row - 1][col - 1] = true;
    start_pos.row = row - 1;
    start_pos.col = col - 1;

    for (int i = 2; i <= m; ++i) {
        Pos pos;
        cin >> row >> col;
        pos_map[row - 1][col - 1] = i;
        pos.row = row - 1;
        pos.col = col - 1;
    }

    cout << dfs(n, m, start_pos) << '\n';

    return 0;
}
