# [백준 3015 오아시스 재결합](https://www.acmicpc.net/problem/3015)

solved.ac Platinum V

## 카테고리

자료 구조, 스택

## 전체 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baekjoon_3015 {

    static class Man {
        int duplicate;
        int height;

        Man(int duplicate, int height) {
            this.duplicate = duplicate;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Man> stack = new Stack<>();
        long result = 0;
        for(int i = 0;i < N; i++) {

            int height = Integer.parseInt(br.readLine());
            int duplicate = 1;
            while(!stack.empty() && stack.peek().height <= height) {
                Man man = stack.pop();
                result += man.duplicate;

                if(man.height == height) {
                    duplicate += man.duplicate;
                }
            }

            if(!stack.isEmpty())
                result++;

            stack.push(new Man(duplicate, height));
        }

        System.out.println(result);
    }
}

```

## 시간복잡도

코드로는 시간복잡도 계산이 모호한 부분이 있어 논리적으로 시간복잡도를 계산했다. N명의 사람은 스택에 한번씩 push 되어서 최악의 경우 스택이 비워질 때까지 pop 하므로 작업의 총량은 2N이다.

즉, 시간복잡도는 **O(N)이다**.

## 풀이

**⏱️소요시간 60분**<br>

N의 최대가 500,000이므로 O(N^2) 알고리즘은 무조건 시간초과가 터진다. 이 문제의 핵심은 필요한 성질을 논리적으로 파악하고 이를 스택 자료구조를 이용하여 최적의 계산을 통해 결과값으로 찾아내는 것이다.

문제는 대표적인 스택 자료구조 활용 문제인 [탑](https://www.acmicpc.net/problem/2493)과 비슷하다. <br> 두 사람 사이에 서로의 키보다 큰 사람이 없다면 두 사람은 서로를 바라 볼 수 있다. 모든 사람들의 키가 주어졌을 대, 서로를 볼 수 있는 쌍의 수를 구하면 된다. '탑' 문제를 기억하는 사람은 금방 이 문제가 스택을 활용한 문제라는 것을 알 수 있다.

문제를 푸는 원칙은 다음과 같다.

줄의 왼쪽부터 사람의 키를 확인하면서 스택에 집어 넣을 것이다. 

⭐⭐이 때, 본인의 왼쪽만을 바라보며 결과를 저장한다!!!⭐⭐

|1|2|3|4|5|
|:------:|:---:|:---:|:---:|:---:|
|1|2|7|4|9|

1번 사람부터 키를 확인하면 1이 왼쪽에서 볼 수 있는 사람은 아무도 없다.<br>
2번 사람의 키를 확인했을 때 본인의 왼쪽에서 볼 수 있는 사람은 1뿐이다.<br>
3번 사람의 키를 확인했을 때 본인의 왼쪽에서 볼 수 있는 사람은 2뿐이다.<br>
4번 사람의 키를 확인했을 때 본인의 왼쪽에서 볼 수 있는 사람은 3뿐이다.<br>
5번 사람의 키를 확인했을 때 본인의 왼쪽에서 볼 수 있는 사람은 3, 4뿐이다.<br>

만약 스택의 top에 있는 사람이 본인의 키보다 작거나 같다면 스택의 top을 pop()한다. 이 동작을 스택의 top이 본인보다 키가 클 때까지 혹은 스택이 빌 때까지 반복한다. pop을 수행하면 볼 수 있는 사람 쌍을 1씩 증가시킨다. 

본인을 stack에 추가하기 전에 만약 stack이 비어있지 않다면 stack의 탑과 바로 마주볼 수 있는 상황을 포함하기 위하여 사람 쌍을 1 증가시킨다. 그 후 본인을 stack에 push한다.

```java
while(!stack.empty() && stack.peek() <= height) {
    stack.pop();
    result++;
}

if(!stack.isEmpty())
    result++;

stack.push(height);
```

여기까지의 흐름은 탑과 거의 유사하게 흘러왔지만 '서로의 키보다 큰 사람이 없다면' 이라는 조건이 말썽이다. 키가 같은 사람이 생길 상황을 예외적인 케이스로 고려해주어야 한다.

|1|2|3|4|5|6|7|
|:------:|:---:|:---:|:---:|:---:|:---:|:---:|
|1|2|7|<span style="color:red">4</span>|<span style="color:red">4</span>|<span style="color:red">4</span>|9|

위 상황에서 예외적인 케이스만 살펴보자.

4번 사람은 이전과 동일하게 3번만 볼 수 있다.<br>
5번 사람은 3, 4번을 볼 수 있다.<br>
6번 사람은 3, 4, 5번을 볼 수 있다.<br>
7번 사람은 3, 4, 5, 6번을 볼 수 있다.

즉, 키가 같은 사람이 몇번 나오는지 중복 갯수 체크가 함께 이루어져야 한다.
이를 위해 새로운 사람 객체를 만들었다.
```java
static class Man {
    int duplicate;
    int height;

    Man(int duplicate, int height) {
        this.duplicate = duplicate;
        this.height = height;
    }
}
```
위 객체의 duplicate를 통해서 같은 키 중복이 몇번 연속적으로 일어났는지 확인하는 코드는 다음과 같다.

```java
int height = Integer.parseInt(br.readLine());
int duplicate = 1;
while(!stack.empty() && stack.peek().height <= height) {
    Man man = stack.pop();
    result += man.duplicate;

    if(man.height == height) {
        duplicate += man.duplicate;
    }
}

if(!stack.isEmpty())
    result++;

stack.push(new Man(duplicate, height));
```

## 결과

결과 : [맞았습니다!!](https://www.acmicpc.net/source/64137478)<br>
메모리 : 59576 KB<br>
시간 : 428 ms<br>
언어 : JAVA<br>
코드길이 : 1046 B<br>
