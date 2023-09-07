# 1431 시리얼 번호


n = int(input())
serial = [input() for _ in range(n)]

from functools import cmp_to_key

def compare(a,b):
    len_a = len(a)
    len_b = len(b)
    num = ['0','1','2','3','4','5','6','7','8','9']
    # 첫번째 조건
    if len_a > len_b:   # a가 큰데 자리를 바꾸면 큰 값이 뒤로 오게 정렬됨
        return 1
    elif len_a < len_b:
        return -1

    # 두번째 조건
    cnt_a = 0
    cnt_b = 0
    for i in range(len_a):
        if a[i] in num:
            cnt_a += int(a[i])
        if b[i] in num:
            cnt_b += int(b[i])
    if cnt_a > cnt_b:
        return 1
    elif cnt_a < cnt_b:
        return -1
    # 세번째 조건
    if a > b:
        return 1
    else:
        return -1


serial = sorted(serial, key = cmp_to_key(compare))

for temp in serial:
    print(temp)
