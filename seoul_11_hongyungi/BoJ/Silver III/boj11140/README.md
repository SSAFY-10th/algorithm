# [백준 11140 LOL](https://www.acmicpc.net/problem/11140)

solved.ac Silver III

## 카테고리

문자열, 구현, case_work

## 시간복잡도

테스트 케이스를 하나씩 순회하면서 O(n)
길이가 1부터 3까지인 서브스트링으로 쪼개서 조건을 검사하면서 O(n)
총 O(n^2)으로 시간복잡도에서 유리한 알고리즘은 아니지만
테스트케이스가 최대 100개, 한 단어 당 최대 길이는 50글자이므로 충분히 1초 안에 통과 가능하다.

## 풀이

단어를 길이가 1부터 3까지인 substring 쪼개서 조건을 세운다.
조건을 *순서에 맞게* 배치한다면 모든 상황을 고려한 답을 도출할 수 있다.

조건 1. 단어에 "o"나 "l"이 있으면 문자를 2개 추가하는 것으로 lol을 만들 수 있다.
```java
if(temp.equals("o") || (temp.equals("l"))) result = 2;
```

조건 2. 단어에 "lo"나 "ol", "ll"이 있으면 문자를 1개 추가하는 것으로 lol을 만들 수 있다.
```java
if(temp.equals("lo") || temp.equals("ol") || temp.equals("ll")) result = 1;
```

조건 3. "l"과 "l" 사이에 문자가 하나 있다면 이를 제거하는 것으로 lol을 만들 수 있다.
```java
String REGEXP_PATTERN_STRING = "[a-z]*l[a-z]l[a-z]*";
if(Pattern.matches(REGEXP_PATTERN_STRING, temp)) result = 1;
```

조건 4. 단어에 "lol"이 있으면 무조건 결과는 0이다.
```java
if(temp.equals("lol")) result = 0;
```

## 결과

결과 : [맞았습니다!!](http://boj.kr/34fe82abdd904b09bbbf7943d8376185)

## 추신

40분 걸렸음. 문자열이 너무 약점이다.
정규표현식 좀 확실하게 알아야할 것 같다..
로직 구현하고 정규표현식을 사용해야 하는 예외 상황까지 생각나는데 20분정도 소요됐는데 정작 정규표현식 쓰는데 너무 헤매서 결국 서칭하고 20분이 더 걸렸다.
문자열 푸는데 정규표현식 제대로 모르면 너무 힘들다.. 따로 공부좀 하자.