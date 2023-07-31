# [BoJ 21736 헌내기는 친구가 필요해](https://www.acmicpc.net/problem/21736)

## 카테고리

BFS

## 시간복잡도

O(n^2)

## 해설

![image](https://github.com/SSAFY-10th/algorithm/assets/76154390/02285bff-a4d9-4100-bf69-34eec5eef796)


2차원 좌표평면에서 사방으로 이동하며, 목표(P)에 접근하게 될 경우 count를 +1 해주는 문제이다.



**사용 변수**

```java
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char[][] matrix;  // 주어진 매트릭스
    static int n,m;          // row와 column의 한계값
    static int r,c;          // 시작 row, column 값
    static int[][] deltas = {  // 사방 탐색용 2차원 배열
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
    };

    static int count;   // 도연이가 만날 수 있는 사람 수
    static final char VISITED = '1'; // visited 배열 대체용 값
    static final char BLOCK = 'X';   // 도연이가 갈 수 없는 벽
    static final char PEOPLE = 'P';  // 도연이 친구^^
```



먼저 주어진 n과 m를 입력받고, matrix를 그려줍니다. 그리고 시작 지점의 좌표('I'인 부분)를 획득합니다.
```java
    private static void setVariables() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        matrix = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j<m; j++){
                char col = line.charAt(j);
                    matrix[i][j] = col;
                    if(col == 'I'){
                        r = i;
                        c = j;
                    }
            }
        }
    }
```

아주 간단한 bfs 코드입니다. 사방 탐색의 결과로 접근할 수 있는 지역(범위를 넘지 않고(isIn == true), 벽이 아니고(!= BLOCK) ,
방문한 적이 없음(!= visited))을 Queue에 넣어서 BFS를 구현했습니다. 

```java
    private static void bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r,c});
        matrix[r][c] = VISITED;

        while(!q.isEmpty()){
            int[] poll = q.poll();
            for(int[] del : deltas){
                int nr = poll[0] + del[0];
                int nc = poll[1] + del[1];

                if(isIn(nr,nc) && matrix[nr][nc] != BLOCK&& matrix[nr][nc] != VISITED ){
                    if(matrix[nr][nc]==PEOPLE)
                        count++;
                    matrix[nr][nc] = VISITED;
                    q.add(new int[]{nr,nc});
                }
            }
        }
    }

    private static boolean isIn(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }
```


## 성능 요약

메모리: 34820 KB, 시간: 328 ms

결과 : [맞았습니다!!](http://boj.kr/29639e6b3a0d425589d98cd8ab8e4da4)
