# [[D2] 스도쿠 검증 - 1974](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Psz16AYEDFAUq)

## 카테고리

DP

## 시간복잡도

스도쿠 크기가 고정되어 있으므로 시간복잡도는 O(1)이다.

## 풀이

메모이제이션을 위한 중복 체크 배열을 3가지 만들어준다.

```cpp
bool row_check[9][9] = { false };
bool col_check[9][9] = { false };
bool square_check[9][9] = { false };
bool result = true;
```

스도쿠의 각 슬롯의 값을 입력 받으며, 조건 체크를 진행한다.

```cpp
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
```

결과를 출력한다.

```cpp
cout << '#' << test_case << ' ' << result << '\n';
```

## 성능 요약

메모리: 13,536 KB, 시간: 5 ms, 코드길이: 1,033 Bytes
