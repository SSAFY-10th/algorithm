# [BoJ 2098 외판원 순회](https://www.acmicpc.net/problem/2098)

## 카테고리

다이나믹 프로그래밍, 비트마스킹, 비트필드를 이용한 다이나믹 프로그래밍, 외판원 순회 문제

## 시간복잡도

O(n * 2^n)

## 해설

노드의 개수에 따라서 완료시의 비트필드 형태를 설정하고, 그래프의 인접행렬을 입력받는다.

```cpp
void initial_graph() {
    finish = (1 << n) - 1;
    for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
            cin >> graph[i][j];
}
```

DFS 재귀문의 종료조건으로 비트필드가 완료 조건을 달성하면, 0번 노드로 돌아가는 거리를 반환한다.

```cpp
if (visited == finish)
    return graph[current][0];
```

이미 현재의 방문 상태와 동일한 상태에 대한 메모가 존재한다면, 그 값을 반환한다.

```cpp
if (memo[current][visited])
    return memo[current][visited];
```

DFS를 통해 현재 노드에서 방문하지 않은 노드들을 방문하고, 그 노드들에서 0번 노드로 돌아가는 거리를 구한다.
그 중 최소값을 반환한다.

```cpp
memo[current][visited] = INT_MAX;
for (int i = 0; i < n; ++i) {
    if (graph[current][i] and !(visited bitand (1 << i))) {
        int result = dfs(i, visited bitor (1 << i));
        if (result and result != INT_MAX)
            memo[current][visited] = min(result + graph[current][i], memo[current][visited]);
    }
}
return memo[current][visited];
```

## 성능 요약

메모리: 6116 KB, 시간: 28 ms

결과 : [맞았습니다!!](http://boj.kr/604c9e78d1184ccf97b0bf5479fec2bd)