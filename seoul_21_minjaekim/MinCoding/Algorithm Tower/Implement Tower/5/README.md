# [알고리즘 탑] 구현의 탑 5번 - 오목

## 카테고리

브루트포스 알고리즘, 구현

## 시간복잡도

O(1)

## 해설

DAT를 이용하기 위해 각 방향에서 바라본 연속된 돌의 개수를 저장할 배열을 선언한다.

```cpp
int row_check[19] = { 0, };
int col_check[19] = { 0, };
int diag1_check[37] = { 0, };
int diag2_check[37] = { 0, };
```

오목판을 뒤에서부터 순회하며, 칸에 놓인 돌의 상태에 따라 DAT를 업데이트한다.

```cpp
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
```

연속된 5개의 돌이 있다면, 그 다음 칸에 놓인 돌의 상태에 따라 승리할 수 있는지 확인한다.

```cpp
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
```

가장 왼쪽, 위쪽에 놓인 돌을 출력한다.

```cpp
if (diag1_check[row + col] == 5 || diag1_check[row + col] == -5) {
    row += 4;
    col -= 4;
}
cout << row + 1 << ' ' << col + 1 << '\n';
```

## 성능 요약

메모리: 2020 KB, 시간: 0 ms