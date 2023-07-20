# [[D2] 숫자 배열 회전 - 1961](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Pq-OKAVYDFAUq)

## 카테고리

배열

## 시간복잡도

입력 행렬을 읽고, 출력하려면 각 요소를 한번씩 방문해야 하므로 시간복잡도는 O(N^2)이다.

## 풀이

행렬을 입력받는다.

```cpp
int N;
cin >> N;

vector<vector<int>> matrix(N, vector<int>(N));
for (vector<int>& row : matrix)
    for (int& element : row)
        cin >> element;
```

90도, 180도, 270도 회전시킨 행렬을 순서대로 출력한다.

```cpp
cout << '#' << test_case << '\n';
for (int i = 0; i < N; ++i) {
    for (int j = N - 1; j >= 0; --j)
        cout << matrix[j][i];
    cout << ' ';

    for (int j = N - 1; j >= 0; --j)
        cout << matrix[N - i - 1][j];
    cout << ' ';

    for (int j = 0; j < N; ++j)
        cout << matrix[j][N - i - 1];
    cout << '\n';
```

## 성능 요약

메모리: 4,256 KB, 시간: 6 ms, 코드길이: 895 Bytes
