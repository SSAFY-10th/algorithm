# [BoJ 2014 �Ҽ��� ��](https://www.acmicpc.net/problem/2014)

## ī�װ�

����, �ڷ� ����, ������, �켱���� ť

## �ð����⵵

O(n * k)

## �ؼ�

�Է¹��� `k`���� �Ҽ��� �� �Ҽ��� ����Ű�� �ε����� �����ϴ� `prime_numbers` �迭�� �����Ѵ�.

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

�Ҽ��� ��(`ugly number`)�� �����ϴ� �迭�� �����Ѵ�.
�Ҽ��� ���ذ��� ������ ���ڰ� �ʿ��ϱ� ������ 1�� �ϳ� �־��ش�.

```cpp
int ugly_numbers[100001] = { 1 };
```

�� �Ҽ��� �����͸� ����Ű�� `prime_numbers` �迭�� �̿��� ugly number�� ���ϰ�, �� �� �ּҰ��� `ugly_numbers` �迭�� �ִ´�.
�� �� �ش� `ugly number`�� ���� �� �ִ� �Ҽ����� �����͸� ������Ų��.

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

## ���� ���

�޸�: 2292 KB, �ð�: 16 ms

��� : [�¾ҽ��ϴ�!!](http://boj.kr/ce23d5163b5948499f4ff2d1031287b9)