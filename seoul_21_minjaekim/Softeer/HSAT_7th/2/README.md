# [7차 Softeer 정기 역량 진단] 2번 - 순서대로 방문하기

## 카테고리

DFS, 백트래킹

## 시간복잡도

`O(4 ^ (N ^ 2))`

## 문제

주어진 지도에서 입력 받은 방문해야 하는 지점을 순서대로 방문할 수 있는 서로 다른 방법의 수를 구하는 프로그램을 작성하시오.

입력 받은 방문해야 하는 지점의 첫 번째 지점이 출발지이다.

입력 받은 방문해야 하는 지점의 마지막 지점이 도착지이다.

한 번 지났던 지점은 다시 지날 수 없다.

## 제약 조건

- 2 ≤ n ≤ 4
- 2 ≤ m ≤ n^2

## 시간 제약

- Java : 1초
- C++ : 1초
- Python : 1초

## 입력 형식

- 첫 번째 줄에 격자의 크기 `n`, 순서대로 방문해야 하는 칸의 수 `m`이 주어진다.
- 두 번째 줄부터 `n`개의 줄에 걸쳐 각 행에 해당하는 `n`개의 수가 `0` 혹은 `1`로 주어진다. `0`은 빈 칸, `1`은 벽을 의미한다.
- `n + 2` 번째 줄부터 `m`개의 줄에 걸쳐 방문해야 하는 `m`개의 칸의 위치가 `row`, `col` 쌍으로 주어진다. 주어지는 칸에는 벽이 없고, 동일한 칸이 여러 번 주어지는 경우는 없다.

## 출력 형식

차량이 `m`개의 지점을 순서대로 방문할 수 있는 서로 다른 방법의 수를 출력한다.
만약 가능한 방법이 없다면 `0`을 출력한다.

## 해설

각 지점의 행, 열 정보를 저장하는 구조체 `Pos`와 각 방향의 행, 열 정보를 저장하는 구조체 `Directions`를 정의한다.

`direc` 배열에는 각 방향으로의 행, 열 정보를 저장한다.

```cpp
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
```

방문 순서를 저장하는 `pos_map` 배열과 방문 여부를 저장하는 `visited` 배열을 정의한다.

```cpp
bool visited[4][4];
int pos_map[4][4];
```

벽의 위치를 `visited`에 방문함으로 표시한다.

```cpp
int n, m;
cin >> n >> m;

for (int i = 0; i < n; ++i)
    for (int j = 0; j < n; ++j)
        cin >> visited[i][j];
```

첫 번째 지점의 정보를 입력받고, `visited`에 방문함으로 표시한다.

```cpp
int row, col;
Pos start_pos;
cin >> row >> col;
visited[row - 1][col - 1] = true;
start_pos.row = row - 1;
start_pos.col = col - 1;
```

두 번째 지점부터 `m`개의 지점의 정보를 입력받고, `pos_map`에 지점의 순서를 저장한다.

```cpp
for (int i = 2; i <= m; ++i) {
    Pos pos;
    cin >> row >> col;
    pos_map[row - 1][col - 1] = i;
    pos.row = row - 1;
    pos.col = col - 1;
}
```

`dfs` 함수를 통해 경로의 수를 구한다.

```cpp
cout << dfs(n, m, start_pos) << '\n';
```

현재 지점의 정보를 확인해 백트래킹을 수행한다.

현재 지점이 도착지라면 `1`을 반환한다.

현재 지점이 도착지가 아니고, 현재 지점이 이번에 방문할 순서의 지점이라면 `pos_idx`를 증가시킨다.

현재 지점이 이번에 방문할 순서의 지점이 아니라면 `0`을 반환한다.

```cpp
int cur_idx = pos_map[cur_pos.row][cur_pos.col];
if (cur_idx == end_idx && cur_idx == pos_idx)
    return 1;
else if (cur_idx == pos_idx)
    ++pos_idx;
else if (cur_idx)
    return 0;
```

`dfs` 알고리즘을 이용해 다음 지점이 이미 방문한 지점이 아니라면, 다음 지점으로 재귀적으로 이동해 경로의 수를 구한다.

리턴을 이용해 목적지까지 도착 가능한 경로의 수를 누적시킨다.

```cpp
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
```

## 문제 분석

`dfs` 문제에 특정 지점들을 순서대로 방문하는 조건이 추가된 문제이다.

해당 지점들을 알맞는 순서에 방문하지 않으면 `백트래킹`을 통해 시간을 단축시킬 수 있다.
