def dfs(curr):
    visited.add(curr)
    for next in edge[curr]:
        if next in visited:
            continue
        dfs(next)

T = int(input())
for t in range(1, T + 1):
    N, M = map(int, input().split())
    edge = dict()
    for start in range(1, N + 1):
        edge[start] = []
    for _ in range(M):
        s, e = map(int, input().split())
        edge[s].append(e)
        edge[e].append(s)
    visited = set()
    group = 0
    for start in range(1, N + 1):
        if start in visited:
            continue
        dfs(start)
        group += 1
    print(f'#{t} {group}')