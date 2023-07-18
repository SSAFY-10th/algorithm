import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1986 {
    static class Pos{
        int x;
        int y;

        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static int[] knight_dx = {-1,-2,-2,-1,1,2,2,1};
    public static int[] knight_dy = {-2,-1,1,2,2,1,-1,-2};

    public static int[] queen_dx = {0,1,1,1,0,-1,-1,-1};
    public static int[] queen_dy = {1,1,0,-1,-1,-1,0,1};

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n + 1][m + 1];

        st = new StringTokenizer(br.readLine());
        int queenNum = Integer.parseInt(st.nextToken());
        Pos[] queens = new Pos[queenNum];
        for(int i = 0;i < queenNum;i++) {
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            map[y][x] = 2;

            queens[i] = new Pos(x, y);
        }

        st = new StringTokenizer(br.readLine());
        int knightNum = Integer.parseInt(st.nextToken());
        Pos[] knights = new Pos[knightNum];
        for(int i = 0;i < knightNum;i++) {
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            map[y][x] = 2;

            knights[i] = new Pos(x, y);
        }

        st = new StringTokenizer(br.readLine());
        int pawnNum = Integer.parseInt(st.nextToken());
        Pos[] pawns = new Pos[pawnNum];
        for(int i = 0;i < pawnNum;i++) {
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            map[y][x] = 2;

            pawns[i] = new Pos(x, y);
        }

        // 퀸 동작
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

		/*
		for(int[] row : map) {
			System.out.println(Arrays.toString(row));
		}
		*/

        int result = 0;
        for(int i = 1;i <= n; i++) {
            for(int j = 1; j <= m;j++) {
                if(map[i][j] == 0)
                    result++;
            }
        }
        System.out.println(result);
    }
}
