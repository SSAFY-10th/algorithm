#include <iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int field[19][19];
    for (auto& row : field)
        for (int& element : row)
            cin >> element;

    int row_check[19] = { 0, };
    int col_check[19] = { 0, };
    int diag1_check[37] = { 0, };
    int diag2_check[37] = { 0, };

    for (int row = 18; row >= 0; --row) {
        for (int col = 18; col >= 0; --col) {
            if (field[row][col] == 0) {
                row_check[row] = 0;
                col_check[col] = 0;
                diag1_check[row + col] = 0;
                diag2_check[row - col + 18] = 0;
            }
            else if (field[row][col] == 1) {
				row_check[row] = (row_check[row] > 0 ? row_check[row] + 1 : 1);
				col_check[col] = (col_check[col] > 0 ? col_check[col] + 1 : 1);
				diag1_check[row + col] = (diag1_check[row + col] > 0 ? diag1_check[row + col] + 1 : 1);
				diag2_check[row - col + 18] = (diag2_check[row - col + 18] > 0 ? diag2_check[row - col + 18] + 1 : 1);
			}
            else if (field[row][col] == 2) {
				row_check[row] = (row_check[row] < 0 ? row_check[row] - 1 : -1);
				col_check[col] = (col_check[col] < 0 ? col_check[col] - 1 : -1);
				diag1_check[row + col] = (diag1_check[row + col] < 0 ? diag1_check[row + col] - 1 : -1);
				diag2_check[row - col + 18] = (diag2_check[row - col + 18] < 0 ? diag2_check[row - col + 18] - 1 : -1);
			}
            if (row_check[row] == 5 || col_check[col] == 5 || diag1_check[row + col] == 5 || diag2_check[row - col + 18] == 5) {
                if (row_check[row] == 5 && col > 0 && field[row][col - 1] == 1)
                    continue;
                if (col_check[col] == 5 && row > 0 && field[row - 1][col] == 1)
					continue;
                if (diag1_check[row + col] == 5 && row > 0 && col < 18 && field[row - 1][col + 1] == 1)
                    continue;
                if (diag2_check[row - col + 18] == 5 && row > 0 && col > 0 && field[row - 1][col - 1] == 1)
                    continue;
                cout << 1 << '\n';
            }
            else if (row_check[row] == -5 || col_check[col] == -5 || diag1_check[row + col] == -5 || diag2_check[row - col + 18] == -5) {
                if (row_check[row] == -5 && col > 0 && field[row][col - 1] == 2)
                    continue;
                if (col_check[col] == -5 && row > 0 && field[row - 1][col] == 2)
                    continue;
                if (diag1_check[row + col] == -5 && row > 0 && col < 18 && field[row - 1][col + 1] == 2)
                    continue;
                if (diag2_check[row - col + 18] == -5 && row > 0 && col > 0 && field[row - 1][col - 1] == 2)
                    continue;
                cout << 2 << '\n';
            }
            else
                continue;
            if (diag1_check[row + col] == 5 || diag1_check[row + col] == -5) {
                row += 4;
                col -= 4;
            }
            cout << row + 1 << ' ' << col + 1 << '\n';
            return 0;
		}
	}
    cout << 0 << '\n';

    return 0;
}