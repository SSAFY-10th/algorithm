#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int countCombinations(vector<int>& arr, int K) {
    int count = 0;
    sort(arr.begin(), arr.end()); // 배열을 오름차순으로 정렬

    int N = arr.size();
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

    return count;
}

int main() {
    int N, K;
    
    cin >> N >> K;

    vector<int> arr(N);
    
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    int result = countCombinations(arr, K);
    cout  << result << endl;

    return 0;
}