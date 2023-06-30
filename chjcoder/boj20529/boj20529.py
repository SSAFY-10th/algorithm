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