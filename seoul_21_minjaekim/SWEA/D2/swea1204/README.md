# [[D2] [S/W 문제해결 기본] 1일차 - 최빈수 구하기 - 1204](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV13zo1KAAACFAYh)

## 카테고리

최빈값, DP

## 시간복잡도

학생의 수와 점수의 범위가 상수이기 때문에 시간복잡도는 O(1)이다.

## 풀이

메모이제이션을 이용해 입력받은 값을 인덱스로 배열 요소에 빈도를 카운트한다.
역대 최고 빈도보다 높은 빈도로 등장한 점수가 있으면 해당 점수를 저장한다.

```cpp
int tc_num, max_score = 0, result;
cin >> tc_num;

int scores[101] = { 0 };

for (int i = 0; i < 1000; i++) {
    int score;
    cin >> score;
    scores[score]++;
    if (scores[score] >= max_score) {
        max_score = scores[score];
        result = score;
    }
}
```

결과를 출력한다.

```cpp
cout << "#" << test_case << " " << result << '\n';
```

## 성능 요약

메모리: 13,536 KB, 시간: 6 ms, 코드길이: 676 Bytes
