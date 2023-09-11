# [BoJ 17114 하이퍼토마토](https://www.acmicpc.net/problem/17114)

## 카테고리

그래프탐색, 너비우선탐색, 구현

## 시간복잡도

O(n)

## 해설

### 문제조건

![image](https://ssafy-enjoytrip-kkh.s3.ap-northeast-2.amazonaws.com/algo/%ED%95%98%EC%9D%B4%ED%8D%BC%ED%86%A0%EB%A7%88%ED%86%A01_%EC%A1%B0%EA%B1%B4.png)

일반적인 토마토 문제가 구데기컵을 만나서 기괴해진 문제입니다. 이전에 2차원, 3차원 토마토 문제를 풀어보았다면, 크게 어려움 없이 해결할 수 있지만,
입력이 11차원으로 주어지기때문에, 정신을 붙잡는 것이 중요했던 문제입니다.

### 입출력

![image](https://ssafy-enjoytrip-kkh.s3.ap-northeast-2.amazonaws.com/algo/%ED%95%98%EC%9D%B4%ED%8D%BC%ED%86%A0%EB%A7%88%ED%86%A02_%EC%9E%85%EC%B6%9C%EB%A0%A5.png)

입력으로는 m,n,o,p,q,r,s,t,u,v,w가 주어지고, 한줄씩 격자 공간의 정보가 담깁니다.
주어진 입력에서 22방향으로 토마토가 익는데, 이 때 전체 토마토가 익을 때 필요한 최소 시간을 출력하면 되는 문제입니다.

### 정답 풀이

토마토 풀이와 동일합니다. 하지만 n값 (mnopqrstuvw)가 최대 10^6이고, 토마토가 이동하는 방향이 22방향이기 때문에, 중간에 토마토가 전부 처리되었다면,
이때 즉시 반복문을 종료하는 것이 시간 단축에 유의미한 차이를 주었습니다.

**BFS**

```java
    private static int bfs() {
        int days = 0;
        loop : while (!queue.isEmpty()) {
            int size = queue.size();
            days++; // 바깥 반복이 시작될 때, 기간을 추가해줍니다.
            // 같은 너비에 있는 애들을 지웁니다.
            while (size-- > 0) {
                RottenTomato tomato = queue.poll();
                for (int[] d : deltas) {
                    int nw = tomato.w + d[0];
                    int nv = tomato.v + d[1];
                    int nu = tomato.u + d[2];
                    int nt = tomato.t + d[3];
                    int ns = tomato.s + d[4];
                    int nr = tomato.r + d[5];
                    int nq = tomato.q + d[6];
                    int np = tomato.p + d[7];
                    int no = tomato.o + d[8];
                    int nn = tomato.n + d[9];
                    int nm = tomato.m + d[10];

                    if (isIn(nw, nv, nu, nt, ns, nr, nq, np, no, nn, nm)) {
                        queue.add(new RottenTomato(nw, nv, nu, nt, ns, nr, nq, np, no, nn, nm));
                        map[nw][nv][nu][nt][ns][nr][nq][np][no][nn][nm] = BLOCK;
                        if (--nOfTomatos == 0) { // 만약 모든 토마토가 사라졌다면 종료합니다. 특히 반복이 많기 때문에, 해당 부분에서 종료해서 최적화를 해주어야 합니다.
                            break loop;
                        }
                    }
                }
            }
        }
        return days;
    }
```


## 전체 코드
```java
import java.io.*;
import java.util.*;

public class Main {
    static PScanner sc = new PScanner(System.in);
    static StringBuilder sb = new StringBuilder();

    static StringTokenizer st;
    static int[][][][][][][][][][][] map; // 전체 맵입니다.
    static int nOfTomatos = 0; // 정상적인 토마토의 수입니다.

    static Queue<RottenTomato> queue; // bfs를 위한 queue입니다.
    static final int BLOCK = -1;

    static int m;
    static int n;
    static int o;
    static int p;
    static int q;
    static int r;
    static int s;
    static int t;
    static int u;
    static int v;
    static int w;

    // 22방향 이동 탐색
    static int[][] deltas = {
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
    };

    public static void main(String[] args) throws Exception {

        m = sc.nextInt();
        n = sc.nextInt();
        o = sc.nextInt();
        p = sc.nextInt();
        q = sc.nextInt();
        r = sc.nextInt();
        s = sc.nextInt();
        t = sc.nextInt();
        u = sc.nextInt();
        v = sc.nextInt();
        w = sc.nextInt();
        map = new int[w][v][u][t][s][r][q][p][o][n][m]; // 맵의 크기를 받습니다. 해당 부분에서 유의해서 넣어줘야합니다.

        queue = new ArrayDeque<>();
        // 입력을 받습니다.
        for (int ww = 0; ww < w; ww++) {
            for (int vv = 0; vv < v; vv++) {
                for (int uu = 0; uu < u; uu++) {
                    for (int tt = 0; tt < t; tt++) {
                        for (int ss = 0; ss < s; ss++) {
                            for (int rr = 0; rr < r; rr++) {
                                for (int qq = 0; qq < q; qq++) {
                                    for (int pp = 0; pp < p; pp++) {
                                        for (int oo = 0; oo < o; oo++) {
                                            for (int nn = 0; nn < n; nn++) {
                                                for (int mm = 0; mm < m; mm++) {
                                                    int number = sc.nextInt();
                                                    if (number == 1) {
                                                        queue.add(new RottenTomato(ww, vv, uu, tt, ss, rr, qq, pp, oo, nn, mm));
                                                        number = -1;
                                                    } else if (number == 0) {
                                                        nOfTomatos++;
                                                    }
                                                    map[ww][vv][uu][tt][ss][rr][qq][pp][oo][nn][mm] = number;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // 만약 시작부터 정상 토마토가 없다면 0 을 출력합니다.
        if (nOfTomatos == 0) {
            System.out.println(0);
            return;
        }

        // bfs를 돌면서 토마토를 처리합니다.
        int days = bfs();
        if (nOfTomatos == 0) { // 정상 토마토가 0개가 되었다면 처리하는데 필요한 days를 작성합니다.
            System.out.println(days);
        } else // 모두 처리하지 못했다면 -1을 출력합니다.
            System.out.println(-1);


    }

    private static int bfs() {
        int days = 0;
        loop : while (!queue.isEmpty()) {
            int size = queue.size();
            days++; // 바깥 반복이 시작될 때, 기간을 추가해줍니다.
            // 같은 너비에 있는 애들을 지웁니다.
            while (size-- > 0) {
                RottenTomato tomato = queue.poll();
                for (int[] d : deltas) {
                    int nw = tomato.w + d[0];
                    int nv = tomato.v + d[1];
                    int nu = tomato.u + d[2];
                    int nt = tomato.t + d[3];
                    int ns = tomato.s + d[4];
                    int nr = tomato.r + d[5];
                    int nq = tomato.q + d[6];
                    int np = tomato.p + d[7];
                    int no = tomato.o + d[8];
                    int nn = tomato.n + d[9];
                    int nm = tomato.m + d[10];

                    if (isIn(nw, nv, nu, nt, ns, nr, nq, np, no, nn, nm)) {
                        queue.add(new RottenTomato(nw, nv, nu, nt, ns, nr, nq, np, no, nn, nm));
                        map[nw][nv][nu][nt][ns][nr][nq][np][no][nn][nm] = BLOCK;
                        if (--nOfTomatos == 0) { // 만약 모든 토마토가 사라졌다면 종료합니다. 특히 반복이 많기 때문에, 해당 부분에서 종료해서 최적화를 해주어야 합니다.
                            break loop;
                        }
                    }
                }
            }
        }
        return days;
    }

    // 해당 방향으로 이동 가능한지 체크하는 코드
    private static boolean isIn(int ww, int vv, int uu, int tt, int ss, int rr, int qq, int pp, int oo, int nn, int mm) {
        return ww >= 0 && ww < w &&
                vv >= 0 && vv < v &&
                uu >= 0 && uu < u &&
                tt >= 0 && tt < t &&
                ss >= 0 && ss < s &&
                rr >= 0 && rr < r &&
                qq >= 0 && qq < q &&
                pp >= 0 && pp < p &&
                oo >= 0 && oo < o &&
                nn >= 0 && nn < n &&
                mm >= 0 && mm < m && map[ww][vv][uu][tt][ss][rr][qq][pp][oo][nn][mm] == 0;
    }
    // Queue에서 사용할 토마토의 좌표 정보
    private static class RottenTomato {
        int w;
        int v;
        int u;
        int t;
        int s;
        int r;
        int q;
        int p;
        int o;
        int n;
        int m;

        public RottenTomato(int w, int v, int u, int t, int s, int r, int q, int p, int o, int n, int m) {
            this.w = w;
            this.v = v;
            this.u = u;
            this.t = t;
            this.s = s;
            this.r = r;
            this.q = q;
            this.p = p;
            this.o = o;
            this.n = n;
            this.m = m;
        }

    }
    // 입력 최적화 클래스
    static class PScanner{private final InputStreamReader in;private final char[]buf;private int len,ptr;public PScanner(InputStream input){in=new InputStreamReader(input);buf=new char[8192];}public boolean hasNext(){consume();return ptr<len&&buf[ptr]>' ';}public String next(){consume();char[]cbuf=new char[16];char clen=0;while((cbuf[clen++]=read())>' '){if(clen==cbuf.length)cbuf=Arrays.copyOf(cbuf,clen << 2);}return new String(cbuf,0,clen - 1);}public int nextInt(){consume();int v=0;char c=read();boolean neg=c=='-';if(neg)c=read();do{v=v * 10+c - '0';}while('0'<=(c=read())&&c<='9');return neg?-v:v;}public long nextLong(){consume();long v=0;char c=read();boolean neg=c=='-';if(neg)c=read();do{v=v * 10+c - '0';}while('0'<=(c=read())&&c<='9');return neg?-v:v;}private char read(){if(ptr==len)fill();return ptr<len?buf[ptr++]:0;}private void fill(){try{len=in.read(buf);ptr=0;}catch(IOException e){throw new RuntimeException(e.getMessage());}}private void consume(){char c;while((c=read())<=' '&&c!=0);ptr--;}}

}
```

### 결과

결과 : [맞았습니다!!](http://boj.kr/685634d92bbf4867823039601e44c658) <br>
메모리 : 221736 KB <br>
시간 : 968 ms <br>
언어 : JAVA <br>