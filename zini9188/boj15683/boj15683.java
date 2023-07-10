import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static ArrayList<Cctv> cctvInfo;
    static int[][] office;
    static int answer;
    static final int[][][] cctvDirection = {
            {},
            {{0}, {1}, {2}, {3}},
            {{0, 1}, {2, 3}},
            {{0, 3}, {0, 2}, {1, 2}, {1, 3}},
            {{0, 1, 3}, {0, 2, 3}, {0, 1, 2}, {1, 2, 3}},
            {{0, 1, 2, 3}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        office = new int[N][M];
        cctvInfo = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (office[i][j] >= 1 && office[i][j] <= 5) {
                    cctvInfo.add(new Cctv(i, j, office[i][j], 0));
                }
            }
        }

        answer = Integer.MAX_VALUE;
        findDirCase(0, cctvInfo.size());

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void findDirCase(int index, int size) {
        if (index == size) {
            answer = Math.min(getBlindSpot(), answer);
            return;
        }

        Cctv cctv = cctvInfo.get(index);
        for (int i = 0; i < cctvDirection[cctv.number].length; i++) {
            cctv.dir = i;
            findDirCase(index + 1, size);
        }
    }

    private static int getBlindSpot() {
        boolean[][] visited = new boolean[N][M];
        for (Cctv cctv : cctvInfo) {
            for (int dir : cctvDirection[cctv.number][cctv.dir]) {
                visited[cctv.x][cctv.y] = true;
                int nx = cctv.x + dx[dir];
                int ny = cctv.y + dy[dir];
                while (isInRange(nx, ny) && office[nx][ny] != 6) {
                    visited[nx][ny] = true;
                    nx += dx[dir];
                    ny += dy[dir];
                }
            }
        }

        int size = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (visited[x][y] || office[x][y] == 6) {
                    size++;
                }
            }
        }

        return N * M - size;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    static class Cctv {
        int x, y, number, dir;

        public Cctv(int x, int y, int number, int dir) {
            this.x = x;
            this.y = y;
            this.number = number;
            this.dir = dir;
        }
    }
}