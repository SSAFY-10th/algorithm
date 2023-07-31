# [백준 13335 트럭](https://www.acmicpc.net/problem/13335)

solved.ac Silver I

## 카테고리

구현, 자료구조, 시뮬레이션, 큐

## 시간복잡도

모든 트럭이 다리를 건널 동안 반복문이 돌아가므로 최악의 경우는 트럭 하나씩 다리를 건너는 경우이다. 
반복문 안에서의 로직은 모두 연결리스트(큐)를 활용한 O(1)연산으로 구성되어 있다.
따라서 시간복잡도는 O(n X w)이다. 

이때 n의 최대값은 1,000이고, w의 최댓값은 100이므로 최악의 경우 100,000번의 연산이다. 시간제한 1초 안에는 거뜬하다.

## 풀이

코테 문제를 읽으면 특정 자료구조를 쓰라고 등떠미는 표현이 등장하는 난이도가 쉬운 문제들이 있다. 예를 들어 이 문제의 경우,

**하나의 차선**으로 된 다리가 하나 있다. 이 다리를 n 개의 트럭이 건너가려고 한다. **트럭의 순서는 바꿀 수 없으며**<br>
**순서대로 다리를 오른쪽에서 왼쪽으로** 건넌다고 하자.

물구나무 서서 봐도 이 문제는 다리를 <span style="color:red">Queue</span>로 구현하는 문제이다(순서, FIFO). 다만 한가지 신경써야하는 점이 있다면 다리 위에 올라간 트럭의 하중을 기억하고 있어야 한다는 점이다.

또한 단위 시간을 기준으로 반복문을 수행하며 다리의 길이 w만큼 큐의 사이즈를 유지하기 위하여 무게가 0인 mockTruck을 생성하여 Queue에 넣어주었다.

```java
// 다리 초기화
Queue<Integer> bridge = new LinkedList<>();
int time = 0, totalWeight = 0;
for (int i = 0; i < w; i++) {
    bridge.add(0);  //mockTruck으로 채우기
}

while (!bridge.isEmpty()) {
    time++;
    totalWeight -= bridge.poll();
    if(!trucks.isEmpty()) {
        if (totalWeight + trucks.peek() > L) {
            bridge.add(0);  // 다리 길이 유지를 위해 mock 넣어주기
        } else {
            bridge.add(trucks.peek());
            totalWeight += trucks.poll();
        }
    }
}
```

## 결과

결과 : [맞았습니다!!](https://www.acmicpc.net/source/63908629)
