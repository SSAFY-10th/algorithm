#include <iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int T;
    cin >> T;
    for (int test_case = 1; test_case <= T; ++test_case)
    {
        int tc_num, max_score = 0, result;
        cin >> tc_num;

        int scores[101] = { 0 };

        for (int i = 0; i < 1000; i++) {
            int score;
            cin >> score;
            scores[score]++;
            if (scores[score] >= max_score) {
                max_score = scores[score];
                result = score;
            }
        }

        cout << "#" << test_case << " " << result << '\n';
    }

    return 0;
}
