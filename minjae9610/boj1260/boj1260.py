import sys
from collections import defaultdict, deque


def dfs(node):
    dfs_visited[node] = True

    visited_node = [node]
    for path in sorted(graph[node]):
        if not dfs_visited[path]:
            visited_node += dfs(path)

    return visited_node


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


N, M, V = map(int, sys.stdin.readline().split())
graph = defaultdict(set)
for _ in range(M):
    s, e = map(int, sys.stdin.readline().split())
    graph[s].add(e)
    graph[e].add(s)

dfs_visited = [False] * (N + 1)
bfs_visited = [False] * (N + 1)

sys.stdout.write(' '.join(map(str, dfs(V))) + "\n" + ' '.join(map(str, bfs(V))))
