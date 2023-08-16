# [BoJ 16985 bfs](https://www.acmicpc.net/problem/20529)

solved.ac Gold 2

## 카테고리

완전탐색, bfs

## 시간복잡도

O(N^5)?

## 풀이

1.입력받기
2. 층 별로 permuation(순열) 찾기
3. 회전 -> 90도씩 반복
4. 탐색 -> bfs
5. 최소 횟수 -> bfs돌린 결과 나올 때 마다 작은수 업데이트

```python
from itertools import permutations
from collections import deque
from copy import deepcopy

def Search():
    global cnt
    for _ in range(4):
        for _ in range(4):
            for _ in range(4):
                for _ in range(4):
                    for _ in range(4):
                        Rotation(4) # 90도 회전
                        cnt = min(cnt,Bfs(temp_graph))  # bfs로 현재 그래프에서의 최소 횟수 탐색
                        if cnt == 12:
                            return
                    Rotation(3)
                Rotation(2)
            Rotation(1)
        Rotation(0)
    return

def Maaaaaaaaaze():
    for c in permutations(range(5)):
        for i in range(5):
            temp_graph[i] = graph[c[i]] # 경우의 수
        Search()    # cnt return


# 그래프 90도 회전
def Rotation(n):
    temp = [[0 for j in range(5)] for i in range(5)]
    for i in range(5):
        for j in range(5):
            temp[i][j] = temp_graph[n][4-j][i]
    temp_graph[n] = temp
    return

# bfs로 최소 횟수 탐색
def Bfs(graph):
    if graph[0][0][0] == 0 or graph[4][4][4] == 0: # 시작이나 끝이 막혀있으면 탈출 불가
        return 126
    deq = deque()
    deq.append([0,0,0])
    visit = [[[-1,-1,-1,-1,-1] for j in range(5)] for i in range(5)]

    visit[0][0][0] = 0  # 시작점
    dx = [0,0,1,-1,0,0]
    dy = [1,-1,0,0,0,0]
    dz = [0,0,0,0,1,-1]

    while deq:
        z,x,y = deq.popleft()
        if z == 4 and x == 4 and y == 4:    #출구 도착
            return visit[z][x][y]

        if visit[z][x][y] > cnt:    # 시간 단축(현재 탐색중인게 현재cnt값 보다 높아지면 중단)
            return visit[z][x][y]

        for i in range(6):
            nx = x + dx[i]
            ny = y + dy[i]
            nz = z + dz[i]

            if nx < 0 or nx > 4 or ny < 0 or ny > 4 or nz < 0 or nz > 4:
                continue
            if graph[nz][nx][ny] == 1 and visit[nz][nx][ny] == -1:
                visit[nz][nx][ny] = visit[z][x][y] + 1
                deq.append([nz,nx,ny])
    # 출구 도달 못하면
    return 126

# 1. 입력 받기
graph = [[list(map(int,input().split())) for j in range(5)]for i in range(5)]
temp_graph = [[[0,0,0,0,0]for j in range(5)]for i in range(5)]
cnt = 126

Maaaaaaaaaze()
if cnt == 126:
    print(-1)
else:
    print(cnt)
```

## 결과

결과 : [맞았습니다!!]
