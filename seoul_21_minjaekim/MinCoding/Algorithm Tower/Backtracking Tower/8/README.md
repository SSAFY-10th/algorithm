# [알고리즘 탑] Backtracking의 탑 8번 - 다빈치 타워

## 카테고리

DAT, Backtracking, 재귀

## 시간복잡도

O(M^N * N)

## 해설

재귀로 계산하면서 나오는 값중 최대값을 계산해서 반환한다.

```cpp
long long calcMaxDavinci(int depth = 0);
```

depth는 현재 깊이를 의미하는 변수다. depth가 M과 같아졌을 때 조합의 값을 계산한다.

```cpp
if (depth == M) {
```

각 세로 열을 순회하며 수를 뽑는 과정을 수행한다.

```cpp
for (int i = 0; i < M; ++i) {
    int sum = 0;
    bool isIn[201] = { false };
    for (int j = 0; j < N; ++j) {
        int value = arr[j][(i + start_idxs[j]) % M];
```

isIn[] 배열을 이용해 수의 중복을 확인하고 중복이 발생하면 최소값인 -pow(100 * N, M) 값을 반환한다.

```cpp
if (isIn[value + 100])
    return -pow(100 * N, M);
```

각 세로 열의 합들을 곱한다. 모든 세로 열을 순회 한 뒤 davinci를 반환한다.

```cpp
davinci *= sum;
```

재귀를 돌면서 반환된 값 중 최대값을 구해서 반환한다.

```cpp
long long max_davinci = -pow(100 * N, M);
for (int i = 0; i < M; ++i) {
    start_idxs[depth] = i;
    max_davinci = max(calcMaxDavinci(depth + 1), max_davinci);
}
return max_davinci;
```

## 성능 요약

메모리: 2 MB, 시간: 2 ms