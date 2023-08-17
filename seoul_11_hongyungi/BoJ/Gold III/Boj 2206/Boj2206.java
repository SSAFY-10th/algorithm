import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2206 {

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
                        visit[nextY][nextX] = now.smash + 1;
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