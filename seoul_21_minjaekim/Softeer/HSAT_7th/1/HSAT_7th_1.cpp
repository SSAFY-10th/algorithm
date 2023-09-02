#include<iostream>
#include<algorithm>
#include<unordered_map>

using namespace std;

int main(int argc, char** argv)
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, q;
    cin >> n >> q;

    int cars[50000];
    for (int i = 0; i < n; ++i)
        cin >> cars[i];

    sort(cars, cars + n);

    unordered_map<int, int> map;
    for (int i = 0; i < n; ++i)
        map[cars[i]] = i + 1;

    for (int i = 0; i < q; ++i) {
        int m, m_idx;
        cin >> m;
        m_idx = map[m];
        cout << (m_idx ? (m_idx - 1) * (n - m_idx) : m_idx) << '\n';
    }

    return 0;
}
