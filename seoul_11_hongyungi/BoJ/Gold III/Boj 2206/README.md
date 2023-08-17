# [백준 2206 벽 부수고 이동하기](https://www.acmicpc.net/problem/2206)

solved.ac Gold III

## 카테고리

그래프 이론, 그래프 탐색, 너비 우선 탐색(bfs)

## 전체 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map, visit;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int N, M;

    static int result = Integer.MAX_VALUE;

    static class Pos {
        int x;
        int y;
        int dis;
        int smash;

        Pos(int x, int y, int dis, int smash) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.smash = smash;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        visit = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            String line = br.readLine();

            for(int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j - 1)));
            }
            Arrays.fill(visit[i], Integer.MAX_VALUE);
        }

        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(1,1,1,0));
        visit[1][1] = 0;

        while(!q.isEmpty()) {
            Pos now = q.poll();

            if(now.x == M && now.y == N) {
                System.out.println(now.dis);
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if(nextX <= 0 || nextY <= 0 || nextX > M || nextY > N)
                    continue;

                if(visit[nextY][nextX] > now.smash) {
                    if(now.smash == 0 && map[nextY][nextX] == 1) {
                        q.add(new Pos(nextX, nextY, now.dis + 1, 1));
                        visit[nextY][nextX] = 1;
                    } else if(map[nextY][nextX] == 0) {
                        q.add(new Pos(nextX, nextY, now.dis + 1, now.smash));
                        visit[nextY][nextX] = now.smash;
                    }
                }
            }
        }

        System.out.println(-1);
    }
}
```

## 시간복잡도

N X M 행렬 맵에서 BFS를 통해 (1, 1)부터 (N, M)까지 가는 최단 거리를 구하므로 시간복잡도는 O(N X M)이다.

## 풀이

**⏱️소요시간 60분 이상**<br>

가중치가 없는 행렬 맵에서 최단거리를 구해야 하므로 공식처럼 BFS 알고리즘을 꺼내게 된다. 하지만 일반적인 BFS와는 다르게 방문체크를 위한 boolean 타입의 2차원 배열을 사용할 수 없다.

문제에서는 단순히 '방문을 했다, 방문하지 않았다'라는 `두가지 상태`로 모든 경우의 수를 표현할 수 없다. '아직 한번도 방문을 하지 않았는가, 또는 벽을 한번도 부순적 없는 채로 방문하였는가, 또는 벽을 한번 부순채로 방문하였는가'라는 `세가지 상태`를 표현하여야 한다. 때문에 true of false로는 구분이 불가능하다.

따라서 선택할 수 있는 경우는 두가지이다.<br>
1. 기존에 boolean 타입 2차원 배열로 체크하던 방문체크를 int 타입으로 바꿔 다양한 표현을 가능하게 할 수 있다.
2. boolean 타입 2차원 배열을 한 차원 늘려 3차원으로 선언한 후 더 다양한 표현을 가능하게 할 수 있다.

이번 문제에서는 1번 방법을 활용하여 풀었지만 코드를 직관적으로 이해하는 것은 2번 방법이 더 효율적인 것 같다.

현재 움직이고 있는 위치의 상태를 저장하기 위한 클래스를 선언한다. 우리는 이 Pos 클래스를 Queue에 넣고 빼며 BFS를 돌릴 것이다. 현재 위치에서 (1, 1)까지 떨어진 거리를 기억하기 위해 `int dis`를, 벽을 부순적 있는지 없는지를 기억하기 위해 `int smash`를 멤버 변수로 사용한다.
```java
static class Pos {
    int x;
    int y;
    int dis;    // (1, 1)까지의 거리이다.
    int smash;  // 벽을 부순 적 있으면 1, 없으면 0이다.

    Pos(int x, int y, int dis, int smash) {
        this.x = x;
        this.y = y;
        this.dis = dis;
        this.smash = smash;
    }
}
```

코드에서는 한번도 방문하지 않은 상태를 Integer.MAX_VALUE로 표현한다. 또한 벽을 부수게 되면 visit의 값은 1이 되고 다음 Queue에서 꺼내졌을 때 visit에 해당 상태를 그대로 건내주게 된다.
```java
/**
 * 반복문 내에서
 */
Arrays.fill(visit[i], Integer.MAX_VALUE);

/**
 * BFS 내에서
 */
if(now.smash == 0 && map[nextY][nextX] == 1) {
    q.add(new Pos(nextX, nextY, now.dis + 1, 1));
    visit[nextY][nextX] = 1;            // 벽을 부수므로 상태가 1이 된다.
} else if(map[nextY][nextX] == 0) {
    q.add(new Pos(nextX, nextY, now.dis + 1, now.smash));
    visit[nextY][nextX] = now.smash;    // 상태를 그대로 건내 준다.
}
```

BFS의 조건에서 몇가지 주의할 점이 있다.<br>
1. 방문 상태가 0인데(벽을 안부수고 방문했는데) smash상태가 1인 점은 갈 수 없다. <- 최단거리로 가려고 벽을 부수는데 충분히 0이 갈 수 있는 점이면 의미가 없는 행동이다.
2. 같은 상태가 방문한 점은 또 방문할 수 없다.
3. 방문 상태가 1이고 smash 상태가 0이면 1과 같은 이유로 방문 상태를 0으로 바꿔야 한다.

위 모든 조건을 만족하기 위한 BFS 조건은 아래처럼 간단하게 표현된다.

```java
if(visit[nextY][nextX] > now.smash) {
    if(now.smash == 0 && map[nextY][nextX] == 1) {
        q.add(new Pos(nextX, nextY, now.dis + 1, 1));
        visit[nextY][nextX] = 1;
    } else if(map[nextY][nextX] == 0) {
        q.add(new Pos(nextX, nextY, now.dis + 1, now.smash));
        visit[nextY][nextX] = now.smash;
    }
}
```
## 결과

결과 : [맞았습니다!!](https://www.acmicpc.net/source/65045417)<br>
메모리 : 172188 KB<br>
시간 : 588 ms<br>
언어 : JAVA<br>
코드길이 : 1850 B<br>
