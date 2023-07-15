# [[D2] 파리퇴치3 - 12712](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AXuARWAqDkQDFARa)

## 카테고리

브루트포스

## 시간복잡도

O(N^2 * M)

## 풀이

그리드 필드 입력을 받아 vector 컨테이너를 초기화 한다.

```cpp
int N, M, result = 0;
cin >> N >> M;
vector<vector<int>> field(N, vector<int>(N));

for (auto& row : field)
    for (int& fly : row)
        cin >> fly;
```

스프레이의 패턴을 델타 배열로 선언한다.

```cpp
int dx_plus[] = {1, -1, 0, 0};
int dy_plus[] = {0, 0, 1, -1};
int dx_x[] = {1, 1, -1, -1};
int dy_x[] = {1, -1, 1, -1};
```

필드를 순회하며 스프레이 패턴의 합을 구하고 그 중 최대값을 저장한다.

```cpp
for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
        int sum = field[i][j];
        for (int k = 0; k < 4; k++) {
            for (int l = 1; l < M; l++) {
                int x = i + dx_plus[k] * l;
                int y = j + dy_plus[k] * l;
                if (x < 0 or N <= x or y < 0 or N <= y)
                    break;
                sum += field[x][y];
            }
        }
        result = max(result, sum);
        sum = field[i][j];
        for (int k = 0; k < 4; k++) {
            for (int l = 1; l < M; l++) {
                int x = i + dx_x[k] * l;
                int y = j + dy_x[k] * l;
                if (x < 0 or N <= x or y < 0 or N <= y)
                    break;
                sum += field[x][y];
            }
        }
        result = max(result, sum);
    }
}
```

결과를 출력한다.

```cpp
cout << '#' << test_case << ' ' << result << '\n';
```

## 성능 요약

메모리: 13,548 KB, 시간: 6 ms, 코드길이: 1,708 Bytes