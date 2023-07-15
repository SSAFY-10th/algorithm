#include <bits/stdc++.h>
using namespace std;
#define ll long long
#define fastio ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
#define loop(k, sz) for(int k = 0; k < sz; k++)
#define endl '\n'

class Node{
public:
    char alphabet;
    Node* next[26];
};
int N, M;
vector<string> wordlist;
vector<string> checklist;
int main() {
    fastio
    cin >> N >> M;
    loop(n, N){
        string tmp;
        cin >> tmp;
        wordlist.push_back(tmp);
    }
    loop(m, M){
        string tmp;
        cin >> tmp;
        checklist.push_back(tmp);
    }
    Node* headnode = new Node();
    loop(n, N){
        string str = wordlist[n];
        Node* currNode = headnode;
        loop(m, str.length()){
            char currc = str[m];
            int num = currc - 'a';
            if(currNode->next[num] == NULL) currNode->next[num] = new Node();
            currNode = currNode->next[num];
        }
    }
    int includeString = 0;
    loop(n, M){
        string str = checklist[n];
        Node* currNode = headnode;
        bool existCheck = true;
        loop(m, str.length()){
            char currc = str[m];
            int num = currc - 'a';
            if(currNode->next[num] == NULL) {
                existCheck = false;
                break;
            }
            currNode = currNode->next[num];
        }
        if(existCheck) includeString++;
    }
    cout << includeString << endl;
    return 0;
}
