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
        int N;
        cin >> N;

        vector<vector<int>> matrix(N, vector<int>(N));
        for (vector<int>& row : matrix)
            for (int& element : row)
                cin >> element;

        cout << '#' << test_case << '\n';
        for (int i = 0; i < N; ++i) {
            for (int j = N - 1; j >= 0; --j)
                cout << matrix[j][i];
            cout << ' ';

            for (int j = N - 1; j >= 0; --j)
                cout << matrix[N - i - 1][j];
            cout << ' ';

            for (int j = 0; j < N; ++j)
                cout << matrix[j][N - i - 1];
            cout << '\n';
        }
    }

    return 0;
}
