#include <iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    string target, bridge[2];
    cin >> target >> bridge[0] >> bridge[1];

    int dp[2][20][100] = {0,};

    for (int i = 0; i < 2; ++i) {
        for (int j = 0; j < target.size(); ++j) {
            int devil_or_angel = (i + j) % 2;
            if (j == 0)
    			dp[i][0][0] = (target[0] == bridge[devil_or_angel][0]);
            for (int k = 1; k < int(bridge[i].size() - target.size()) + j + 1; ++k) {
                if (target[j] == bridge[devil_or_angel][k]) {
                    if (j == 0)
						dp[i][j][k] = dp[i][j][k - 1] + 1;
                    else
                        dp[i][j][k] = dp[i][j][k - 1] + dp[i][j - 1][k - 1];
				}
                else {
                    dp[i][j][k] = dp[i][j][k - 1];
                }
			}
		}
	}

    cout << dp[0][target.size() - 1][bridge[0].size() - 1] + dp[1][target.size() - 1][bridge[1].size() - 1] << '\n';

    return 0;
}