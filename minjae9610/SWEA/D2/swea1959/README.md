# [[D2] 두 개의 숫자열 - 1959](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpoFaAS4DFAUq)

## 카테고리

배열

## 시간복잡도

시간복잡도는 O(N + M + NM)이다.

## 풀이

배열을 입력받는다.
O(N + M)

```cpp
int N, M, result = INT_MIN;
cin >> N >> M;

int* A = new int[N];
int* B = new int[M];

for (int i = 0; i < N; ++i)
    cin >> A[i];
for (int i = 0; i < M; ++i)
    cin >> B[i];
```

두 배열 중 큰 배열을 A, N으로 설정한다.
O(1)

```cpp
if (M > N) {
    swap(M, N);
    swap(A, B);
}
```

두 배열의 내적을 계산한다.
O((N - M + 1) * M) => O(NM)

```cpp
for (int offset = 0; offset <= N - M; ++offset) {
    int sum = 0;
    for (int i = 0; i < M; i++)
        sum += A[i + offset] * B[i];
    result = max(result, sum);
}
```

동적할당 된 배열을 할당 해제한다.
O(1)

```cpp
delete[] A;
delete[] B;
```

결과를 출력한다.
O(1)

```cpp
cout << "#" << test_case << " " << result << '\n';
```

## 성능 요약

메모리: 13,540 KB, 시간: 5 ms, 코드길이: 910 Bytes
