# [백준 6884 소수 부분 수열](https://www.acmicpc.net/problem/6884)

solved.ac Silver II

## 카테고리

수학, 브루트포스, 정수론, 누적합, 소수 판정, 에라토스테네스의 체

## 시간복잡도

단어 길이를 1씩 늘려가는 반복문에서 O(n)
검사를 시작하는 인덱스를 1씩 움직이면서 O(n)
총 O(n^2) 알고리즘이다.
수열의 길이는 최대 10000이므로 worst case의 경우 100,000,000번 연산한다(보통 1초로 추정). 테스트 케이스는 최대 20개이다.
5초안에 통과 못할 수도 있을 것이라 생각했는데 다행히 넘어갔다.

## 풀이

부분 수열에 대한 누적합을 이중 포문 안에서 하나하나 더하면 O(n ^ 3)이라는 지옥에서 온 알고리즘이 탄생한다.
따라서 이를 피하기 위해 입력 단계에서 미리 누적합을 구해놓았다.

```java
int[] sum = new int[len + 1];

for (int i = 1; i <= len; i++) {
    sum[i] += sum[i - 1] + Integer.parseInt(st.nextToken());
}
```
이렇게 되면 나중에 누적합을 부분수열의 길이와 시작하는 인덱스만으로 O(1) 연산을 통해 구할 수 있다.
```java
int temp = sum[start + curLen - 1] - sum[start - 1];    // temp는 부분 수열의 누적합
```

그 밖의 로직은 간단하므로 생략한다.

## 결과

결과 : [맞았습니다!!](https://www.acmicpc.net/source/63587932)
