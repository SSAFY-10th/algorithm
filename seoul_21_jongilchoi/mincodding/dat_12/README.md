# [세수의 합]

## 카테고리

DAT

## 풀이

카운터로 사용할 변수 count를 선언해준다.
```
int count = 0;
    sort(arr.begin(), arr.end()); // 배열을 오름차순으로 정렬
```


투포인터를 이용하여 원하는 값 K가 나왔을경우 변수 count에 1씩 추가한다.

```cpp
for (int i = 0; i < N - 2; i++) {
        int left = i + 1;
        int right = N - 1;

        while (left < right) {
            int sum = arr[i] + arr[left] + arr[right];
            if (sum == K) {
                count++;
                left++; // 다음 가능한 조합 탐색
                right--;
            }
            else if (sum < K) {
                left++; // 더 큰 값을 만들기 위해 왼쪽 포인터 이동
            }
            else {
                right--; // 더 작은 값을 만들기 위해 오른쪽 포인터 이동
            }
        }
    }
```


메인 함수에서 count 를 출력한다.

```
int result = countCombinations(arr, K);
    cout  << result << endl;
```