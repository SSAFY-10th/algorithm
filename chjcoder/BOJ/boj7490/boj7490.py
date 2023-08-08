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