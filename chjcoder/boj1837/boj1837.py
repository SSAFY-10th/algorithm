P,K = map(int,input().split())

for i in range(2,K):
    if P % i == 0:
        print(f'BAD {i}')
        break
else:
    print('GOOD')