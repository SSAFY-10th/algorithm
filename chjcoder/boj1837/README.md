# [BoJ 1837]

solved.ac Bronze 3

## 카테고리

부르트포스

## 시간복잡도

for문 하나 돌아서 O(n)

## 풀이

1. 딕셔너리에 mbti별로 저장해서 3개 이상일 때 거리 0이 되도록함
2. 그 외는 전부 계산해 봐야 하므로 조합(combinations)사용 

```python
P,K = map(int,input().split())
# K 이하의 수 중에 소수가 나오면 BAD 이므로 굳이 다 돌아볼 필요 없음
for i in range(2,K):
    if P % i == 0:
        print(f'BAD {i}')
        break
else:
    print('GOOD'))
```

## 결과

결과 : [맞았습니다!!](https://www.acmicpc.net/source/62793941)
