# [알고리즘 탑] Backtracking의 탑 9번 - 좋은수열

## 카테고리

Backtracking, 재귀

## 시간복잡도

O(3^N)

## 해설

입력받은 문자의 뒤에 1~3을 붙여가며 재귀를 돈다.

```cpp
for (int i = 0; i < 3; i++)
	if (goodString(N, str + char('1' + i)))
		return true;
```

새로 붙은 문자열이 좋은 문자열인지 확인한다.

```cpp
for (int i = 1; i <= str.size() / 2; i++)
	if (str.substr(str.size() - i, i) == str.substr(str.size() - 2 * i, i))
		return false;
```

문자열이 목표 길이에 도달했다면, 문자열을 출력하고 true를 반환해 재귀를 종료한다.

```cpp
if (str.size() == N) {
	cout << str << '\n';
	return true;
}
```

## 성능 요약

메모리: 2 MB, 시간: 1 ms