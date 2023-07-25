# [BoJ 20529 부르트포스](https://www.acmicpc.net/problem/20529)

solved.ac Gold 5

## 카테고리

부르트포스, 백트래킹

## 시간복잡도

백트래킹을 사용하여 O(N!)

## 풀이

1. 모든 경우의 수를 아스키코드 순서에 맞게 따져 보아야 한다.
2. eval()이라는 파이썬 내장함수를 사용하면 문자열을 숫자로 직접 바꾸지 않고도 그 값을 계산할 수 있다.

```python
# 2. 0만드는 백트래킹 함수 구현
def make_zero(depth,seq):
    if depth == n:
        sequence = seq.replace(' ','')
        if eval(sequence) == 0:
            print(seq)
        return

    make_zero(depth+1,seq+' '+str(depth+1))
    make_zero(depth+1,seq+'+'+str(depth+1))
    make_zero(depth+1,seq+'-'+str(depth+1))

# 1. 입력받기
t = int(input())    # test case

for _ in range(t):  
    n = int(input())   
    make_zero(1,'1')
    print('')
```

## 결과

결과 : [맞았습니다!!](https://www.acmicpc.net/source/64036611)
