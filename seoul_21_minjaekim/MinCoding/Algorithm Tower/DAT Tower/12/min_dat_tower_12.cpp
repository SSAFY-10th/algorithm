#include <iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N, K;
    cin >> N >> K;

    int arr[5000];
    bool isIn[100001] = { false };
    for (int i = 0; i < N; ++i) {
        cin >> arr[i];
        isIn[arr[i]] = true;
    }

    int cnt = 0;
    for (int i = 0; i < N; ++i) {
        if (arr[i] > K) continue;
        for (int j = i + 1; j < N; ++j) {
            int need_num = K - arr[i] - arr[j];
            if (need_num < 0 or 100000 < need_num or need_num == arr[i] or need_num == arr[j]) continue;
            cnt += isIn[need_num];
        }
    }

    cout << cnt / 3 << '\n';

    return 0;
}
