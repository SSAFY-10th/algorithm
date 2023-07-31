# [알고리즘 탑] DAT의 탑 12번 - 세 수의 합

## 카테고리

DAT

## 시간복잡도

2중 반복문을 이용해 두 수의 합이 가능한 조합을 구하기 때문에, 시간복잡도는 `O(N^2)`이다.

## 풀이

입력받은 값이 존재하는지 O(1)으로 확인하기 위해 `isIn` 배열을 만든다.

```cpp
int arr[5000];
bool isIn[100001] = { false };
for (int i = 0; i < N; ++i) {
    cin >> arr[i];
    isIn[arr[i]] = true;
}
```

`arr`에서 두 수의 합의 경우까지는 브루트 포스로 구한다.
두 수가 정해지면 마지막 수는 계산을 할 수 있으므로`need_num`을 구하고 `isIn` 배열을 이용해 만들 수 있는지 확인한다.

```cpp
int cnt = 0;
for (int i = 0; i < N; ++i) {
    if (arr[i] > K) continue;
    for (int j = i + 1; j < N; ++j) {
        int need_num = K - arr[i] - arr[j];
        if (need_num < 0 or 100000 < need_num or need_num == arr[i] or need_num == arr[j]) continue;
        cnt += isIn[need_num];
    }
}
```

같은 조합이 순서만 다른 경우를 제외하기 위해 3으로 나눠준다.

```cpp
cout << cnt / 3 << '\n';
```

## 성능 요약

메모리: 2 MB, 시간: 33 ms
