#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;

int N, M;
int arr[5][5];
int start_idxs[5];

// calcMaxDavinci(): ��ͷ� ����ϸ鼭 ������ ���� �ִ밪�� ����ؼ� ��ȯ
long long calcMaxDavinci(int depth = 0) {
    if (depth == M) {
        long long davinci = 1;
        for (int i = 0; i < M; ++i) {
            int sum = 0;
            bool isIn[201] = { false };
            for (int j = 0; j < N; ++j) {
                int value = arr[j][(i + start_idxs[j]) % M];
                if (isIn[value + 100])
                    return -pow(100 * N, M);
                sum += value;
                isIn[value + 100] = true;
            }
            davinci *= sum;
            if (davinci == 0)
                return 0;
        }
        return davinci;
    }

    long long max_davinci = -pow(100 * N, M);
    for (int i = 0; i < M; ++i) {
        start_idxs[depth] = i;
        max_davinci = max(calcMaxDavinci(depth + 1), max_davinci);
    }
    return max_davinci;
}

// main(): �Է��� ���� ��, calcMaxDavinci()���� ����� ���� ���
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N >> M;
    for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++)
            cin >> arr[i][j];

    cout << calcMaxDavinci() << '\n';
}