# [BoJ 2014 소수의 곱](https://www.acmicpc.net/problem/2014)

## 카테고리

수학, 자료 구조, 정수론, 우선순위 큐

## 시간복잡도

O(n * k)

## 해설

입력받은 `k`개의 소수와 그 소수가 가르키는 인덱스를 저장하는 `prime_numbers` 배열을 선언한다.

```cpp

```cpp
int k, n;
cin >> k >> n;

pair<int, int> prime_numbers[100];
for (int i = 0; i < k; ++i) {
    cin >> prime_numbers[i].first;
    prime_numbers[i].second = 0;
}
```

소수의 곱(`ugly number`)을 저장하는 배열을 선언한다.
소수를 곱해가기 시작할 숫자가 필요하기 때문에 1을 하나 넣어준다.

```cpp
int ugly_numbers[100001] = { 1 };
```

각 소수별 포인터를 가르키는 `prime_numbers` 배열을 이용해 ugly number를 구하고, 그 중 최소값을 `ugly_numbers` 배열에 넣는다.
그 후 해당 `ugly number`를 만들 수 있는 소수들의 포인터를 증가시킨다.

```cpp
for (int i = 1; i < n + 1; ++i)
{
    long long min_number = numeric_limits<long long>::max();
    int min_index[100];
    int min_count = 0;
    for (int j = 0; j < k; ++j)
    {
        int next_number = prime_numbers[j].first * ugly_numbers[prime_numbers[j].second];
        if (next_number < min_number)
        {
            min_number = prime_numbers[j].first * ugly_numbers[prime_numbers[j].second];
            min_index[0] = j;
            min_count = 1;
        }
        else if (next_number == min_number)
        {
            min_index[min_count] = j;
            ++min_count;
        }
    }
    ugly_numbers[i] = min_number;
    for (int j = 0; j < min_count; ++j)
        ++prime_numbers[min_index[j]].second;
}
```

## 성능 요약

메모리: 2292 KB, 시간: 16 ms

결과 : [맞았습니다!!](http://boj.kr/ce23d5163b5948499f4ff2d1031287b9)