#include <iostream>

using namespace std;

bool goodString(int N, string str = "") {
	for (int i = 1; i <= str.size() / 2; i++)
		if (str.substr(str.size() - i, i) == str.substr(str.size() - 2 * i, i))
			return false;
	if (str.size() == N) {
		cout << str << '\n';
		return true;
	}

	for (int i = 0; i < 3; i++)
		if (goodString(N, str + char('1' + i)))
			return true;
	return false;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

	int N;
    cin >> N;
    goodString(N);
}