# [백준 1986 체스](https://www.acmicpc.net/problem/1986)

solved.ac Silver I

## 카테고리

구현

## 시간복잡도

map의 가로세로 길이가 n,m으로 나뉘긴 하지만 최악의 경우 둘다 1000이므로 n이라고 간단하게 생각하겠다.
Queen이 지나갈 수 있는 경로를 계산하는데 O(q X n) <- 여기서 q는 퀸의 개수이다(최대 100개).
Knight 경로를 계산하는데 O(k) <- k는 마찬가지로 나이트의 개수이다(최대 100개).

구현 로직을 계산하는 부분보다 정답을 카운팅하는 부분이 시간복잡도가 더 크다.
모든 체스칸을 하나하나 방문하므로 O(n X m)이다.

n,m의 최대 크기는 1000이므로 모든 과정이 충분히 2초 안에 들어오게 된다.

## 풀이

풀이는 핵심이 되는 세 부분만 짚고 넘어가겠다.

1. 폰은 움직이지 못하는 장애물이다. 아무런 코드도 적지 않고 그저 돌맹이로 보겠다. 다른 말이라고 다를 것 없다. 폰과 다르게 경로를 가질 뿐이지 움직이지 못하는 장애물인건 마찬가지다. 
그래서 입력 받을 때 말의 위치를 map에 2로 저장했다.

```java
//queen 입력
st = new StringTokenizer(br.readLine());
int queenNum = Integer.parseInt(st.nextToken());
Pos[] queens = new Pos[queenNum];
for(int i = 0;i < queenNum;i++) {
    int y = Integer.parseInt(st.nextToken());
    int x = Integer.parseInt(st.nextToken());

    map[y][x] = 2;

    queens[i] = new Pos(x, y);
}
```

2. 다음은 퀸의 동작 과정이다. 퀸이 갈 수 있는 방향을 미리 배열로 만들어 두고 모든 방향으로 장애물을 마주치거나 벽을 마주칠 때까지 전진시킨다.
퀸의 경로에는 map에 1을 저장한다.
```java
// 퀸 동작
public static int[] queen_dx = {0,1,1,1,0,-1,-1,-1};
public static int[] queen_dy = {1,1,0,-1,-1,-1,0,1};

for(int i = 0; i < queenNum;i++) {
    int queenX = queens[i].x;
    int queenY = queens[i].y;

    for(int j = 0; j < 8; j++) {
        int nowX = queenX;
        int nowY = queenY;
        while(true) {
            int nextX = nowX + queen_dx[j];
            int nextY = nowY + queen_dy[j];

            if(nextX < 1 || nextX > m || nextY < 1 || nextY > n)
                break;
            if(map[nextY][nextX] == 2)
                break;

            map[nextY][nextX] = 1;
            nowX = nextX;
            nowY = nextY;
        }
    }
}
```

3. 나이트는 퀸처럼 앞으로 계속 나아가는 성질은 없기 때문에 갈 수 있는 경로만 미리 배열에 넣고 나이트 현재 위치에서 갈 수 있는 위치에 1만 저장해 주었다.

```java
// 나이트 동작
for(int i = 0; i < knightNum;i++) {
    int knightX = knights[i].x;
    int knightY = knights[i].y;

    for(int j = 0; j < 8; j++) {
        int nextX = knightX + knight_dx[j];
        int nextY = knightY + knight_dy[j];

        if(nextX < 1 || nextX > m || nextY < 1 || nextY > n)
            continue;
        if(map[nextY][nextX] == 2)
            continue;

        map[nextY][nextX] = 1;
    }
}
```

마지막엔 모든 체스판을 돌면서 0인 부분만 카운팅해주고 결과를 출력한다.

## 결과

결과 : [맞았습니다!!](https://www.acmicpc.net/source/63662079)
