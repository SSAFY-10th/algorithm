from sys import stdin

INFINITY = 999_999_999
test_case = 0

answer = []

while True:
    n = int(stdin.readline())
    if n == 0:
        break
    test_case += 1

    matrix = [list(map(int, stdin.readline().split())) for _ in range(n)]
    record_matrix = [[INFINITY for _ in range(3)] for _ in range(n)]

    record_matrix[0][1] = matrix[0][1]
    record_matrix[0][2] = record_matrix[0][1] + matrix[0][2]

    for y in range(1, n - 1):
        for x in range(0, 3):
            min_list = []
            for c in range(-1, 2):
                next_x = x + c
                if 0 <= next_x <= 2:
                    min_list.append(record_matrix[y - 1][next_x])
            record_matrix[y][x] = matrix[y][x] + min(min_list)

    record_matrix[n - 1][0] = min(record_matrix[n - 2][0], record_matrix[n - 2][1]) + matrix[n - 1][0]
    record_matrix[n - 1][1] = min(record_matrix[n - 2]) + matrix[n - 1][1]
    record_matrix[n - 1][1] = min(record_matrix[n - 1][1], record_matrix[n - 1][0] + matrix[n - 1][1])

    answer.append(record_matrix[n - 1][1])

for i in range(len(answer)):
    print("{0}. {1}".format(i + 1, answer[i]))
