# [BoJ 10986 나머지합](https://www.acmicpc.net/problem/10986)

## 카테고리

누적합, 수학

## 시간복잡도

O(n)

## 해설

### 문제조건
![image](https://ssafy-enjoytrip-kkh.s3.ap-northeast-2.amazonaws.com/algo/%EB%82%98%EB%A8%B8%EC%A7%80%ED%95%A9_%EB%AC%B8%EC%A0%9C_%EC%A1%B0%EA%B1%B4.png)


문제 조건을 읽고 생각한 것은 다음과 같다.

1. '연속된 부분 구간의 합' -> 누적합과 관련된 문제구나.
2. '시간제한 1초' -> 좀 빡세게 구하는구나
3. '1<=N<=10^6' N이 높은 것을 보니 N log N에도 터질지도 모르겠구나.
4. '0 <= A <= 10^9' 누적합을 하다가 overflow가 날지도 모르겠다.



### 실패 풀이

첫번째로 풀이를 한 것은, 누적 합을 구하고, 그 안에서 r이 2인 중복 조합으로 풀어보았습니다.
제한 조건을 고려하지 않고 풀어서 당연하게도 시간초과가 발생했습니다.
10^6승에서 N^2을 하면 당연히 터진다는 생각을 하지 않고 접근을 했었습니다.



### 정답 풀이

누적 합을 구한 배열에서 서로 뺐을 때, M의 배수가 될 수 있는 규칙을 찾았습니다.

그 규칙은 누적합을 구한 구간들이 서로 M에 대한 나머지 연산의 결과가 같은 구간에서 뺀 경우에 M에 나누어 떨어지는 수가 나온다는 것 입니다.

아래 이미지를 보면 이해하기 쉽습니다.



처음에 주어진 입력값을 통해 누적합을 만들고, **M(3)에 대한 나머지 연산의 값이 같은 경우인 (1,7)과 (3,6,9)에 있는 누적합의 경우 서로 뺀다면, 
M의 배수의 형태가 나오는 것을 확인할 수 있습니다.**
(누적합7은 1+2+3+1이기 때문에, 누적합 1을 뺄 경우 6이 된다.)

![image](https://ssafy-enjoytrip-kkh.s3.ap-northeast-2.amazonaws.com/algo/%EB%82%98%EB%A8%B8%EC%A7%80%ED%95%A9_%EC%9D%B4%EB%AF%B8%EC%A7%802.png)


위의 규칙을 활용하면, M으로 나누어 떨어지는 개수를 구하기 위해선, 각 나머지 연산의 결과에 대한 개수를 체크한다면 전부 끝났습니다.

**사용 변수**
```java
    // 입출력을 위한 클래스
    BufferedReader br;
    StringTokenizer st;
    
    int n, int m; // 주어진 배열의 크기 n, 나누어 떨어져야 하는 수 m
    long[]arr; // 누적합을 기록할 배열
    int[]mods; //   나머지 연산 결과에 대한 카운팅 배열
    long result; // 결과계산
```

**누적합 배열 생성과 나머지 연산 결과 카운팅**

누적합 배열을 주어진 입력 크기인 n보다 1개 더 크게 만들고, 이전 인덱스(i-1)의 누적합과 새로 들어온 입력값을 더하는 것으로 계산했습니다.
그리고 그 누적값을 m과 나머지 연산하고 카운팅을 올려주었습니다.

```java
        for (int i = 1; i <= n; i++) {
            arr[i] = (arr[i - 1] %m + Long.parseLong(st.nextToken())% m)% m; 
            int num = (int)(arr[i] % m);
            mods[num]++;
        }
```

**M으로 나누어 떨어지는 개수 세기**

앞선 설명으로 나머지 연산의 결과가 같은 애들끼리 뺀다면 M에 나누어 떨어진다는 것을 알 수 있었습니다. 빼기 위한 조합을 구하기 위해 nC2를 해서 그 값을 결과변수에 더해주었습니다.
그리고 이미 M으로 나누어 떨어지는 구간의 경우(mod == 0) 그 자체로 이미 답이 될 수 있기 때문에, 그 개수만큼 정답변수에 더해주었습니다.

```java
        long result = 0;
        for (int mod : mods)
            result += nCTwo(mod);
        result += mods[0];
        System.out.println(result);

    private static long nCTwo(long n) {
        return (n * (n-1))/2;
    }
```

### 결과

결과 : [맞았습니다!!](http://boj.kr/3860002f5b4e4cef87c6479a2f44296f) <br>
메모리 : 167856 KB <br>
시간 : 480 ms <br>
언어 : JAVA <br>
코드길이 : 1278 B