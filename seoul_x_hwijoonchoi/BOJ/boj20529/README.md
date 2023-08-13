# [BoJ 20529 부르트포스](https://www.acmicpc.net/problem/20529)

solved.ac Silver 1

## 카테고리

부르트포스

## 시간복잡도

같은 MBTI가 2개 이하일 때, 2중 for문을 돌아야 하므로 시간 복잡도는 O(N^2)이다.

## 풀이

1. 딕셔너리에 mbti별로 저장해서 3개 이상일 때 거리 0이 되도록함
2. 그 외는 전부 계산해 봐야 하므로 조합(combinations)사용 

```python
from itertools import combinations
def mbti_dis(lst):
    dis = 8
    dic = {}
    for mbti in lst:   # 딕셔너리에 저장
        if mbti not in dic.keys():
            dic[mbti] = 1
        else:
            dic[mbti] += 1
            if dic[mbti] == 3: # 같은 MBTI가 3개가 되면 거리 0
                dis = 0
                return dis
    # 같은 MBTI 2개 이하
    cnt = 0
    for mbti in combinations(lst,3): #[(1,2,3),(3,4,5)]
        cnt = 0
        for mbti_xy in combinations(mbti,2):
            x,y = mbti_xy
            for i in range(4):
                if x[i] != y[i]:
                    cnt += 1
        if cnt < dis:
            dis = cnt
    return dis

T  = int(input())
for _ in range(T):
    N = int(input())
    lst = list(input().split())
    print(mbti_dis(lst))
```

## 결과

결과 : [맞았습니다!!](https://www.acmicpc.net/source/62701318)
