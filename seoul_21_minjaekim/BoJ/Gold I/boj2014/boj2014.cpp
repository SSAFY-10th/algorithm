#include <algorithm>
#include <iostream>
#include <limits>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);


    int k, n;
    cin >> k >> n;

    pair<int, int> prime_numbers[100];
    for (int i = 0; i < k; ++i) {
        cin >> prime_numbers[i].first;
        prime_numbers[i].second = 0;
    }
    int ugly_numbers[100001] = { 1 };

    for (int i = 1; i < n + 1; ++i)
    {
        long long min_number = numeric_limits<long long>::max();
        int min_index[100];
        int min_count = 0;
        for (int j = 0; j < k; ++j)
        {
            int next_number = prime_numbers[j].first * ugly_numbers[prime_numbers[j].second];
            if (next_number < min_number)
            {
                min_number = prime_numbers[j].first * ugly_numbers[prime_numbers[j].second];
                min_index[0] = j;
                min_count = 1;
            }
            else if (next_number == min_number)
            {
                min_index[min_count] = j;
                ++min_count;
            }
        }
        ugly_numbers[i] = min_number;
        for (int j = 0; j < min_count; ++j)
            ++prime_numbers[min_index[j]].second;
    }

    cout << ugly_numbers[n] << endl;

    return 0;
}
