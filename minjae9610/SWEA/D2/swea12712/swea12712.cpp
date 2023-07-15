#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int T;
    cin >> T;
    for (int test_case = 1; test_case <= T; ++test_case)
    {
        int N, M, result = 0;
        cin >> N >> M;
        vector<vector<int>> field(N, vector<int>(N));

        for (auto& row : field)
            for (int& fly : row)
                cin >> fly;

        int dx_plus[] = {1, -1, 0, 0};
        int dy_plus[] = {0, 0, 1, -1};
        int dx_x[] = {1, 1, -1, -1};
        int dy_x[] = {1, -1, 1, -1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = field[i][j];
                for (int k = 0; k < 4; k++) {
                    for (int l = 1; l < M; l++) {
                        int x = i + dx_plus[k] * l;
                        int y = j + dy_plus[k] * l;
                        if (x < 0 or N <= x or y < 0 or N <= y)
                            break;
                        sum += field[x][y];
                    }
                }
                result = max(result, sum);
                sum = field[i][j];
                for (int k = 0; k < 4; k++) {
                    for (int l = 1; l < M; l++) {
                        int x = i + dx_x[k] * l;
                        int y = j + dy_x[k] * l;
                        if (x < 0 or N <= x or y < 0 or N <= y)
                            break;
                        sum += field[x][y];
                    }
                }
                result = max(result, sum);
            }
        }

        cout << '#' << test_case << ' ' << result << '\n';
    }

    return 0;
}