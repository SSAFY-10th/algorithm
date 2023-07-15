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
        bool row_check[9][9] = { false };
        bool col_check[9][9] = { false };
        bool square_check[9][9] = { false };
        bool result = true;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int slot;
                cin >> slot;
                if (!result)
                    continue;
                int square = (row / 3) * 3 + col / 3;
                if (!row_check[row][slot - 1] and !col_check[col][slot - 1] and !square_check[square][slot - 1])
                    row_check[row][slot - 1] = col_check[col][slot - 1] = square_check[square][slot - 1] = true;
                else
                    result = false;
            }
        }
        cout << '#' << test_case << ' ' << result << '\n';
    }

    return 0;
}