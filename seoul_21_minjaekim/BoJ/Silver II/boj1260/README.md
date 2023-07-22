# [BoJ 1260 DFS와 BFS](https://www.acmicpc.net/problem/1260)

solved.ac Silver II

## 카테고리

그래프 이론, 그래프 탐색, 너비 우선 탐색, 깊이 우선 탐색

## 시간복잡도

DFS와 BFS 모두 각 노드와 에지를 한 번씩 방문하므로 시간 복잡도는 O(N + M)이다.

## 풀이

재귀를 이용한 dfs 구현

```python
def dfs(node):
    dfs_visited[node] = True

    visited_node = [node]
    for path in sorted(graph[node]):
        if not dfs_visited[path]:
            visited_node += dfs(path)

    return visited_node
```

queue를 이용한 bfs 구현

```python
def bfs(start):
    bfs_visited[start] = True
    que = deque([start])

    visited_node = []
    while que:
        visited_node.append(que.popleft())
        for node in sorted(graph[visited_node[-1]]):
            if not bfs_visited[node]:
                que.append(node)
                bfs_visited[node] = True

    return visited_node
```

dict를 이용한 graph path 핸들링

```python
N, M, V = map(int, sys.stdin.readline().split())
graph = defaultdict(set)
for _ in range(M):
    s, e = map(int, sys.stdin.readline().split())
    graph[s].add(e)
    graph[e].add(s)
```

list를 이용한 방문 체크

```python
dfs_visited = [False] * (N + 1)
bfs_visited = [False] * (N + 1)
```

실행 및 결과 출력

```python
sys.stdout.write(' '.join(map(str, dfs(V))) + "\n" + ' '.join(map(str, bfs(V))))
```

## 결과

결과 : [맞았습니다!!](http://boj.kr/34fe82abdd904b09bbbf7943d8376185)
