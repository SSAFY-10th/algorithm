#include <iostream>
#include <climits>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int T;
    cin >> T;
    for (int test_case = 1; test_case <= T; ++test_case)
    {
        int N, M, result = INT_MIN;
        cin >> N >> M;

        int* A = new int[N];
        int* B = new int[M];

        for (int i = 0; i < N; ++i)
            cin >> A[i];
        for (int i = 0; i < M; ++i)
            cin >> B[i];

        if (M > N) {
            swap(M, N);
            swap(A, B);
        }

        for (int offset = 0; offset <= N - M; ++offset) {
            int sum = 0;
            for (int i = 0; i < M; i++)
                sum += A[i + offset] * B[i];
            result = max(result, sum);
        }

        delete[] A;
        delete[] B;

        cout << "#" << test_case << " " << result << '\n';
    }

    return 0;
}
