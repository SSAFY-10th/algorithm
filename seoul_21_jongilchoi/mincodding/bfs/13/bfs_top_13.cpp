#include <iostream>
#include <string>
#include <queue>

using namespace std;

struct info
{
	int num;
	string s;
};


int d(int n) { // 2배로 만들고 숫자가 4자리를 넘어가면 10000으로 나눈 나머지 
	n = n * 2;
	if (n >= 10000)
		return n % 10000;
	else
		return n;
}

int sub(int n) {
	n -= 1;
	if (n == -1)
		return 9999;
	else
		return n;
}

int l(int n) {
	int th = n / 1000;
	n = n % 1000;
	int hundred = n / 100;
	n = n % 100;
	int ten = n / 10;
	n = n % 10;
	int one = n / 1;
	int rst = hundred * 1000 + ten * 100 + one * 10 + th;
	return rst;
}
int r(int n) {
	int th = n / 1000;
	n = n % 1000;
	int hundred = n / 100;
	n = n % 100;
	int ten = n / 10;
	n = n % 10;
	int one = n / 1;
	int rst = one * 1000 + th * 100 + hundred * 10 + ten;
	return rst;
}

string bfs(int s, int e) {
	queue<info> bfs_q;
	int dat[10000] = { 0 };
	string rst = "";
	bfs_q.push({ s,rst });
	while (!bfs_q.empty()) {
		info I = bfs_q.front(); bfs_q.pop();
		int now = I.num;
		string now_s = I.s;

		if (now == e) {
			return now_s;
		}

		int rst_d = d(now);
		if (dat[rst_d] == 0)
		{
			dat[rst_d] = 1;
			now_s += "D";
			bfs_q.push({ rst_d,now_s });
			now_s = now_s.substr(0, now_s.size() - 1);
		}
		int rst_s = sub(now);
		if (dat[rst_s] == 0) {
			dat[rst_s] = 1;
			now_s += "S";
			bfs_q.push({ rst_s,now_s });
			now_s = now_s.substr(0, now_s.size() - 1);
		}
		int rst_l = l(now);
		if (dat[rst_l] == 0) {
			dat[rst_l] = 1;
			now_s += "L";
			bfs_q.push({ rst_l,now_s });
			now_s = now_s.substr(0, now_s.size() - 1);
		}
		int rst_r = r(now);
		if (dat[rst_r] == 0) {
			dat[rst_r] = 1;
			now_s += "R";
			bfs_q.push({ rst_r,now_s });
			now_s = now_s.substr(0, now_s.size() - 1);
		}
	}

}

int main() {
	int size;
	cin >> size;

	for (int i = 0; i < size; i++) {
		int start, end;
		cin >> start >> end;
		string result = bfs(start, end);
		cout << result << endl;
	}
}