# 2194 유닛 이동시키기
'''
최소 이동 횟수 -> bfs 고려

1. 유닛을 graph에서 어떤식으로 표현할지?
2. 장애물과의 충돌을 어떻게 판단할지?
3. 유닛의 이동을 어떻게 처리할지?
4. 유닛의 도착을 어떻게 처리할지?

unit
unit의 크기에 맞게 순찰을 돌면서
장애물과 부딪히는지를 판단하면 될 듯?

'''

from collections import deque

def Unit_check(x,y):
    '''
    unit이 graph밖으로 나가면 False
    unit이 장애물을 만나면 False
    그 외 : True
    '''
    for i in range(x,a):
        for j in range(y,b):
            if i < 0 or i > (n-1) or j < 0 or j > (m-1):    # graph 밖으로 나가면
                return False
            if graph[i][j] == -1:   # 장애물을 만나면
                return False

    return True # 그 외 True return


def Bfs():
    deq = deque()
    deq.append(start)   # 시작점 넣기
    dx = [0,0,-1,1] # 상하좌우 설정
    dy = [1,-1,0,0]

    while deq:
        x,y = deq.popleft()
        if [x,y] == end:    # 도착점에 도착하면 최소 회수 출력
            return graph[x][y]
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            #### unit의 한 원소라도 graph 밖으로 나가는지 판단 할 수 있게 수정
            if nx < 0 or nx > (n-1) or ny < 0 or ny > (m-1):
                continue
            if Unit_check(nx,ny):
                graph[nx][ny] = graph[x][y] + 1
                deq.append([nx,ny])


    print(-1)
    return


# 1. 입력 받기
# n: graph세로(행,x), m: graph가로(열,y), a: unit세로(행), b: unit가로(열), k: 장애물 개수
n, m, a, b, k = map(int,input().split())

# graph 만들기
graph = [[0 for j in range(m)] for i in range(n)]

# 장애물 표시
for i in range(k):
    x,y = map(int,input().split())
    graph[x-1][y-1] = -1

# 시작점, 끝점
start = list(map(int,input().split()))
start[0] -= 1
start[1] -= 1
end = list(map(int,input().split()))
end[0] -= 1
end[1] -= 1

Bfs()